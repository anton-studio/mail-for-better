package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 群发邮件统计
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Data
@TableName("m4g_campaign_emails")
public class M4gCampaignEmailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 活动ID
	 */
	private Long campaignId;
	/**
	 * 邮箱ID
	 */
	private Long emailId;
	/**
	 * 首次打开邮件时间
	 */
	private Date firstOpen;
	/**
	 * tracking ID
	 */
	private Long trackingId;
	/**
	 * 邮件发送时间
	 */
	private Date sendTime;
	/**
	 * 
	 */
	private Date createTime;

}
