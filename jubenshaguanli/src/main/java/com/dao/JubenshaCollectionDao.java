package com.dao;

import com.entity.JubenshaCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JubenshaCollectionView;

/**
 * 剧本杀收藏 Dao 接口
 *
 * @author 
 */
public interface JubenshaCollectionDao extends BaseMapper<JubenshaCollectionEntity> {

   List<JubenshaCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
