package com.dao;

import com.entity.JubenshaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JubenshaView;

/**
 * 剧本杀 Dao 接口
 *
 * @author 
 */
public interface JubenshaDao extends BaseMapper<JubenshaEntity> {

   List<JubenshaView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
