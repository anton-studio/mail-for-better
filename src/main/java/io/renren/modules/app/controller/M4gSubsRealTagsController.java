package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.app.entity.M4gTagsEntity;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.app.entity.M4gSubsRealTagsEntity;
import io.renren.modules.app.service.M4gSubsRealTagsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * sub-tags-link
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-16 10:30:54
 */
@RestController
@RequestMapping("generator/m4gsubsrealtags")
public class M4gSubsRealTagsController extends AbstractController {
    @Autowired
    private M4gSubsRealTagsService m4gSubsRealTagsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:m4gsubsrealtags:list")
    public R list(@RequestParam Map<String, Object> params){
        QueryWrapper<M4gTagsEntity> wrapper = new QueryWrapper<>();
        Long userId = getUserId();
        if (userId != 1l) {
            wrapper.lambda().eq(M4gTagsEntity::getOwnedBy, userId);
        }
        PageUtils page = m4gSubsRealTagsService.queryPageWithCustomWrapper(params, wrapper);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:m4gsubsrealtags:info")
    public R info(@PathVariable("id") Long id){
		M4gSubsRealTagsEntity m4gSubsRealTags = m4gSubsRealTagsService.getById(id);

        return R.ok().put("m4gSubsRealTags", m4gSubsRealTags);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:m4gsubsrealtags:save")
    public R save(@RequestBody M4gSubsRealTagsEntity m4gSubsRealTags){
		m4gSubsRealTagsService.save(m4gSubsRealTags);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:m4gsubsrealtags:update")
    public R update(@RequestBody M4gSubsRealTagsEntity m4gSubsRealTags){
		m4gSubsRealTagsService.updateById(m4gSubsRealTags);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:m4gsubsrealtags:delete")
    public R delete(@RequestBody Long[] ids){
		m4gSubsRealTagsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
