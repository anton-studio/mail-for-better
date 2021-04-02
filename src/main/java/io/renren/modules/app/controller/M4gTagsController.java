package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.app.entity.M4gTagsEntity;
import io.renren.modules.app.service.M4gTagsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 标签管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@RestController
@RequestMapping("generator/m4gtags")
public class M4gTagsController {
    @Autowired
    private M4gTagsService m4gTagsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4gtags:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = m4gTagsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4gtags:info")
    public R info(@PathVariable("id") Long id){
		M4gTagsEntity m4gTags = m4gTagsService.getById(id);

        return R.ok().put("m4gTags", m4gTags);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4gtags:save")
    public R save(@RequestBody M4gTagsEntity m4gTags){
		m4gTagsService.save(m4gTags);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4gtags:update")
    public R update(@RequestBody M4gTagsEntity m4gTags){
		m4gTagsService.updateById(m4gTags);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4gtags:delete")
    public R delete(@RequestBody Long[] ids){
		m4gTagsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
