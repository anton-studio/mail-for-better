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

import io.renren.modules.app.entity.M4gCampaignOpenTrackingEntity;
import io.renren.modules.app.service.M4gCampaignOpenTrackingService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 群发打开历史统计
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@RestController
@RequestMapping("generator/m4gcampaignopentracking")
public class M4gCampaignOpenTrackingController {
    @Autowired
    private M4gCampaignOpenTrackingService m4gCampaignOpenTrackingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4gcampaignopentracking:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = m4gCampaignOpenTrackingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4gcampaignopentracking:info")
    public R info(@PathVariable("id") Long id){
		M4gCampaignOpenTrackingEntity m4gCampaignOpenTracking = m4gCampaignOpenTrackingService.getById(id);

        return R.ok().put("m4gCampaignOpenTracking", m4gCampaignOpenTracking);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4gcampaignopentracking:save")
    public R save(@RequestBody M4gCampaignOpenTrackingEntity m4gCampaignOpenTracking){
		m4gCampaignOpenTrackingService.save(m4gCampaignOpenTracking);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4gcampaignopentracking:update")
    public R update(@RequestBody M4gCampaignOpenTrackingEntity m4gCampaignOpenTracking){
		m4gCampaignOpenTrackingService.updateById(m4gCampaignOpenTracking);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4gcampaignopentracking:delete")
    public R delete(@RequestBody Long[] ids){
		m4gCampaignOpenTrackingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
