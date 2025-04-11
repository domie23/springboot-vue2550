package com.dao;

import com.entity.JubenshaOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JubenshaOrderView;

/**
 * 剧本杀订单 Dao 接口
 *
 * @author 
 */
public interface JubenshaOrderDao extends BaseMapper<JubenshaOrderEntity> {

   List<JubenshaOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
