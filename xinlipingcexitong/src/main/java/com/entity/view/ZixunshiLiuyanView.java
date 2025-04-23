package com.entity.view;

import com.entity.ZixunshiLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 咨询师留言
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("zixunshi_liuyan")
public class ZixunshiLiuyanView extends ZixunshiLiuyanEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

		//级联表 zixunshi
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

	public ZixunshiLiuyanView() {

	}

	public ZixunshiLiuyanView(ZixunshiLiuyanEntity zixunshiLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, zixunshiLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





























				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}


				//级联表的get和set zixunshi

					/**
					* 获取： 咨询师姓名
					*/
					public String getZixunshiName() {
						return zixunshiName;
					}
					/**
					* 设置： 咨询师姓名
					*/
					public void setZixunshiName(String zixunshiName) {
						this.zixunshiName = zixunshiName;
					}

					/**
					* 获取： 咨询师手机号
					*/
					public String getZixunshiPhone() {
						return zixunshiPhone;
					}
					/**
					* 设置： 咨询师手机号
					*/
					public void setZixunshiPhone(String zixunshiPhone) {
						this.zixunshiPhone = zixunshiPhone;
					}

					/**
					* 获取： 咨询师身份证号
					*/
					public String getZixunshiIdNumber() {
						return zixunshiIdNumber;
					}
					/**
					* 设置： 咨询师身份证号
					*/
					public void setZixunshiIdNumber(String zixunshiIdNumber) {
						this.zixunshiIdNumber = zixunshiIdNumber;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getZixunshiEmail() {
						return zixunshiEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setZixunshiEmail(String zixunshiEmail) {
						this.zixunshiEmail = zixunshiEmail;
					}

					/**
					* 获取： 咨询师头像
					*/
					public String getZixunshiPhoto() {
						return zixunshiPhoto;
					}
					/**
					* 设置： 咨询师头像
					*/
					public void setZixunshiPhoto(String zixunshiPhoto) {
						this.zixunshiPhoto = zixunshiPhoto;
					}

					/**
					* 获取： 从业时长
					*/
					public String getZixunshiCongye() {
						return zixunshiCongye;
					}
					/**
					* 设置： 从业时长
					*/
					public void setZixunshiCongye(String zixunshiCongye) {
						this.zixunshiCongye = zixunshiCongye;
					}

					/**
					* 获取： 擅长
					*/
					public String getZixunshiShanchang() {
						return zixunshiShanchang;
					}
					/**
					* 设置： 擅长
					*/
					public void setZixunshiShanchang(String zixunshiShanchang) {
						this.zixunshiShanchang = zixunshiShanchang;
					}

					/**
					* 获取： 咨询师详细介绍
					*/
					public String getZixunshiContent() {
						return zixunshiContent;
					}
					/**
					* 设置： 咨询师详细介绍
					*/
					public void setZixunshiContent(String zixunshiContent) {
						this.zixunshiContent = zixunshiContent;
					}













}
