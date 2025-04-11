
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
 * 剧本杀
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jubensha")
public class JubenshaController {
    private static final Logger logger = LoggerFactory.getLogger(JubenshaController.class);

    @Autowired
    private JubenshaService jubenshaService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

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
        params.put("jubenshaDeleteStart",1);params.put("jubenshaDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","jubensha_clicknum");
        }
        PageUtils page = jubenshaService.queryPage(params);

        //字典表数据转换
        List<JubenshaView> list =(List<JubenshaView>)page.getList();
        for(JubenshaView c:list){
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
        JubenshaEntity jubensha = jubenshaService.selectById(id);
        if(jubensha !=null){
            //entity转view
            JubenshaView view = new JubenshaView();
            BeanUtils.copyProperties( jubensha , view );//把实体数据重构到view中

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
    public R save(@RequestBody JubenshaEntity jubensha, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jubensha:{}",this.getClass().getName(),jubensha.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JubenshaEntity> queryWrapper = new EntityWrapper<JubenshaEntity>()
            .eq("jubensha_name", jubensha.getJubenshaName())
            .eq("jubensha_kaishishijian", jubensha.getJubenshaKaishishijian())
            .eq("jubensha_shichang", jubensha.getJubenshaShichang())
            .eq("jubensha_types", jubensha.getJubenshaTypes())
            .eq("jubensha_kucun_number", jubensha.getJubenshaKucunNumber())
            .eq("jubensha_price", jubensha.getJubenshaPrice())
            .eq("jubensha_clicknum", jubensha.getJubenshaClicknum())
            .eq("shangxia_types", jubensha.getShangxiaTypes())
            .eq("jubensha_delete", jubensha.getJubenshaDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JubenshaEntity jubenshaEntity = jubenshaService.selectOne(queryWrapper);
        if(jubenshaEntity==null){
            jubensha.setJubenshaClicknum(1);
            jubensha.setShangxiaTypes(1);
            jubensha.setJubenshaDelete(1);
            jubensha.setCreateTime(new Date());
            jubenshaService.insert(jubensha);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JubenshaEntity jubensha, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jubensha:{}",this.getClass().getName(),jubensha.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<JubenshaEntity> queryWrapper = new EntityWrapper<JubenshaEntity>()
            .notIn("id",jubensha.getId())
            .andNew()
            .eq("jubensha_name", jubensha.getJubenshaName())
            .eq("jubensha_kaishishijian", jubensha.getJubenshaKaishishijian())
            .eq("jubensha_shichang", jubensha.getJubenshaShichang())
            .eq("jubensha_types", jubensha.getJubenshaTypes())
            .eq("jubensha_kucun_number", jubensha.getJubenshaKucunNumber())
            .eq("jubensha_price", jubensha.getJubenshaPrice())
            .eq("jubensha_clicknum", jubensha.getJubenshaClicknum())
            .eq("shangxia_types", jubensha.getShangxiaTypes())
            .eq("jubensha_delete", jubensha.getJubenshaDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JubenshaEntity jubenshaEntity = jubenshaService.selectOne(queryWrapper);
        if("".equals(jubensha.getJubenshaPhoto()) || "null".equals(jubensha.getJubenshaPhoto())){
                jubensha.setJubenshaPhoto(null);
        }
        if(jubenshaEntity==null){
            jubenshaService.updateById(jubensha);//根据id更新
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
        ArrayList<JubenshaEntity> list = new ArrayList<>();
        for(Integer id:ids){
            JubenshaEntity jubenshaEntity = new JubenshaEntity();
            jubenshaEntity.setId(id);
            jubenshaEntity.setJubenshaDelete(2);
            list.add(jubenshaEntity);
        }
        if(list != null && list.size() >0){
            jubenshaService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JubenshaEntity> jubenshaList = new ArrayList<>();//上传的东西
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
                            JubenshaEntity jubenshaEntity = new JubenshaEntity();
//                            jubenshaEntity.setJubenshaName(data.get(0));                    //剧本杀名称 要改的
//                            jubenshaEntity.setJubenshaPhoto("");//照片
//                            jubenshaEntity.setJubenshaKaishishijian(data.get(0));                    //剧本杀开始时间 要改的
//                            jubenshaEntity.setJubenshaShichang(data.get(0));                    //预估时长 要改的
//                            jubenshaEntity.setJubenshaTypes(Integer.valueOf(data.get(0)));   //剧本杀分类 要改的
//                            jubenshaEntity.setJubenshaKucunNumber(Integer.valueOf(data.get(0)));   //最大参与人数 要改的
//                            jubenshaEntity.setJubenshaPrice(Integer.valueOf(data.get(0)));   //购买获得积分 要改的
//                            jubenshaEntity.setJubenshaOldMoney(data.get(0));                    //剧本杀原价 要改的
//                            jubenshaEntity.setJubenshaNewMoney(data.get(0));                    //现价 要改的
//                            jubenshaEntity.setJubenshaClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            jubenshaEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            jubenshaEntity.setJubenshaDelete(1);//逻辑删除字段
//                            jubenshaEntity.setJubenshaContent("");//照片
//                            jubenshaEntity.setCreateTime(date);//时间
                            jubenshaList.add(jubenshaEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jubenshaService.insertBatch(jubenshaList);
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
            params.put("orderBy","jubensha_clicknum");
        }
        PageUtils page = jubenshaService.queryPage(params);

        //字典表数据转换
        List<JubenshaView> list =(List<JubenshaView>)page.getList();
        for(JubenshaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JubenshaEntity jubensha = jubenshaService.selectById(id);
            if(jubensha !=null){

                //点击数量加1
                jubensha.setJubenshaClicknum(jubensha.getJubenshaClicknum()+1);
                jubenshaService.updateById(jubensha);

                //entity转view
                JubenshaView view = new JubenshaView();
                BeanUtils.copyProperties( jubensha , view );//把实体数据重构到view中

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
    public R add(@RequestBody JubenshaEntity jubensha, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jubensha:{}",this.getClass().getName(),jubensha.toString());
        Wrapper<JubenshaEntity> queryWrapper = new EntityWrapper<JubenshaEntity>()
            .eq("jubensha_name", jubensha.getJubenshaName())
            .eq("jubensha_kaishishijian", jubensha.getJubenshaKaishishijian())
            .eq("jubensha_shichang", jubensha.getJubenshaShichang())
            .eq("jubensha_types", jubensha.getJubenshaTypes())
            .eq("jubensha_kucun_number", jubensha.getJubenshaKucunNumber())
            .eq("jubensha_price", jubensha.getJubenshaPrice())
            .eq("jubensha_clicknum", jubensha.getJubenshaClicknum())
            .eq("shangxia_types", jubensha.getShangxiaTypes())
            .eq("jubensha_delete", jubensha.getJubenshaDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JubenshaEntity jubenshaEntity = jubenshaService.selectOne(queryWrapper);
        if(jubenshaEntity==null){
            jubensha.setJubenshaDelete(1);
            jubensha.setCreateTime(new Date());
        jubenshaService.insert(jubensha);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
