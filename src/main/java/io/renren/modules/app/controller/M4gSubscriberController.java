package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.M4gCampaignsEntity;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("generator:m4gsubscriber:info")
    @ApiOperation("info")
    public R info(@PathVariable("id") Long id){
		M4gSubscriberEntity m4gSubscriber = m4gSubscriberService.getById(id);

        return R.ok().put("m4gSubscriber", m4gSubscriber);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("generator:m4gsubscriber:save")
    @ApiOperation("save")
    public R save(@RequestBody M4gSubscriberEntity m4gSubscriber){
        m4gSubscriber.setOwnedBy(getUserId());
		m4gSubscriberService.save(m4gSubscriber);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("generator:m4gsubscriber:update")
    @ApiOperation("update")
    public R update(@RequestBody M4gSubscriberEntity m4gSubscriber){
		m4gSubscriberService.updateById(m4gSubscriber);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("generator:m4gsubscriber:delete")
    @ApiOperation("delete")
    public R delete(@RequestBody Long[] ids){
		m4gSubscriberService.removeByIds(Arrays.asList(ids));

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
