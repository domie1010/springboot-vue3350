package com.entity.model;

import com.entity.ZixunshiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 咨询师预约
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZixunshiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 预约单号
     */
    private String zixunshiOrderUuidNumber;


    /**
     * 咨询师
     */
    private Integer zixunshiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yuyueTime;


    /**
     * 预约状态
     */
    private Integer zixunshiOrderYesnoTypes;


    /**
     * 审核回复
     */
    private String zixunshiOrderYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date zixunshiOrderShenheTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：预约单号
	 */
    public String getZixunshiOrderUuidNumber() {
        return zixunshiOrderUuidNumber;
    }


    /**
	 * 设置：预约单号
	 */
    public void setZixunshiOrderUuidNumber(String zixunshiOrderUuidNumber) {
        this.zixunshiOrderUuidNumber = zixunshiOrderUuidNumber;
    }
    /**
	 * 获取：咨询师
	 */
    public Integer getZixunshiId() {
        return zixunshiId;
    }


    /**
	 * 设置：咨询师
	 */
    public void setZixunshiId(Integer zixunshiId) {
        this.zixunshiId = zixunshiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getYuyueTime() {
        return yuyueTime;
    }


    /**
	 * 设置：预约时间
	 */
    public void setYuyueTime(Date yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
    /**
	 * 获取：预约状态
	 */
    public Integer getZixunshiOrderYesnoTypes() {
        return zixunshiOrderYesnoTypes;
    }


    /**
	 * 设置：预约状态
	 */
    public void setZixunshiOrderYesnoTypes(Integer zixunshiOrderYesnoTypes) {
        this.zixunshiOrderYesnoTypes = zixunshiOrderYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getZixunshiOrderYesnoText() {
        return zixunshiOrderYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setZixunshiOrderYesnoText(String zixunshiOrderYesnoText) {
        this.zixunshiOrderYesnoText = zixunshiOrderYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getZixunshiOrderShenheTime() {
        return zixunshiOrderShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setZixunshiOrderShenheTime(Date zixunshiOrderShenheTime) {
        this.zixunshiOrderShenheTime = zixunshiOrderShenheTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
