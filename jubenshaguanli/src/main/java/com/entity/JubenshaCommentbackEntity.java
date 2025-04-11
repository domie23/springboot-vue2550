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
 * 剧本杀评价
 *
 * @author 
 * @email
 */
@TableName("jubensha_commentback")
public class JubenshaCommentbackEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JubenshaCommentbackEntity() {

	}

	public JubenshaCommentbackEntity(T t) {
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
     * 剧本杀
     */
    @TableField(value = "jubensha_id")

    private Integer jubenshaId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 评分
     */
    @TableField(value = "jubensha_commentback_pingfen_number")

    private Integer jubenshaCommentbackPingfenNumber;


    /**
     * 评价内容
     */
    @TableField(value = "jubensha_commentback_text")

    private String jubenshaCommentbackText;


    /**
     * 回复内容
     */
    @TableField(value = "reply_text")

    private String replyText;


    /**
     * 评价时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


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
	 * 设置：剧本杀
	 */
    public Integer getJubenshaId() {
        return jubenshaId;
    }


    /**
	 * 获取：剧本杀
	 */

    public void setJubenshaId(Integer jubenshaId) {
        this.jubenshaId = jubenshaId;
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
	 * 设置：评分
	 */
    public Integer getJubenshaCommentbackPingfenNumber() {
        return jubenshaCommentbackPingfenNumber;
    }


    /**
	 * 获取：评分
	 */

    public void setJubenshaCommentbackPingfenNumber(Integer jubenshaCommentbackPingfenNumber) {
        this.jubenshaCommentbackPingfenNumber = jubenshaCommentbackPingfenNumber;
    }
    /**
	 * 设置：评价内容
	 */
    public String getJubenshaCommentbackText() {
        return jubenshaCommentbackText;
    }


    /**
	 * 获取：评价内容
	 */

    public void setJubenshaCommentbackText(String jubenshaCommentbackText) {
        this.jubenshaCommentbackText = jubenshaCommentbackText;
    }
    /**
	 * 设置：回复内容
	 */
    public String getReplyText() {
        return replyText;
    }


    /**
	 * 获取：回复内容
	 */

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
    /**
	 * 设置：评价时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：评价时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 获取：回复时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        return "JubenshaCommentback{" +
            "id=" + id +
            ", jubenshaId=" + jubenshaId +
            ", yonghuId=" + yonghuId +
            ", jubenshaCommentbackPingfenNumber=" + jubenshaCommentbackPingfenNumber +
            ", jubenshaCommentbackText=" + jubenshaCommentbackText +
            ", replyText=" + replyText +
            ", insertTime=" + insertTime +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
        "}";
    }
}
