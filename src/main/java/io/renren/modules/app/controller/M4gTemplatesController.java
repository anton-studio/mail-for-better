package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.M4gTagsEntity;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api("Template 接口")
public class M4gTemplatesController extends AbstractController {
    @Autowired
    private M4gTemplatesService m4gTemplatesService;

    /**
     * 列表
     */
    @Login
    @GetMapping("/list")
    @RequiresPermissions("generator:m4gtemplates:list")
    @ApiOperation("list")
    public R list(@RequestParam Map<String, Object> params){
        QueryWrapper<M4gTemplatesEntity> wrapper = new QueryWrapper<>();
        Long userId = getUserId();
        if (userId != 1l) {
            wrapper.lambda().eq(M4gTemplatesEntity::getOwnedBy, userId);
        }
        PageUtils page = m4gTemplatesService.queryPageWithCustomWrapper(params, wrapper);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("generator:m4gtemplates:info")
    @ApiOperation("info")
    public R info(@PathVariable("id") Long id){
		M4gTemplatesEntity m4gTemplates = m4gTemplatesService.getById(id);

        return R.ok().put("m4gTemplates", m4gTemplates);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("generator:m4gtemplates:save")
    @Login
    @ApiOperation("save")
    public R save(@RequestBody M4gTemplatesEntity m4gTemplates){
        m4gTemplates.setOwnedBy(getUserId());
		m4gTemplatesService.save(m4gTemplates);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("generator:m4gtemplates:update")
    @ApiOperation("update")
    public R update(@RequestBody M4gTemplatesEntity m4gTemplates){
		m4gTemplatesService.updateById(m4gTemplates);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("generator:m4gtemplates:delete")
    @ApiOperation("delete")
    public R delete(@RequestBody Long[] ids){
		m4gTemplatesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
