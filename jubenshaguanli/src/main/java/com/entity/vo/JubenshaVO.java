package com.entity.vo;

import com.entity.JubenshaEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 剧本杀
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jubensha")
public class JubenshaVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 剧本杀名称
     */

    @TableField(value = "jubensha_name")
    private String jubenshaName;


    /**
     * 剧本杀照片
     */

    @TableField(value = "jubensha_photo")
    private String jubenshaPhoto;


    /**
     * 剧本杀开始时间
     */

    @TableField(value = "jubensha_kaishishijian")
    private String jubenshaKaishishijian;


    /**
     * 预估时长
     */

    @TableField(value = "jubensha_shichang")
    private String jubenshaShichang;


    /**
     * 剧本杀分类
     */

    @TableField(value = "jubensha_types")
    private Integer jubenshaTypes;


    /**
     * 最大参与人数
     */

    @TableField(value = "jubensha_kucun_number")
    private Integer jubenshaKucunNumber;


    /**
     * 购买获得积分
     */

    @TableField(value = "jubensha_price")
    private Integer jubenshaPrice;


    /**
     * 剧本杀原价
     */

    @TableField(value = "jubensha_old_money")
    private Double jubenshaOldMoney;


    /**
     * 现价
     */

    @TableField(value = "jubensha_new_money")
    private Double jubenshaNewMoney;


    /**
     * 点击次数
     */

    @TableField(value = "jubensha_clicknum")
    private Integer jubenshaClicknum;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "jubensha_delete")
    private Integer jubenshaDelete;


    /**
     * 剧本杀介绍
     */

    @TableField(value = "jubensha_content")
    private String jubenshaContent;


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
	 * 设置：剧本杀名称
	 */
    public String getJubenshaName() {
        return jubenshaName;
    }


    /**
	 * 获取：剧本杀名称
	 */

    public void setJubenshaName(String jubenshaName) {
        this.jubenshaName = jubenshaName;
    }
    /**
	 * 设置：剧本杀照片
	 */
    public String getJubenshaPhoto() {
        return jubenshaPhoto;
    }


    /**
	 * 获取：剧本杀照片
	 */

    public void setJubenshaPhoto(String jubenshaPhoto) {
        this.jubenshaPhoto = jubenshaPhoto;
    }
    /**
	 * 设置：剧本杀开始时间
	 */
    public String getJubenshaKaishishijian() {
        return jubenshaKaishishijian;
    }


    /**
	 * 获取：剧本杀开始时间
	 */

    public void setJubenshaKaishishijian(String jubenshaKaishishijian) {
        this.jubenshaKaishishijian = jubenshaKaishishijian;
    }
    /**
	 * 设置：预估时长
	 */
    public String getJubenshaShichang() {
        return jubenshaShichang;
    }


    /**
	 * 获取：预估时长
	 */

    public void setJubenshaShichang(String jubenshaShichang) {
        this.jubenshaShichang = jubenshaShichang;
    }
    /**
	 * 设置：剧本杀分类
	 */
    public Integer getJubenshaTypes() {
        return jubenshaTypes;
    }


    /**
	 * 获取：剧本杀分类
	 */

    public void setJubenshaTypes(Integer jubenshaTypes) {
        this.jubenshaTypes = jubenshaTypes;
    }
    /**
	 * 设置：最大参与人数
	 */
    public Integer getJubenshaKucunNumber() {
        return jubenshaKucunNumber;
    }


    /**
	 * 获取：最大参与人数
	 */

    public void setJubenshaKucunNumber(Integer jubenshaKucunNumber) {
        this.jubenshaKucunNumber = jubenshaKucunNumber;
    }
    /**
	 * 设置：购买获得积分
	 */
    public Integer getJubenshaPrice() {
        return jubenshaPrice;
    }


    /**
	 * 获取：购买获得积分
	 */

    public void setJubenshaPrice(Integer jubenshaPrice) {
        this.jubenshaPrice = jubenshaPrice;
    }
    /**
	 * 设置：剧本杀原价
	 */
    public Double getJubenshaOldMoney() {
        return jubenshaOldMoney;
    }


    /**
	 * 获取：剧本杀原价
	 */

    public void setJubenshaOldMoney(Double jubenshaOldMoney) {
        this.jubenshaOldMoney = jubenshaOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getJubenshaNewMoney() {
        return jubenshaNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setJubenshaNewMoney(Double jubenshaNewMoney) {
        this.jubenshaNewMoney = jubenshaNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getJubenshaClicknum() {
        return jubenshaClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setJubenshaClicknum(Integer jubenshaClicknum) {
        this.jubenshaClicknum = jubenshaClicknum;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getJubenshaDelete() {
        return jubenshaDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJubenshaDelete(Integer jubenshaDelete) {
        this.jubenshaDelete = jubenshaDelete;
    }
    /**
	 * 设置：剧本杀介绍
	 */
    public String getJubenshaContent() {
        return jubenshaContent;
    }


    /**
	 * 获取：剧本杀介绍
	 */

    public void setJubenshaContent(String jubenshaContent) {
        this.jubenshaContent = jubenshaContent;
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
