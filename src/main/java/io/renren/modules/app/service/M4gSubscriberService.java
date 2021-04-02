package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.M4gSubscriberEntity;

import java.util.Map;

/**
 * 邮箱管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
public interface M4gSubscriberService extends IService<M4gSubscriberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

