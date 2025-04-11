package com.dao;

import com.entity.JubenshaCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JubenshaCommentbackView;

/**
 * 剧本杀评价 Dao 接口
 *
 * @author 
 */
public interface JubenshaCommentbackDao extends BaseMapper<JubenshaCommentbackEntity> {

   List<JubenshaCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
