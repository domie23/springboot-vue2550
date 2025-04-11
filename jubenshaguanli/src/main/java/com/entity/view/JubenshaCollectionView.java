package com.entity.view;

import com.entity.JubenshaCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 剧本杀收藏
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jubensha_collection")
public class JubenshaCollectionView extends JubenshaCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 类型的值
		*/
		private String jubenshaCollectionValue;



		//级联表 jubensha
			/**
			* 剧本杀名称
			*/
			private String jubenshaName;
			/**
			* 剧本杀照片
			*/
			private String jubenshaPhoto;
			/**
			* 剧本杀开始时间
			*/
			private String jubenshaKaishishijian;
			/**
			* 预估时长
			*/
			private String jubenshaShichang;
			/**
			* 剧本杀分类
			*/
			private Integer jubenshaTypes;
				/**
				* 剧本杀分类的值
				*/
				private String jubenshaValue;
			/**
			* 最大参与人数
			*/
			private Integer jubenshaKucunNumber;
			/**
			* 购买获得积分
			*/
			private Integer jubenshaPrice;
			/**
			* 剧本杀原价
			*/
			private Double jubenshaOldMoney;
			/**
			* 现价
			*/
			private Double jubenshaNewMoney;
			/**
			* 点击次数
			*/
			private Integer jubenshaClicknum;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer jubenshaDelete;
			/**
			* 剧本杀介绍
			*/
			private String jubenshaContent;

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
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 总积分
			*/
			private Double yonghuSumJifen;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;
			/**
			* 会员等级
			*/
			private Integer huiyuandengjiTypes;
				/**
				* 会员等级的值
				*/
				private String huiyuandengjiValue;

	public JubenshaCollectionView() {

	}

	public JubenshaCollectionView(JubenshaCollectionEntity jubenshaCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, jubenshaCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 类型的值
			*/
			public String getJubenshaCollectionValue() {
				return jubenshaCollectionValue;
			}
			/**
			* 设置： 类型的值
			*/
			public void setJubenshaCollectionValue(String jubenshaCollectionValue) {
				this.jubenshaCollectionValue = jubenshaCollectionValue;
			}













				//级联表的get和set jubensha
					/**
					* 获取： 剧本杀名称
					*/
					public String getJubenshaName() {
						return jubenshaName;
					}
					/**
					* 设置： 剧本杀名称
					*/
					public void setJubenshaName(String jubenshaName) {
						this.jubenshaName = jubenshaName;
					}
					/**
					* 获取： 剧本杀照片
					*/
					public String getJubenshaPhoto() {
						return jubenshaPhoto;
					}
					/**
					* 设置： 剧本杀照片
					*/
					public void setJubenshaPhoto(String jubenshaPhoto) {
						this.jubenshaPhoto = jubenshaPhoto;
					}
					/**
					* 获取： 剧本杀开始时间
					*/
					public String getJubenshaKaishishijian() {
						return jubenshaKaishishijian;
					}
					/**
					* 设置： 剧本杀开始时间
					*/
					public void setJubenshaKaishishijian(String jubenshaKaishishijian) {
						this.jubenshaKaishishijian = jubenshaKaishishijian;
					}
					/**
					* 获取： 预估时长
					*/
					public String getJubenshaShichang() {
						return jubenshaShichang;
					}
					/**
					* 设置： 预估时长
					*/
					public void setJubenshaShichang(String jubenshaShichang) {
						this.jubenshaShichang = jubenshaShichang;
					}
					/**
					* 获取： 剧本杀分类
					*/
					public Integer getJubenshaTypes() {
						return jubenshaTypes;
					}
					/**
					* 设置： 剧本杀分类
					*/
					public void setJubenshaTypes(Integer jubenshaTypes) {
						this.jubenshaTypes = jubenshaTypes;
					}


						/**
						* 获取： 剧本杀分类的值
						*/
						public String getJubenshaValue() {
							return jubenshaValue;
						}
						/**
						* 设置： 剧本杀分类的值
						*/
						public void setJubenshaValue(String jubenshaValue) {
							this.jubenshaValue = jubenshaValue;
						}
					/**
					* 获取： 最大参与人数
					*/
					public Integer getJubenshaKucunNumber() {
						return jubenshaKucunNumber;
					}
					/**
					* 设置： 最大参与人数
					*/
					public void setJubenshaKucunNumber(Integer jubenshaKucunNumber) {
						this.jubenshaKucunNumber = jubenshaKucunNumber;
					}
					/**
					* 获取： 购买获得积分
					*/
					public Integer getJubenshaPrice() {
						return jubenshaPrice;
					}
					/**
					* 设置： 购买获得积分
					*/
					public void setJubenshaPrice(Integer jubenshaPrice) {
						this.jubenshaPrice = jubenshaPrice;
					}
					/**
					* 获取： 剧本杀原价
					*/
					public Double getJubenshaOldMoney() {
						return jubenshaOldMoney;
					}
					/**
					* 设置： 剧本杀原价
					*/
					public void setJubenshaOldMoney(Double jubenshaOldMoney) {
						this.jubenshaOldMoney = jubenshaOldMoney;
					}
					/**
					* 获取： 现价
					*/
					public Double getJubenshaNewMoney() {
						return jubenshaNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setJubenshaNewMoney(Double jubenshaNewMoney) {
						this.jubenshaNewMoney = jubenshaNewMoney;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getJubenshaClicknum() {
						return jubenshaClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setJubenshaClicknum(Integer jubenshaClicknum) {
						this.jubenshaClicknum = jubenshaClicknum;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getJubenshaDelete() {
						return jubenshaDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setJubenshaDelete(Integer jubenshaDelete) {
						this.jubenshaDelete = jubenshaDelete;
					}
					/**
					* 获取： 剧本杀介绍
					*/
					public String getJubenshaContent() {
						return jubenshaContent;
					}
					/**
					* 设置： 剧本杀介绍
					*/
					public void setJubenshaContent(String jubenshaContent) {
						this.jubenshaContent = jubenshaContent;
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
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}
					/**
					* 获取： 总积分
					*/
					public Double getYonghuSumJifen() {
						return yonghuSumJifen;
					}
					/**
					* 设置： 总积分
					*/
					public void setYonghuSumJifen(Double yonghuSumJifen) {
						this.yonghuSumJifen = yonghuSumJifen;
					}
					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}
					/**
					* 获取： 会员等级
					*/
					public Integer getHuiyuandengjiTypes() {
						return huiyuandengjiTypes;
					}
					/**
					* 设置： 会员等级
					*/
					public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
						this.huiyuandengjiTypes = huiyuandengjiTypes;
					}


						/**
						* 获取： 会员等级的值
						*/
						public String getHuiyuandengjiValue() {
							return huiyuandengjiValue;
						}
						/**
						* 设置： 会员等级的值
						*/
						public void setHuiyuandengjiValue(String huiyuandengjiValue) {
							this.huiyuandengjiValue = huiyuandengjiValue;
						}



}
