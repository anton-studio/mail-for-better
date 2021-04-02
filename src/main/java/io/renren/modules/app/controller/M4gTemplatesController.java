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

import io.renren.modules.app.entity.M4gTemplatesEntity;
import io.renren.modules.app.service.M4gTemplatesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 模版管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@RestController
@RequestMapping("generator/m4gtemplates")
public class M4gTemplatesController {
    @Autowired
    private M4gTemplatesService m4gTemplatesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4gtemplates:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = m4gTemplatesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4gtemplates:info")
    public R info(@PathVariable("id") Long id){
		M4gTemplatesEntity m4gTemplates = m4gTemplatesService.getById(id);

        return R.ok().put("m4gTemplates", m4gTemplates);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4gtemplates:save")
    public R save(@RequestBody M4gTemplatesEntity m4gTemplates){
		m4gTemplatesService.save(m4gTemplates);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4gtemplates:update")
    public R update(@RequestBody M4gTemplatesEntity m4gTemplates){
		m4gTemplatesService.updateById(m4gTemplates);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4gtemplates:delete")
    public R delete(@RequestBody Long[] ids){
		m4gTemplatesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
