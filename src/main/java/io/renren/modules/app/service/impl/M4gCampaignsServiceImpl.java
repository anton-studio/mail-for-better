package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gCampaignsDao;
import io.renren.modules.app.entity.M4gCampaignsEntity;
import io.renren.modules.app.service.M4gCampaignsService;


@Service("m4gCampaignsService")
public class M4gCampaignsServiceImpl extends ServiceImpl<M4gCampaignsDao, M4gCampaignsEntity> implements M4gCampaignsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gCampaignsEntity> page = this.page(
                new Query<M4gCampaignsEntity>().getPage(params),
                new QueryWrapper<M4gCampaignsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageWithCustomWrapper(Map<String, Object> params, Wrapper wrapper) {
        IPage<M4gCampaignsEntity> page = this.page(
                new Query<M4gCampaignsEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}