package io.renren.modules.app.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.dao.M4gSubsRealTagsDao;
import io.renren.modules.app.dao.M4gSubscriberDao;
import io.renren.modules.app.entity.M4gSubsRealTagsEntity;
import io.renren.modules.app.vo.SubList;
import io.renren.modules.app.vo.SubscriberVO;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.service.M4gSubsRealTagsService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.app.entity.M4gSubscriberEntity;
import io.renren.modules.app.service.M4gSubscriberService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 邮箱管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@RestController
@RequestMapping("generator/m4gsubscriber")
@Api("Subscriber 接口")
public class M4gSubscriberController extends AbstractController {
    @Autowired
    private M4gSubscriberService m4gSubscriberService;

    @Autowired
    private M4gSubsRealTagsService m4gSubsRealTagsService;

    @Autowired
    M4gSubsRealTagsDao subsRealTagsDao;

    @Autowired
    M4gSubscriberDao subscriberDao;

    /**
     * 列表
     */
    @GetMapping("/list")
    @Login
    @RequiresPermissions("generator:m4gsubscriber:list")
    @ApiOperation("list")
    public R list(@RequestParam Map<String, Object> params){
        QueryWrapper<M4gSubscriberEntity> wrapper = new QueryWrapper<>();
        Long userId = getUserId();
        if (userId != 1l) {
            wrapper.lambda().eq(M4gSubscriberEntity::getOwnedBy, userId);
        }

        PageUtils page = m4gSubscriberService.queryPageWithCustomWrapper(params, wrapper);

        return R.ok().put("page", page);
    }

    @PostMapping("/listbyparam")
    @Login
    @RequiresPermissions("generator:m4gsubscriber:list")
    @ApiOperation("list")
    public R listByParam(@RequestBody SubList dto){
        Map<String, Object> param = new HashMap<>();
        if (dto.getCategoryIds() != null && dto.getCategoryIds().size() != 0) {
            param.put("categoryIds", dto.getCategoryIds());
        }
        if (dto.getTagIds() != null && dto.getTagIds().size() != 0) {
            param.put("tagIds", dto.getTagIds());
        }
        System.out.println(param);
        List<M4gSubscriberEntity> validEntities = subscriberDao.findValidByParams(param);
        return R.ok().put("data", validEntities);
    }

    @GetMapping("/listv2")
    @Login
    @RequiresPermissions("generator:m4gsubscriber:list")
    @ApiOperation("list")
    public R listv2(@RequestParam Map<String, Object> params){
        Long userId = getUserId();
        if (userId != 1l) {
            params.put("ownedBy", userId);
        }
        if (params.get("categoryIds") != null) {
            String[] categoryIds = ((String) params.get("categoryIds")).split(",");
            List<Long> catList = new ArrayList<>();
            for (String categoryId : categoryIds) {
                catList.add(Long.valueOf(categoryId));
            }
            params.put("categoryIds", catList);
        }
        if (params.get("tags") != null && !StringUtils.isEmpty(params.get("tags"))) {
            String[] tagIds = ((String) params.get("tags")).split(",");
            List<Long> tagList = new ArrayList<>();
            for (String categoryId : tagIds) {
                tagList.add(Long.valueOf(categoryId));
            }
            params.put("tagIds", tagList);
        }
        PageUtils page = m4gSubscriberService.queryPageWithFilter(params);

        // get subId to tags map
        List<Long> subIds = new ArrayList<>();
        for (Object o : page.getList()) {
            M4gSubscriberEntity entity = (M4gSubscriberEntity) o;
            subIds.add(entity.getId());
        }
        if (page.getTotalCount() == 0) {
            return R.ok().put("page", page);
        }
        List<M4gSubsRealTagsEntity> links = subsRealTagsDao.getByTagIds(subIds);
        Map<Long, List<Long>> map = new HashMap<>();
        for (M4gSubsRealTagsEntity link : links) {
            List<Long> longs = map.get(link.getSubsId());
            if (longs == null) {
                List<Long> tagIds = new ArrayList<>();
                tagIds.add(link.getTagId());
                map.put(link.getSubsId(), tagIds);
            } else {
                List<Long> tagIds = map.get(link.getSubsId());
                tagIds.add(link.getTagId());
                map.put(link.getSubsId(), tagIds);
            }
        }

        // attach tagsInfo
        List<SubscriberVO> voList = new ArrayList<>();
        for (Object o : page.getList()) {
            M4gSubscriberEntity entity = (M4gSubscriberEntity) o;
            SubscriberVO vo = new SubscriberVO();
            BeanUtils.copyProperties(entity, vo);
            vo.setRealTags(map.get(vo.getId()));
            voList.add(vo);
        }
        page.setList(voList);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("generator:m4gsubscriber:info")
    @ApiOperation("info")
    public R info(@PathVariable("id") Long id){
		M4gSubscriberEntity m4gSubscriber = m4gSubscriberService.getById(id);
        SubscriberVO subscriberVO = new SubscriberVO();
        BeanUtils.copyProperties(m4gSubscriber, subscriberVO);
        subscriberVO.setRealTags(m4gSubsRealTagsService.getTagIdsBySubId(id));
        return R.ok().put("m4gSubscriber", subscriberVO);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("generator:m4gsubscriber:save")
    @ApiOperation("save")
    public R save(@RequestBody SubscriberVO m4gSubscriber){
        M4gSubscriberEntity m4gSubscriberEntity = new M4gSubscriberEntity();
        BeanUtils.copyProperties(m4gSubscriber, m4gSubscriberEntity);
        m4gSubscriberEntity.setOwnedBy(getUserId());

		m4gSubscriberService.saveSubsWithTags(m4gSubscriberEntity, m4gSubscriber.getRealTags());

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("generator:m4gsubscriber:update")
    @ApiOperation("update")
    public R update(@RequestBody SubscriberVO m4gSubscriber){
        M4gSubscriberEntity m4gSubscriberEntity = new M4gSubscriberEntity();
        BeanUtils.copyProperties(m4gSubscriber, m4gSubscriberEntity);

		m4gSubscriberService.updateEntityWithTags(m4gSubscriberEntity, m4gSubscriber.getRealTags());

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("generator:m4gsubscriber:delete")
    @ApiOperation("delete")
    public R delete(@RequestBody Long[] ids){
		m4gSubscriberService.removeByIdsCascade(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/import")
    @RequiresPermissions("generator:m4gsubscriber:import")
    @ApiOperation("import")
    @Login
    public R delete(@RequestBody List<M4gSubscriberEntity> subsList, Long tagId){
        for (M4gSubscriberEntity m4gSubscriberEntity : subsList) {
            m4gSubscriberEntity.setOwnedBy(getUserId());
            m4gSubscriberEntity.setTagId(tagId);
        }
        m4gSubscriberService.saveBatch(subsList, subsList.size());
        return R.ok();
    }

}
