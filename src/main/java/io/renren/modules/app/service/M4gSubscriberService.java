package io.renren.modules.app.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.M4gSubscriberEntity;

import java.util.List;
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
    PageUtils queryPageWithCustomWrapper(Map<String, Object> params, Wrapper wrapper);
    PageUtils queryPageWithFilter(Map<String, Object> params);
    List<M4gSubscriberEntity> findByTagId(Long id);
    List<M4gSubscriberEntity> findValidByTagId(Long id);
    void saveSubsWithTags(M4gSubscriberEntity entity, List<Long> tags);
    void updateEntityWithTags(M4gSubscriberEntity entity, List<Long> tags);
    void removeByIdsCascade(List<Long> subsIds);
}

