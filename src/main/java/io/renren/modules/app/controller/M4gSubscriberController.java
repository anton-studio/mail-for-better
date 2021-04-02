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
public class M4gSubscriberController {
    @Autowired
    private M4gSubscriberService m4gSubscriberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4gsubscriber:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = m4gSubscriberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4gsubscriber:info")
    public R info(@PathVariable("id") Long id){
		M4gSubscriberEntity m4gSubscriber = m4gSubscriberService.getById(id);

        return R.ok().put("m4gSubscriber", m4gSubscriber);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4gsubscriber:save")
    public R save(@RequestBody M4gSubscriberEntity m4gSubscriber){
		m4gSubscriberService.save(m4gSubscriber);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4gsubscriber:update")
    public R update(@RequestBody M4gSubscriberEntity m4gSubscriber){
		m4gSubscriberService.updateById(m4gSubscriber);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4gsubscriber:delete")
    public R delete(@RequestBody Long[] ids){
		m4gSubscriberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
