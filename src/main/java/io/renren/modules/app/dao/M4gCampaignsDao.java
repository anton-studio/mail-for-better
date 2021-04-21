package io.renren.modules.app.dao;

import io.renren.modules.app.entity.M4gCampaignsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.StatsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 群发管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Mapper
public interface M4gCampaignsDao extends BaseMapper<M4gCampaignsEntity> {
	void updateSendTotalById(@Param("id") Long id, @Param("totalSentCount") Long totalSentTotal);
    void incrementByField(@Param("id") Long id, @Param("field") String field);
    List<StatsEntity> getAllStats();
    List<StatsEntity> getAllStatsById(@Param("id") Long id);
}
