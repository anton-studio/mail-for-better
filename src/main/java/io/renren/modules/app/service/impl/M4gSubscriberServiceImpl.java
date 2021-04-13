package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gSubscriberDao;
import io.renren.modules.app.entity.M4gSubscriberEntity;
import io.renren.modules.app.service.M4gSubscriberService;


@Service("m4gSubscriberService")
public class M4gSubscriberServiceImpl extends ServiceImpl<M4gSubscriberDao, M4gSubscriberEntity> implements M4gSubscriberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gSubscriberEntity> page = this.page(
                new Query<M4gSubscriberEntity>().getPage(params),
                new QueryWrapper<M4gSubscriberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageWithCustomWrapper(Map<String, Object> params, Wrapper wrapper) {
        IPage<M4gSubscriberEntity> page = this.page(
                new Query<M4gSubscriberEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<M4gSubscriberEntity> findByTagId(Long id) {
        QueryWrapper<M4gSubscriberEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(M4gSubscriberEntity::getTagId, id);
        return this.getBaseMapper().selectList(wrapper);
    }

    @Override
    public List<M4gSubscriberEntity> findValidByTagId(Long id) {
        QueryWrapper<M4gSubscriberEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(M4gSubscriberEntity::getTagId, id);
        wrapper.lambda().eq(M4gSubscriberEntity::getIsValid, Boolean.TRUE);
        return this.getBaseMapper().selectList(wrapper);
    }

}