package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 咨询师预约
 *
 * @author 
 * @email
 */
@TableName("zixunshi_order")
public class ZixunshiOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZixunshiOrderEntity() {

	}

	public ZixunshiOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 预约单号
     */
    @TableField(value = "zixunshi_order_uuid_number")

    private String zixunshiOrderUuidNumber;


    /**
     * 咨询师
     */
    @TableField(value = "zixunshi_id")

    private Integer zixunshiId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "yuyue_time")

    private Date yuyueTime;


    /**
     * 预约状态
     */
    @TableField(value = "zixunshi_order_yesno_types")

    private Integer zixunshiOrderYesnoTypes;


    /**
     * 审核回复
     */
    @TableField(value = "zixunshi_order_yesno_text")

    private String zixunshiOrderYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "zixunshi_order_shenhe_time")

    private Date zixunshiOrderShenheTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：预约单号
	 */
    public String getZixunshiOrderUuidNumber() {
        return zixunshiOrderUuidNumber;
    }
    /**
	 * 获取：预约单号
	 */

    public void setZixunshiOrderUuidNumber(String zixunshiOrderUuidNumber) {
        this.zixunshiOrderUuidNumber = zixunshiOrderUuidNumber;
    }
    /**
	 * 设置：咨询师
	 */
    public Integer getZixunshiId() {
        return zixunshiId;
    }
    /**
	 * 获取：咨询师
	 */

    public void setZixunshiId(Integer zixunshiId) {
        this.zixunshiId = zixunshiId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：预约时间
	 */
    public Date getYuyueTime() {
        return yuyueTime;
    }
    /**
	 * 获取：预约时间
	 */

    public void setYuyueTime(Date yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
    /**
	 * 设置：预约状态
	 */
    public Integer getZixunshiOrderYesnoTypes() {
        return zixunshiOrderYesnoTypes;
    }
    /**
	 * 获取：预约状态
	 */

    public void setZixunshiOrderYesnoTypes(Integer zixunshiOrderYesnoTypes) {
        this.zixunshiOrderYesnoTypes = zixunshiOrderYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getZixunshiOrderYesnoText() {
        return zixunshiOrderYesnoText;
    }
    /**
	 * 获取：审核回复
	 */

    public void setZixunshiOrderYesnoText(String zixunshiOrderYesnoText) {
        this.zixunshiOrderYesnoText = zixunshiOrderYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getZixunshiOrderShenheTime() {
        return zixunshiOrderShenheTime;
    }
    /**
	 * 获取：审核时间
	 */

    public void setZixunshiOrderShenheTime(Date zixunshiOrderShenheTime) {
        this.zixunshiOrderShenheTime = zixunshiOrderShenheTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ZixunshiOrder{" +
            "id=" + id +
            ", zixunshiOrderUuidNumber=" + zixunshiOrderUuidNumber +
            ", zixunshiId=" + zixunshiId +
            ", yonghuId=" + yonghuId +
            ", insertTime=" + insertTime +
            ", yuyueTime=" + yuyueTime +
            ", zixunshiOrderYesnoTypes=" + zixunshiOrderYesnoTypes +
            ", zixunshiOrderYesnoText=" + zixunshiOrderYesnoText +
            ", zixunshiOrderShenheTime=" + zixunshiOrderShenheTime +
            ", createTime=" + createTime +
        "}";
    }
}
