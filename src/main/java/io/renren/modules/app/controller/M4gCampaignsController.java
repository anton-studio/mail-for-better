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

import io.renren.modules.app.entity.M4gCampaignsEntity;
import io.renren.modules.app.service.M4gCampaignsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 群发管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@RestController
@RequestMapping("generator/m4gcampaigns")
public class M4gCampaignsController {
    @Autowired
    private M4gCampaignsService m4gCampaignsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4gcampaigns:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = m4gCampaignsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4gcampaigns:info")
    public R info(@PathVariable("id") Long id){
		M4gCampaignsEntity m4gCampaigns = m4gCampaignsService.getById(id);

        return R.ok().put("m4gCampaigns", m4gCampaigns);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4gcampaigns:save")
    public R save(@RequestBody M4gCampaignsEntity m4gCampaigns){
		m4gCampaignsService.save(m4gCampaigns);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4gcampaigns:update")
    public R update(@RequestBody M4gCampaignsEntity m4gCampaigns){
		m4gCampaignsService.updateById(m4gCampaigns);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4gcampaigns:delete")
    public R delete(@RequestBody Long[] ids){
		m4gCampaignsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
