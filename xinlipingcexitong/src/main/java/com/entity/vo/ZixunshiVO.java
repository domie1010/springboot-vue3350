package com.entity.vo;

import com.entity.ZixunshiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 咨询师
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zixunshi")
public class ZixunshiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 咨询师姓名
     */

    @TableField(value = "zixunshi_name")
    private String zixunshiName;


    /**
     * 咨询师手机号
     */

    @TableField(value = "zixunshi_phone")
    private String zixunshiPhone;


    /**
     * 咨询师身份证号
     */

    @TableField(value = "zixunshi_id_number")
    private String zixunshiIdNumber;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 电子邮箱
     */

    @TableField(value = "zixunshi_email")
    private String zixunshiEmail;


    /**
     * 咨询师头像
     */

    @TableField(value = "zixunshi_photo")
    private String zixunshiPhoto;


    /**
     * 从业时长
     */

    @TableField(value = "zixunshi_congye")
    private String zixunshiCongye;


    /**
     * 擅长
     */

    @TableField(value = "zixunshi_shanchang")
    private String zixunshiShanchang;


    /**
     * 咨询师详细介绍
     */

    @TableField(value = "zixunshi_content")
    private String zixunshiContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：咨询师姓名
	 */
    public String getZixunshiName() {
        return zixunshiName;
    }


    /**
	 * 获取：咨询师姓名
	 */

    public void setZixunshiName(String zixunshiName) {
        this.zixunshiName = zixunshiName;
    }
    /**
	 * 设置：咨询师手机号
	 */
    public String getZixunshiPhone() {
        return zixunshiPhone;
    }


    /**
	 * 获取：咨询师手机号
	 */

    public void setZixunshiPhone(String zixunshiPhone) {
        this.zixunshiPhone = zixunshiPhone;
    }
    /**
	 * 设置：咨询师身份证号
	 */
    public String getZixunshiIdNumber() {
        return zixunshiIdNumber;
    }


    /**
	 * 获取：咨询师身份证号
	 */

    public void setZixunshiIdNumber(String zixunshiIdNumber) {
        this.zixunshiIdNumber = zixunshiIdNumber;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getZixunshiEmail() {
        return zixunshiEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setZixunshiEmail(String zixunshiEmail) {
        this.zixunshiEmail = zixunshiEmail;
    }
    /**
	 * 设置：咨询师头像
	 */
    public String getZixunshiPhoto() {
        return zixunshiPhoto;
    }


    /**
	 * 获取：咨询师头像
	 */

    public void setZixunshiPhoto(String zixunshiPhoto) {
        this.zixunshiPhoto = zixunshiPhoto;
    }
    /**
	 * 设置：从业时长
	 */
    public String getZixunshiCongye() {
        return zixunshiCongye;
    }


    /**
	 * 获取：从业时长
	 */

    public void setZixunshiCongye(String zixunshiCongye) {
        this.zixunshiCongye = zixunshiCongye;
    }
    /**
	 * 设置：擅长
	 */
    public String getZixunshiShanchang() {
        return zixunshiShanchang;
    }


    /**
	 * 获取：擅长
	 */

    public void setZixunshiShanchang(String zixunshiShanchang) {
        this.zixunshiShanchang = zixunshiShanchang;
    }
    /**
	 * 设置：咨询师详细介绍
	 */
    public String getZixunshiContent() {
        return zixunshiContent;
    }


    /**
	 * 获取：咨询师详细介绍
	 */

    public void setZixunshiContent(String zixunshiContent) {
        this.zixunshiContent = zixunshiContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
