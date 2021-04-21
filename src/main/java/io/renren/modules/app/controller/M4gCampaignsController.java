package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.dao.M4gCampaignsDao;
import io.renren.modules.app.entity.M4gTagsEntity;
import io.renren.modules.app.entity.StatsEntity;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.service.EmailService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@Api("campaign 接口")
public class M4gCampaignsController extends AbstractController {
    @Autowired
    private M4gCampaignsService m4gCampaignsService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private M4gCampaignsDao campaignsDao;

    /**
     * 列表
     */
    @Login
    @GetMapping("/list")
    @RequiresPermissions("generator:m4gcampaigns:list")
    @ApiOperation("list")
    public R list(@RequestParam Map<String, Object> params){
        QueryWrapper<M4gCampaignsEntity> wrapper = new QueryWrapper<>();

        Long userId = getUserId();
        if (userId != 1l) {
            wrapper.lambda().eq(M4gCampaignsEntity::getOwnedBy, userId);
        }

        PageUtils page = m4gCampaignsService.queryPageWithCustomWrapper(params, wrapper);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("generator:m4gcampaigns:info")
    @ApiOperation("info")
    public R info(@PathVariable("id") Long id){
		M4gCampaignsEntity m4gCampaigns = m4gCampaignsService.getById(id);

        return R.ok().put("m4gCampaigns", m4gCampaigns);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("generator:m4gcampaigns:save")
    @ApiOperation("save")
    @Login
    public R save(@RequestBody M4gCampaignsEntity m4gCampaigns){
        m4gCampaigns.setOwnedBy(getUserId());
		m4gCampaignsService.save(m4gCampaigns);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("generator:m4gcampaigns:update")
    @ApiOperation("update")
    public R update(@RequestBody M4gCampaignsEntity m4gCampaigns){
		m4gCampaignsService.updateById(m4gCampaigns);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("generator:m4gcampaigns:delete")
    @ApiOperation("delete")
    public R delete(@RequestBody Long[] ids){
		m4gCampaignsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("/send/{id}")
    @ApiOperation("发送 Campaign")
//    @RequiresPermissions("generator:m4gcampaigns:delete")
    public R send(@PathVariable Long id){
        emailService.sendByCampaignId(id);
        return R.ok();
    }

    @Login
    @GetMapping("/stats")
    @RequiresPermissions("generator:m4gcampaigns:list")
    @ApiOperation("list")
    public R stats(@RequestParam Map<String, Object> params){
        Long userId = getUserId();
        if (userId != 1l) {
            // sales
            // todo: limit them
            List<StatsEntity> allStats = campaignsDao.getAllStatsById(userId);
            return R.ok().put("data", allStats);
        } else {
            // admin
            List<StatsEntity> allStats;
            if (!StringUtils.isEmpty(params.get("id")) ) {
                Long id = Long.valueOf((String) params.get("id"));
                allStats = campaignsDao.getAllStatsById(id);
            } else {
                allStats = campaignsDao.getAllStats();
            }
            return R.ok().put("data", allStats);
        }

    }

}
