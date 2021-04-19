package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.entity.M4gTagsEntity;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.app.entity.M4gRealTagsEntity;
import io.renren.modules.app.service.M4gRealTagsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 多标签管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-16 10:30:53
 */
@RestController
@RequestMapping("generator/m4grealtags")
public class M4gRealTagsController extends AbstractController {
    @Autowired
    private M4gRealTagsService m4gRealTagsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4grealtags:list")
    public R list(@RequestParam Map<String, Object> params){
        QueryWrapper<M4gRealTagsEntity> wrapper = new QueryWrapper<>();
        Long userId = getUserId();
        if (userId != 1l) {
            wrapper.lambda().eq(M4gRealTagsEntity::getOwnedBy, userId);
        }

        PageUtils page = m4gRealTagsService.queryPageWithCustomWrapper(params, wrapper);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4grealtags:info")
    public R info(@PathVariable("id") Long id){
		M4gRealTagsEntity m4gRealTags = m4gRealTagsService.getById(id);

        return R.ok().put("m4gRealTags", m4gRealTags);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4grealtags:save")
    public R save(@RequestBody M4gRealTagsEntity m4gRealTags){
        m4gRealTags.setOwnedBy(getUserId());
		m4gRealTagsService.save(m4gRealTags);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4grealtags:update")
    public R update(@RequestBody M4gRealTagsEntity m4gRealTags){
		m4gRealTagsService.updateById(m4gRealTags);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4grealtags:delete")
    public R delete(@RequestBody Long[] ids){
		m4gRealTagsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
