package io.renren.modules.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.M4gSubsRealTagsEntity;

import java.util.List;
import java.util.Map;

/**
 * sub-tags-link
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-16 10:30:54
 */
public interface M4gSubsRealTagsService extends IService<M4gSubsRealTagsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageWithCustomWrapper(Map<String, Object> params, QueryWrapper wrapper);
    void saveTagsToSubsId(List<Long> tagIds, Long subsId);
    void deleteTagsBySubsId(List<Long> tagIds, Long subsId);
    void removeBySubsIds(List<Long> subsIds);
    List<Long> getTagIdsBySubId(Long subsId);
}

