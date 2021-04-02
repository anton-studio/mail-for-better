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

import io.renren.modules.app.entity.M4gCampaignEmailsEntity;
import io.renren.modules.app.service.M4gCampaignEmailsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 群发邮件统计
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@RestController
@RequestMapping("generator/m4gcampaignemails")
public class M4gCampaignEmailsController {
    @Autowired
    private M4gCampaignEmailsService m4gCampaignEmailsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4gcampaignemails:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = m4gCampaignEmailsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4gcampaignemails:info")
    public R info(@PathVariable("id") Long id){
		M4gCampaignEmailsEntity m4gCampaignEmails = m4gCampaignEmailsService.getById(id);

        return R.ok().put("m4gCampaignEmails", m4gCampaignEmails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4gcampaignemails:save")
    public R save(@RequestBody M4gCampaignEmailsEntity m4gCampaignEmails){
		m4gCampaignEmailsService.save(m4gCampaignEmails);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4gcampaignemails:update")
    public R update(@RequestBody M4gCampaignEmailsEntity m4gCampaignEmails){
		m4gCampaignEmailsService.updateById(m4gCampaignEmails);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4gcampaignemails:delete")
    public R delete(@RequestBody Long[] ids){
		m4gCampaignEmailsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
