package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 群发管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-04-02 21:29:56
 */
@Data
@TableName("m4g_campaigns")
public class M4gCampaignsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 所属业务员
	 */
	private Long ownedBy;
	/**
	 * 邮箱标题
	 */
	private String subject;
	/**
	 * 邮箱正文
	 */
	private String body;
	/**
	 * 发件人
	 */
	private String fromEmail;
	/**
	 * 1 未发送 2 已定时 3 正在发送 4 发送完成 5 已取消
	 */
	private Integer status;
	private Integer tagId;
	/**
	 * 定时发送时间
	 */
	private Date sendTime;
	/**
	 * complaint 数量
	 */
	private Long complaintCount;
	/**
	 * permanent bounce 数量
	 */
	private Long permanentBounceCount;
	/**
	 * transient bounce 数量
	 */
	private Long transientBounceCount;
	/**
	 * undetermined bounce数量
	 */
	private Long undeterminedBounceCount;
	/**
	 * click through 数量
	 */
	private Long clickThroughCount;
	/**
	 * open 数量
	 */
	private Long openCount;
	/**
	 * total sent 数量
	 */
	private Long totalSentCount;

	private Long deliverCount;

	private Long rejectCount;
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
