package io.renren.modules.app.service.impl;

import io.renren.modules.app.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.M4gCampaignEmailsDao;
import io.renren.modules.app.entity.M4gCampaignEmailsEntity;
import io.renren.modules.app.service.M4gCampaignEmailsService;


@Service("m4gCampaignEmailsService")
public class M4gCampaignEmailsServiceImpl extends ServiceImpl<M4gCampaignEmailsDao, M4gCampaignEmailsEntity> implements M4gCampaignEmailsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<M4gCampaignEmailsEntity> page = this.page(
                new Query<M4gCampaignEmailsEntity>().getPage(params),
                new QueryWrapper<M4gCampaignEmailsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void createByList(List<M4gCampaignEmailsEntity> list) {
        this.saveBatch(list, list.size());
//        CommonUtils.batches(list, 888).forEach(this::saveBatch);
    }

    @Override
    public M4gCampaignEmailsEntity getByTrackingId(String trackingId) {
        QueryWrapper<M4gCampaignEmailsEntity> wrap = new QueryWrapper<>();
        wrap.lambda().eq(M4gCampaignEmailsEntity::getTrackingId, trackingId);
        return this.getBaseMapper().selectOne(wrap);
    }

}