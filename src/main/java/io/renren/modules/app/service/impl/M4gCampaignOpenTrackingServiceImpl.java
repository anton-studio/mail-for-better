package io.renren.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gCampaignOpenTrackingDao;
import io.renren.modules.app.entity.M4gCampaignOpenTrackingEntity;
import io.renren.modules.app.service.M4gCampaignOpenTrackingService;


@Service("m4gCampaignOpenTrackingService")
public class M4gCampaignOpenTrackingServiceImpl extends ServiceImpl<M4gCampaignOpenTrackingDao, M4gCampaignOpenTrackingEntity> implements M4gCampaignOpenTrackingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gCampaignOpenTrackingEntity> page = this.page(
                new Query<M4gCampaignOpenTrackingEntity>().getPage(params),
                new QueryWrapper<M4gCampaignOpenTrackingEntity>()
        );

        return new PageUtils(page);
    }

}