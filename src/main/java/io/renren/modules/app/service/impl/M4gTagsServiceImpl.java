package io.renren.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gTagsDao;
import io.renren.modules.app.entity.M4gTagsEntity;
import io.renren.modules.app.service.M4gTagsService;


@Service("m4gTagsService")
public class M4gTagsServiceImpl extends ServiceImpl<M4gTagsDao, M4gTagsEntity> implements M4gTagsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gTagsEntity> page = this.page(
                new Query<M4gTagsEntity>().getPage(params),
                new QueryWrapper<M4gTagsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageWithCustomWrapper(Map<String, Object> params, QueryWrapper wrapper) {
        IPage<M4gTagsEntity> page = this.page(
                new Query<M4gTagsEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}