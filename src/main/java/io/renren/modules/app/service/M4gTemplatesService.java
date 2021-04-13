package io.renren.modules.app.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.M4gTemplatesEntity;

import java.util.Map;

/**
 * 模版管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
public interface M4gTemplatesService extends IService<M4gTemplatesEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageWithCustomWrapper(Map<String, Object> params, Wrapper wrapper);
}

