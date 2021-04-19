package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sub-tags-link
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-16 10:30:54
 */
@Data
@TableName("m4g_subs_real_tags")
public class M4gSubsRealTagsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long subsId;
	/**
	 * 
	 */
	private Long tagId;
	/**
	 * 所属业务员
	 */
	private Long ownedBy;

}
