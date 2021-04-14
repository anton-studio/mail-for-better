package io.renren.modules.app.dao;

import io.renren.modules.app.bo.StatRecord;
import io.renren.modules.app.entity.M4gCampaignEmailsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 群发邮件统计
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Mapper
public interface M4gCampaignEmailsDao extends BaseMapper<M4gCampaignEmailsEntity> {
	List<StatRecord> getStatsByCampId(Long campId);
}
