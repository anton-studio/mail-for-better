package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 群发打开历史统计
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Data
@TableName("m4g_campaign_open_tracking")
public class M4gCampaignOpenTrackingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * tracking ID
	 */
	private Long trackingId;
	/**
	 * 首次打开邮件时间
	 */
	private Date createTime;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 区域
	 */
	private String region;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * IP 地址
	 */
	private String ipAddress;
	/**
	 * 操作系统
	 */
	private String operatingSystem;
	/**
	 * 设备类型
	 */
	private String deviceType;
	/**
	 * 设备厂商
	 */
	private String deviceVendor;
	/**
	 * 浏览器名字
	 */
	private String browserName;

}
