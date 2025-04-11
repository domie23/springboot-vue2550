package com.entity.model;

import com.entity.JubenshaOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 剧本杀订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JubenshaOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String jubenshaOrderUuidNumber;


    /**
     * 剧本杀
     */
    private Integer jubenshaId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yuyueTime;


    /**
     * 预约人数
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double jubenshaOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer jubenshaOrderTypes;


    /**
     * 支付类型
     */
    private Integer jubenshaOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
	 * 获取：订单号
	 */
    public String getJubenshaOrderUuidNumber() {
        return jubenshaOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setJubenshaOrderUuidNumber(String jubenshaOrderUuidNumber) {
        this.jubenshaOrderUuidNumber = jubenshaOrderUuidNumber;
    }
    /**
	 * 获取：剧本杀
	 */
    public Integer getJubenshaId() {
        return jubenshaId;
    }


    /**
	 * 设置：剧本杀
	 */
    public void setJubenshaId(Integer jubenshaId) {
        this.jubenshaId = jubenshaId;
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
	 * 获取：预约人数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：预约人数
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getJubenshaOrderTruePrice() {
        return jubenshaOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setJubenshaOrderTruePrice(Double jubenshaOrderTruePrice) {
        this.jubenshaOrderTruePrice = jubenshaOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getJubenshaOrderTypes() {
        return jubenshaOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setJubenshaOrderTypes(Integer jubenshaOrderTypes) {
        this.jubenshaOrderTypes = jubenshaOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getJubenshaOrderPaymentTypes() {
        return jubenshaOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setJubenshaOrderPaymentTypes(Integer jubenshaOrderPaymentTypes) {
        this.jubenshaOrderPaymentTypes = jubenshaOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
