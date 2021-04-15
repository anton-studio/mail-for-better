package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.bo.StatRecord;
import io.renren.modules.app.dao.M4gCampaignEmailsDao;
import io.renren.modules.app.dao.UserDao;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api("campaign email 接口")
public class M4gCampaignEmailsController {
    @Autowired
    private M4gCampaignEmailsService m4gCampaignEmailsService;

    @Autowired
    private M4gCampaignEmailsDao m4gCampaignEmailsDao;

    @GetMapping("/all/{id}")
//    @RequiresPermissions("generator:m4gcampaignemails:list")
    public R list(@PathVariable("id") Long campId){

        List<StatRecord> stats = m4gCampaignEmailsDao.getStatsByCampId(campId);
        return R.ok().put("data", stats);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("generator:m4gcampaignemails:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = m4gCampaignEmailsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("generator:m4gcampaignemails:info")
    public R info(@PathVariable("id") Long id){
		M4gCampaignEmailsEntity m4gCampaignEmails = m4gCampaignEmailsService.getById(id);

        return R.ok().put("m4gCampaignEmails", m4gCampaignEmails);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("generator:m4gcampaignemails:save")
    public R save(@RequestBody M4gCampaignEmailsEntity m4gCampaignEmails){
		m4gCampaignEmailsService.save(m4gCampaignEmails);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("generator:m4gcampaignemails:update")
    public R update(@RequestBody M4gCampaignEmailsEntity m4gCampaignEmails){
		m4gCampaignEmailsService.updateById(m4gCampaignEmails);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("generator:m4gcampaignemails:delete")
    public R delete(@RequestBody Long[] ids){
		m4gCampaignEmailsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
