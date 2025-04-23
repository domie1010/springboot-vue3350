package com.entity.model;

import com.entity.ZixunshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 咨询师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZixunshiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 咨询师姓名
     */
    private String zixunshiName;


    /**
     * 咨询师手机号
     */
    private String zixunshiPhone;


    /**
     * 咨询师身份证号
     */
    private String zixunshiIdNumber;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String zixunshiEmail;


    /**
     * 咨询师头像
     */
    private String zixunshiPhoto;


    /**
     * 从业时长
     */
    private String zixunshiCongye;


    /**
     * 擅长
     */
    private String zixunshiShanchang;


    /**
     * 咨询师详细介绍
     */
    private String zixunshiContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：咨询师姓名
	 */
    public String getZixunshiName() {
        return zixunshiName;
    }


    /**
	 * 设置：咨询师姓名
	 */
    public void setZixunshiName(String zixunshiName) {
        this.zixunshiName = zixunshiName;
    }
    /**
	 * 获取：咨询师手机号
	 */
    public String getZixunshiPhone() {
        return zixunshiPhone;
    }


    /**
	 * 设置：咨询师手机号
	 */
    public void setZixunshiPhone(String zixunshiPhone) {
        this.zixunshiPhone = zixunshiPhone;
    }
    /**
	 * 获取：咨询师身份证号
	 */
    public String getZixunshiIdNumber() {
        return zixunshiIdNumber;
    }


    /**
	 * 设置：咨询师身份证号
	 */
    public void setZixunshiIdNumber(String zixunshiIdNumber) {
        this.zixunshiIdNumber = zixunshiIdNumber;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getZixunshiEmail() {
        return zixunshiEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setZixunshiEmail(String zixunshiEmail) {
        this.zixunshiEmail = zixunshiEmail;
    }
    /**
	 * 获取：咨询师头像
	 */
    public String getZixunshiPhoto() {
        return zixunshiPhoto;
    }


    /**
	 * 设置：咨询师头像
	 */
    public void setZixunshiPhoto(String zixunshiPhoto) {
        this.zixunshiPhoto = zixunshiPhoto;
    }
    /**
	 * 获取：从业时长
	 */
    public String getZixunshiCongye() {
        return zixunshiCongye;
    }


    /**
	 * 设置：从业时长
	 */
    public void setZixunshiCongye(String zixunshiCongye) {
        this.zixunshiCongye = zixunshiCongye;
    }
    /**
	 * 获取：擅长
	 */
    public String getZixunshiShanchang() {
        return zixunshiShanchang;
    }


    /**
	 * 设置：擅长
	 */
    public void setZixunshiShanchang(String zixunshiShanchang) {
        this.zixunshiShanchang = zixunshiShanchang;
    }
    /**
	 * 获取：咨询师详细介绍
	 */
    public String getZixunshiContent() {
        return zixunshiContent;
    }


    /**
	 * 设置：咨询师详细介绍
	 */
    public void setZixunshiContent(String zixunshiContent) {
        this.zixunshiContent = zixunshiContent;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
