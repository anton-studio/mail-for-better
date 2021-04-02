package io.renren.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gTemplatesDao;
import io.renren.modules.app.entity.M4gTemplatesEntity;
import io.renren.modules.app.service.M4gTemplatesService;


@Service("m4gTemplatesService")
public class M4gTemplatesServiceImpl extends ServiceImpl<M4gTemplatesDao, M4gTemplatesEntity> implements M4gTemplatesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gTemplatesEntity> page = this.page(
                new Query<M4gTemplatesEntity>().getPage(params),
                new QueryWrapper<M4gTemplatesEntity>()
        );

        return new PageUtils(page);
    }

}