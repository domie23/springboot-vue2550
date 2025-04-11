
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 剧本杀评价
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jubenshaCommentback")
public class JubenshaCommentbackController {
    private static final Logger logger = LoggerFactory.getLogger(JubenshaCommentbackController.class);

    @Autowired
    private JubenshaCommentbackService jubenshaCommentbackService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private JubenshaService jubenshaService;
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jubenshaCommentbackService.queryPage(params);

        //字典表数据转换
        List<JubenshaCommentbackView> list =(List<JubenshaCommentbackView>)page.getList();
        for(JubenshaCommentbackView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JubenshaCommentbackEntity jubenshaCommentback = jubenshaCommentbackService.selectById(id);
        if(jubenshaCommentback !=null){
            //entity转view
            JubenshaCommentbackView view = new JubenshaCommentbackView();
            BeanUtils.copyProperties( jubenshaCommentback , view );//把实体数据重构到view中

                //级联表
                JubenshaEntity jubensha = jubenshaService.selectById(jubenshaCommentback.getJubenshaId());
                if(jubensha != null){
                    BeanUtils.copyProperties( jubensha , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJubenshaId(jubensha.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jubenshaCommentback.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JubenshaCommentbackEntity jubenshaCommentback, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jubenshaCommentback:{}",this.getClass().getName(),jubenshaCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jubenshaCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        jubenshaCommentback.setInsertTime(new Date());
        jubenshaCommentback.setCreateTime(new Date());
        jubenshaCommentbackService.insert(jubenshaCommentback);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JubenshaCommentbackEntity jubenshaCommentback, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jubenshaCommentback:{}",this.getClass().getName(),jubenshaCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jubenshaCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JubenshaCommentbackEntity> queryWrapper = new EntityWrapper<JubenshaCommentbackEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JubenshaCommentbackEntity jubenshaCommentbackEntity = jubenshaCommentbackService.selectOne(queryWrapper);
        jubenshaCommentback.setUpdateTime(new Date());
        if(jubenshaCommentbackEntity==null){
            jubenshaCommentbackService.updateById(jubenshaCommentback);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jubenshaCommentbackService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JubenshaCommentbackEntity> jubenshaCommentbackList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JubenshaCommentbackEntity jubenshaCommentbackEntity = new JubenshaCommentbackEntity();
//                            jubenshaCommentbackEntity.setJubenshaId(Integer.valueOf(data.get(0)));   //剧本杀 要改的
//                            jubenshaCommentbackEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jubenshaCommentbackEntity.setJubenshaCommentbackPingfenNumber(Integer.valueOf(data.get(0)));   //评分 要改的
//                            jubenshaCommentbackEntity.setJubenshaCommentbackText(data.get(0));                    //评价内容 要改的
//                            jubenshaCommentbackEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            jubenshaCommentbackEntity.setInsertTime(date);//时间
//                            jubenshaCommentbackEntity.setUpdateTime(new Date(data.get(0)));          //回复时间 要改的
//                            jubenshaCommentbackEntity.setCreateTime(date);//时间
                            jubenshaCommentbackList.add(jubenshaCommentbackEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jubenshaCommentbackService.insertBatch(jubenshaCommentbackList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = jubenshaCommentbackService.queryPage(params);

        //字典表数据转换
        List<JubenshaCommentbackView> list =(List<JubenshaCommentbackView>)page.getList();
        for(JubenshaCommentbackView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JubenshaCommentbackEntity jubenshaCommentback = jubenshaCommentbackService.selectById(id);
            if(jubenshaCommentback !=null){


                //entity转view
                JubenshaCommentbackView view = new JubenshaCommentbackView();
                BeanUtils.copyProperties( jubenshaCommentback , view );//把实体数据重构到view中

                //级联表
                    JubenshaEntity jubensha = jubenshaService.selectById(jubenshaCommentback.getJubenshaId());
                if(jubensha != null){
                    BeanUtils.copyProperties( jubensha , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJubenshaId(jubensha.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jubenshaCommentback.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody JubenshaCommentbackEntity jubenshaCommentback, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jubenshaCommentback:{}",this.getClass().getName(),jubenshaCommentback.toString());
        jubenshaCommentback.setInsertTime(new Date());
        jubenshaCommentback.setCreateTime(new Date());
        jubenshaCommentbackService.insert(jubenshaCommentback);
        return R.ok();
        }


}
