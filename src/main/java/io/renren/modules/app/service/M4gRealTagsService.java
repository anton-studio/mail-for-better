package io.renren.modules.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.M4gRealTagsEntity;

import java.util.List;
import java.util.Map;

/**
 * 多标签管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-16 10:30:53
 */
public interface M4gRealTagsService extends IService<M4gRealTagsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageWithCustomWrapper(Map<String, Object> params, QueryWrapper wrapper);
}

