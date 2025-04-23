package com.dao;

import com.entity.ZixunshiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZixunshiCollectionView;

/**
 * 咨询师收藏 Dao 接口
 *
 * @author 
 */
public interface ZixunshiCollectionDao extends BaseMapper<ZixunshiCollectionEntity> {

   List<ZixunshiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
