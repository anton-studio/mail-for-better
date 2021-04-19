package io.renren.modules.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gSubsRealTagsDao;
import io.renren.modules.app.entity.M4gSubsRealTagsEntity;
import io.renren.modules.app.service.M4gSubsRealTagsService;


@Service("m4gSubsRealTagsService")
public class M4gSubsRealTagsServiceImpl extends ServiceImpl<M4gSubsRealTagsDao, M4gSubsRealTagsEntity> implements M4gSubsRealTagsService {

    @Autowired
    M4gSubsRealTagsDao subsRealTagsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gSubsRealTagsEntity> page = this.page(
                new Query<M4gSubsRealTagsEntity>().getPage(params),
                new QueryWrapper<M4gSubsRealTagsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageWithCustomWrapper(Map<String, Object> params, QueryWrapper wrapper) {
        return null;
    }

    @Override
    public void saveTagsToSubsId(List<Long> tagIds, Long subsId) {
        List<M4gSubsRealTagsEntity> subRealTags = new ArrayList<>();
        for (Long tagId : tagIds) {
            M4gSubsRealTagsEntity m4gSubsRealTagsEntity = new M4gSubsRealTagsEntity();
            m4gSubsRealTagsEntity.setSubsId(subsId);
            m4gSubsRealTagsEntity.setTagId(tagId);
            subRealTags.add(m4gSubsRealTagsEntity);
        }
        this.saveBatch(subRealTags);
    }

    @Override
    public void deleteTagsBySubsId(List<Long> tagIds, Long subsId) {
        subsRealTagsDao.deleteBySubIdAndTagIds(subsId, tagIds);
    }

    @Override
    public void removeBySubsIds(List<Long> subsIds) {
        subsRealTagsDao.deleteBySubsIds(subsIds);
    }

    @Override
    public List<Long> getTagIdsBySubId(Long subsId) {
        QueryWrapper<M4gSubsRealTagsEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(M4gSubsRealTagsEntity::getSubsId, subsId);
        List<M4gSubsRealTagsEntity> m4gSubsRealTagsEntities = this.getBaseMapper().selectList(wrapper);
        List<Long> tags = new ArrayList<>();
        for (M4gSubsRealTagsEntity m4gSubsRealTagsEntity : m4gSubsRealTagsEntities) {
            tags.add(m4gSubsRealTagsEntity.getTagId());
        }
        return tags;
    }

}