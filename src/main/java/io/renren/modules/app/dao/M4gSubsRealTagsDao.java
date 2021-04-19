package io.renren.modules.app.dao;

import io.renren.modules.app.entity.M4gSubsRealTagsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sub-tags-link
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-16 10:30:54
 */
@Mapper
public interface M4gSubsRealTagsDao extends BaseMapper<M4gSubsRealTagsEntity> {
    void deleteBySubIdAndTagIds(@Param("subsIds")Long subsId, @Param("tagIds")List<Long> tagIds);
    void deleteBySubsIds(@Param("subsIds")List<Long> subsIds);
    List<M4gSubsRealTagsEntity> getByTagIds(@Param("subsIds") List<Long> subsIds);
}
