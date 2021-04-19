package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.app.entity.M4gCampaignsEntity;
import io.renren.modules.app.entity.M4gSubscriberEntity;
import io.renren.modules.app.vo.SubscriberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 邮箱管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Mapper
public interface M4gSubscriberDao extends BaseMapper<M4gSubscriberEntity> {
    IPage<M4gSubscriberEntity> selectPageWithFilter(IPage<M4gSubscriberEntity> page, Map<String, Object> params);
    List<M4gSubscriberEntity> findValidByParams(@Param("params") Map<String, Object> params);
    void disAbleAllByEmailAddress(@Param("emailArr") String emailArr);
    List<M4gSubscriberEntity> exportWithFilter(@Param("params") Map<String, Object> params);
}
