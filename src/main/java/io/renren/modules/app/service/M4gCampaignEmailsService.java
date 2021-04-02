package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.M4gCampaignEmailsEntity;

import java.util.Map;

/**
 * 群发邮件统计
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
public interface M4gCampaignEmailsService extends IService<M4gCampaignEmailsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

