package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.M4gTagsEntity;

import java.util.Map;

/**
 * 标签管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
public interface M4gTagsService extends IService<M4gTagsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

