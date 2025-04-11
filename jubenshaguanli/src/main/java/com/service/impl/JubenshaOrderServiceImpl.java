package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dao.JubenshaOrderDao;
import com.entity.JubenshaOrderEntity;
import com.service.JubenshaOrderService;
import com.entity.view.JubenshaOrderView;

/**
 * 剧本杀订单 服务实现类
 */
@Service("jubenshaOrderService")
@Transactional
public class JubenshaOrderServiceImpl extends ServiceImpl<JubenshaOrderDao, JubenshaOrderEntity> implements JubenshaOrderService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<JubenshaOrderView> page =new Query<JubenshaOrderView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
