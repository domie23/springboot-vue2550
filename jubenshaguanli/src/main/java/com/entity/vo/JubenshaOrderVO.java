package com.entity.vo;

import com.entity.JubenshaOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 剧本杀订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jubensha_order")
public class JubenshaOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "jubensha_order_uuid_number")
    private String jubenshaOrderUuidNumber;


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
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "yuyue_time")
    private Date yuyueTime;


    /**
     * 预约人数
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "jubensha_order_true_price")
    private Double jubenshaOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "jubensha_order_types")
    private Integer jubenshaOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "jubensha_order_payment_types")
    private Integer jubenshaOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
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
	 * 设置：订单号
	 */
    public String getJubenshaOrderUuidNumber() {
        return jubenshaOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setJubenshaOrderUuidNumber(String jubenshaOrderUuidNumber) {
        this.jubenshaOrderUuidNumber = jubenshaOrderUuidNumber;
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
	 * 设置：预约人数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：预约人数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getJubenshaOrderTruePrice() {
        return jubenshaOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setJubenshaOrderTruePrice(Double jubenshaOrderTruePrice) {
        this.jubenshaOrderTruePrice = jubenshaOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getJubenshaOrderTypes() {
        return jubenshaOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setJubenshaOrderTypes(Integer jubenshaOrderTypes) {
        this.jubenshaOrderTypes = jubenshaOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getJubenshaOrderPaymentTypes() {
        return jubenshaOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setJubenshaOrderPaymentTypes(Integer jubenshaOrderPaymentTypes) {
        this.jubenshaOrderPaymentTypes = jubenshaOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
