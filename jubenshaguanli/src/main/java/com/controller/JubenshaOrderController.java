
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
 * 剧本杀订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jubenshaOrder")
public class JubenshaOrderController {
    private static final Logger logger = LoggerFactory.getLogger(JubenshaOrderController.class);

    @Autowired
    private JubenshaOrderService jubenshaOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private JubenshaService jubenshaService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private JubenshaCommentbackService jubenshaCommentbackService;



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
        PageUtils page = jubenshaOrderService.queryPage(params);

        //字典表数据转换
        List<JubenshaOrderView> list =(List<JubenshaOrderView>)page.getList();
        for(JubenshaOrderView c:list){
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
        JubenshaOrderEntity jubenshaOrder = jubenshaOrderService.selectById(id);
        if(jubenshaOrder !=null){
            //entity转view
            JubenshaOrderView view = new JubenshaOrderView();
            BeanUtils.copyProperties( jubenshaOrder , view );//把实体数据重构到view中

                //级联表
                JubenshaEntity jubensha = jubenshaService.selectById(jubenshaOrder.getJubenshaId());
                if(jubensha != null){
                    BeanUtils.copyProperties( jubensha , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJubenshaId(jubensha.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jubenshaOrder.getYonghuId());
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
    public R save(@RequestBody JubenshaOrderEntity jubenshaOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jubenshaOrder:{}",this.getClass().getName(),jubenshaOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jubenshaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        jubenshaOrder.setInsertTime(new Date());
        jubenshaOrder.setCreateTime(new Date());
        jubenshaOrderService.insert(jubenshaOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JubenshaOrderEntity jubenshaOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jubenshaOrder:{}",this.getClass().getName(),jubenshaOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jubenshaOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JubenshaOrderEntity> queryWrapper = new EntityWrapper<JubenshaOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JubenshaOrderEntity jubenshaOrderEntity = jubenshaOrderService.selectOne(queryWrapper);
        if(jubenshaOrderEntity==null){
            jubenshaOrderService.updateById(jubenshaOrder);//根据id更新
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
        jubenshaOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JubenshaOrderEntity> jubenshaOrderList = new ArrayList<>();//上传的东西
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
                            JubenshaOrderEntity jubenshaOrderEntity = new JubenshaOrderEntity();
//                            jubenshaOrderEntity.setJubenshaOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            jubenshaOrderEntity.setJubenshaId(Integer.valueOf(data.get(0)));   //剧本杀 要改的
//                            jubenshaOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jubenshaOrderEntity.setYuyueTime(new Date(data.get(0)));          //预约时间 要改的
//                            jubenshaOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //预约人数 要改的
//                            jubenshaOrderEntity.setJubenshaOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            jubenshaOrderEntity.setJubenshaOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            jubenshaOrderEntity.setJubenshaOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            jubenshaOrderEntity.setInsertTime(date);//时间
//                            jubenshaOrderEntity.setCreateTime(date);//时间
                            jubenshaOrderList.add(jubenshaOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("jubenshaOrderUuidNumber")){
                                    List<String> jubenshaOrderUuidNumber = seachFields.get("jubenshaOrderUuidNumber");
                                    jubenshaOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jubenshaOrderUuidNumber = new ArrayList<>();
                                    jubenshaOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jubenshaOrderUuidNumber",jubenshaOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<JubenshaOrderEntity> jubenshaOrderEntities_jubenshaOrderUuidNumber = jubenshaOrderService.selectList(new EntityWrapper<JubenshaOrderEntity>().in("jubensha_order_uuid_number", seachFields.get("jubenshaOrderUuidNumber")));
                        if(jubenshaOrderEntities_jubenshaOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JubenshaOrderEntity s:jubenshaOrderEntities_jubenshaOrderUuidNumber){
                                repeatFields.add(s.getJubenshaOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jubenshaOrderService.insertBatch(jubenshaOrderList);
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
        PageUtils page = jubenshaOrderService.queryPage(params);

        //字典表数据转换
        List<JubenshaOrderView> list =(List<JubenshaOrderView>)page.getList();
        for(JubenshaOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JubenshaOrderEntity jubenshaOrder = jubenshaOrderService.selectById(id);
            if(jubenshaOrder !=null){


                //entity转view
                JubenshaOrderView view = new JubenshaOrderView();
                BeanUtils.copyProperties( jubenshaOrder , view );//把实体数据重构到view中

                //级联表
                    JubenshaEntity jubensha = jubenshaService.selectById(jubenshaOrder.getJubenshaId());
                if(jubensha != null){
                    BeanUtils.copyProperties( jubensha , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJubenshaId(jubensha.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jubenshaOrder.getYonghuId());
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
    public R add(@RequestBody JubenshaOrderEntity jubenshaOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jubenshaOrder:{}",this.getClass().getName(),jubenshaOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            JubenshaEntity jubenshaEntity = jubenshaService.selectById(jubenshaOrder.getJubenshaId());
            if(jubenshaEntity == null){
                return R.error(511,"查不到剧本杀");
            }
            // Double jubenshaNewMoney = jubenshaEntity.getJubenshaNewMoney();

            if(false){
            }
            else if(jubenshaOrder.getYuyueTime() == null ){
                return R.error(511,"预约时间不能为空");
            }
            else if(jubenshaEntity.getJubenshaNewMoney() == null){
                return R.error(511,"剧本杀价格不能为空");
            }



            List<JubenshaOrderEntity> jubenshaOrderEntities = jubenshaOrderService.selectList(new EntityWrapper<JubenshaOrderEntity>().eq("jubensha_id", jubenshaOrder.getJubenshaId()).eq("yuyue_time", new SimpleDateFormat("yyyy-MM-dd").format(jubenshaOrder.getYuyueTime())).notIn("jubensha_order_types","2"));

            Integer zongNumber = 0;
            for(JubenshaOrderEntity j:jubenshaOrderEntities){
                zongNumber+=j.getBuyNumber();
            }
            if((zongNumber.intValue()+jubenshaOrder.getBuyNumber())>jubenshaEntity.getJubenshaKucunNumber().intValue()){
                return R.error(511,"已超过总参与人数,现已参与"+zongNumber);
            }




            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");



            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            double balance = yonghuEntity.getNewMoney() - jubenshaEntity.getJubenshaNewMoney()*jubenshaOrder.getBuyNumber()*zhekou;//余额
            buyJifen = new BigDecimal(jubenshaEntity.getJubenshaPrice()).multiply(new BigDecimal(jubenshaOrder.getBuyNumber())).doubleValue();//所获积分
            if(balance<0)
                return R.error(511,"余额不够支付");
            jubenshaOrder.setJubenshaOrderTypes(3); //设置订单状态为已支付
            jubenshaOrder.setJubenshaOrderTruePrice(jubenshaEntity.getJubenshaNewMoney()*jubenshaOrder.getBuyNumber()*zhekou); //设置实付价格
            jubenshaOrder.setYonghuId(userId); //设置订单支付人id
            jubenshaOrder.setJubenshaOrderPaymentTypes(1);
            jubenshaOrder.setInsertTime(new Date());
            jubenshaOrder.setCreateTime(new Date());
//                jubenshaEntity.setJubenshaKucunNumber( jubenshaEntity.getJubenshaKucunNumber() -jubenshaOrder.getBuyNumber());
//                jubenshaService.updateById(jubenshaEntity);
                jubenshaOrderService.insert(jubenshaOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);
            yonghuService.updateById(yonghuEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }











    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        if("用户".equals(role)){
            JubenshaOrderEntity jubenshaOrder = jubenshaOrderService.selectById(id);
            Integer buyNumber = jubenshaOrder.getBuyNumber();
            Integer jubenshaOrderPaymentTypes = jubenshaOrder.getJubenshaOrderPaymentTypes();
            Integer jubenshaId = jubenshaOrder.getJubenshaId();
            if(jubenshaId == null)
                return R.error(511,"查不到剧本杀");
            JubenshaEntity jubenshaEntity = jubenshaService.selectById(jubenshaId);
            if(jubenshaEntity == null)
                return R.error(511,"查不到剧本杀");
            Double jubenshaNewMoney = jubenshaEntity.getJubenshaNewMoney();
            if(jubenshaNewMoney == null)
                return R.error(511,"剧本杀价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(jubenshaOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = jubenshaEntity.getJubenshaNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(jubenshaEntity.getJubenshaPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }

//            jubenshaEntity.setJubenshaKucunNumber(jubenshaEntity.getJubenshaKucunNumber() + buyNumber);



            jubenshaOrder.setJubenshaOrderTypes(2);//设置订单状态为退款
            jubenshaOrderService.updateById(jubenshaOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
//            jubenshaService.updateById(jubenshaEntity);//更新订单中物品的信息
            return R.ok();
        }else{
            return R.error(511,"您没有权限退款");
        }
    }


    /**
     * 确认
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        JubenshaOrderEntity  jubenshaOrderEntity = new  JubenshaOrderEntity();;
        jubenshaOrderEntity.setId(id);
        jubenshaOrderEntity.setJubenshaOrderTypes(4);
        boolean b =  jubenshaOrderService.updateById( jubenshaOrderEntity);
        if(!b){
            return R.error("确认出错");
        }
        return R.ok();
    }









    /**
     * 使用
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        JubenshaOrderEntity  jubenshaOrderEntity = new  JubenshaOrderEntity();
        jubenshaOrderEntity.setId(id);
        jubenshaOrderEntity.setJubenshaOrderTypes(5);
        boolean b =  jubenshaOrderService.updateById( jubenshaOrderEntity);
        if(!b){
            return R.error("使用出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer jubenshaCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            JubenshaOrderEntity jubenshaOrder = jubenshaOrderService.selectById(id);
        if(jubenshaOrder == null)
            return R.error(511,"查不到该订单");
        if(jubenshaOrder.getJubenshaOrderTypes() != 5)
            return R.error(511,"您不能评价");
        Integer jubenshaId = jubenshaOrder.getJubenshaId();
        if(jubenshaId == null)
            return R.error(511,"查不到该物品");



            JubenshaCommentbackEntity jubenshaCommentbackEntity = new JubenshaCommentbackEntity();
            jubenshaCommentbackEntity.setId(id);
            jubenshaCommentbackEntity.setJubenshaId(jubenshaId);
            jubenshaCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            jubenshaCommentbackEntity.setJubenshaCommentbackPingfenNumber(jubenshaCommentbackPingfenNumber);
            jubenshaCommentbackEntity.setJubenshaCommentbackText(commentbackText);
            jubenshaCommentbackEntity.setReplyText(null);
            jubenshaCommentbackEntity.setInsertTime(new Date());
            jubenshaCommentbackEntity.setUpdateTime(null);
            jubenshaCommentbackEntity.setCreateTime(new Date());
            jubenshaCommentbackService.insert(jubenshaCommentbackEntity);

            jubenshaOrder.setJubenshaOrderTypes(1);//设置订单状态为已评价
            jubenshaOrderService.updateById(jubenshaOrder);//根据id更新
            return R.ok();
        }else{
            return R.error(511,"您没有权限评价");
        }
    }







}
