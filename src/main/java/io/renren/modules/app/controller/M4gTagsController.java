package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api("Tags 接口")
public class M4gTagsController extends AbstractController {
    @Autowired
    private M4gTagsService m4gTagsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @Login
    @RequiresPermissions("generator:m4gtags:list")
    @ApiOperation("list")
    public R list(@RequestParam Map<String, Object> params){
        QueryWrapper<M4gTagsEntity> wrapper = new QueryWrapper<>();
        Long userId = getUserId();
        if (userId != 1l) {
            wrapper.lambda().eq(M4gTagsEntity::getOwnedBy, userId);
        }
        PageUtils page = m4gTagsService.queryPageWithCustomWrapper(params, wrapper);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @Login
    @RequiresPermissions("generator:m4gtags:info")
    @ApiOperation("info")
    public R info(@PathVariable("id") Long id){
		M4gTagsEntity m4gTags = m4gTagsService.getById(id);

        return R.ok().put("m4gTags", m4gTags);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @Login
    @RequiresPermissions("generator:m4gtags:save")
    @ApiOperation("save")
    public R save(@RequestBody M4gTagsEntity m4gTags){
        m4gTags.setOwnedBy(getUserId());
		m4gTagsService.save(m4gTags);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Login
    @RequiresPermissions("generator:m4gtags:update")
    @ApiOperation("update")
    public R update(@RequestBody M4gTagsEntity m4gTags, @LoginUser UserEntity user){
		m4gTagsService.updateById(m4gTags);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @Login
    @RequiresPermissions("generator:m4gtags:delete")
    @ApiOperation("delete")
    public R delete(@RequestBody Long[] ids, @LoginUser UserEntity user){
		m4gTagsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
