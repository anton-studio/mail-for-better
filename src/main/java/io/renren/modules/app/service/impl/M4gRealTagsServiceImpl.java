package io.renren.modules.app.service.impl;

import io.renren.modules.app.entity.M4gTagsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gRealTagsDao;
import io.renren.modules.app.entity.M4gRealTagsEntity;
import io.renren.modules.app.service.M4gRealTagsService;


@Service("m4gRealTagsService")
public class M4gRealTagsServiceImpl extends ServiceImpl<M4gRealTagsDao, M4gRealTagsEntity> implements M4gRealTagsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gRealTagsEntity> page = this.page(
                new Query<M4gRealTagsEntity>().getPage(params),
                new QueryWrapper<M4gRealTagsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageWithCustomWrapper(Map<String, Object> params, QueryWrapper wrapper) {
        IPage<M4gTagsEntity> page = this.page(
                new Query<M4gRealTagsEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

}