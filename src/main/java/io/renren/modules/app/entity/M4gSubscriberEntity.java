package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 邮箱管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Data
@TableName("m4g_subscriber")
public class M4gSubscriberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 所属业务员
	 */
	private Long ownedBy;
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

	private Boolean isBounce;
	private Boolean isComplaint;
	private Boolean isReject;
	private Long tagId;
	private Boolean isValid;
}
