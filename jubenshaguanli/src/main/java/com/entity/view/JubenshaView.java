package com.entity.view;

import com.entity.JubenshaEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 剧本杀
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jubensha")
public class JubenshaView extends JubenshaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 剧本杀分类的值
		*/
		private String jubenshaValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public JubenshaView() {

	}

	public JubenshaView(JubenshaEntity jubenshaEntity) {
		try {
			BeanUtils.copyProperties(this, jubenshaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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












}
