package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 模版管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Data
@TableName("m4g_templates")
public class M4gTemplatesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 模版分类
	 */
	private String category;
	/**
	 * 所属业务员
	 */
	private Long ownedBy;
	/**
	 * 是否公开模版
	 */
	private Integer is_public;
	/**
	 * 邮箱标题
	 */
	private String subject;
	/**
	 * 邮箱正文
	 */
	private String body;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date deleteTime;
	/**
	 * 排序
	 */
	private Integer orderNum;

}
