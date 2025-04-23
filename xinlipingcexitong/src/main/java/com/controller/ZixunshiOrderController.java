
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
 * 咨询师预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zixunshiOrder")
public class ZixunshiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ZixunshiOrderController.class);

    @Autowired
    private ZixunshiOrderService zixunshiOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ZixunshiService zixunshiService;



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
        else if("咨询师".equals(role))
            params.put("zixunshiId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = zixunshiOrderService.queryPage(params);

        //字典表数据转换
        List<ZixunshiOrderView> list =(List<ZixunshiOrderView>)page.getList();
        for(ZixunshiOrderView c:list){
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
        ZixunshiOrderEntity zixunshiOrder = zixunshiOrderService.selectById(id);
        if(zixunshiOrder !=null){
            //entity转view
            ZixunshiOrderView view = new ZixunshiOrderView();
            BeanUtils.copyProperties( zixunshiOrder , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(zixunshiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                ZixunshiEntity zixunshi = zixunshiService.selectById(zixunshiOrder.getZixunshiId());
                if(zixunshi != null){
                    BeanUtils.copyProperties( zixunshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setZixunshiId(zixunshi.getId());
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
    public R save(@RequestBody ZixunshiOrderEntity zixunshiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zixunshiOrder:{}",this.getClass().getName(),zixunshiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("咨询师".equals(role))
            zixunshiOrder.setZixunshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("用户".equals(role))
            zixunshiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        zixunshiOrder.setInsertTime(new Date());
        zixunshiOrder.setCreateTime(new Date());
        zixunshiOrderService.insert(zixunshiOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZixunshiOrderEntity zixunshiOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zixunshiOrder:{}",this.getClass().getName(),zixunshiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("咨询师".equals(role))
//            zixunshiOrder.setZixunshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("用户".equals(role))
//            zixunshiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ZixunshiOrderEntity> queryWrapper = new EntityWrapper<ZixunshiOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixunshiOrderEntity zixunshiOrderEntity = zixunshiOrderService.selectOne(queryWrapper);
        if(zixunshiOrderEntity==null){
            zixunshiOrderService.updateById(zixunshiOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ZixunshiOrderEntity zixunshiOrderEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,zixunshiOrderEntity:{}",this.getClass().getName(),zixunshiOrderEntity.toString());

//        if(zixunshiOrderEntity.getZixunshiOrderYesnoTypes() == 2){//通过
//            zixunshiOrderEntity.setZixunshiOrderTypes();
//        }else if(zixunshiOrderEntity.getZixunshiOrderYesnoTypes() == 3){//拒绝
//            zixunshiOrderEntity.setZixunshiOrderTypes();
//        }
        zixunshiOrderEntity.setZixunshiOrderShenheTime(new Date());//审核时间
        zixunshiOrderService.updateById(zixunshiOrderEntity);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zixunshiOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ZixunshiOrderEntity> zixunshiOrderList = new ArrayList<>();//上传的东西
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
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZixunshiOrderEntity zixunshiOrderEntity = new ZixunshiOrderEntity();
//                            zixunshiOrderEntity.setZixunshiOrderUuidNumber(data.get(0));                    //预约单号 要改的
//                            zixunshiOrderEntity.setZixunshiId(Integer.valueOf(data.get(0)));   //咨询师 要改的
//                            zixunshiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zixunshiOrderEntity.setInsertTime(date);//时间
//                            zixunshiOrderEntity.setYuyueTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            zixunshiOrderEntity.setZixunshiOrderYesnoTypes(Integer.valueOf(data.get(0)));   //预约状态 要改的
//                            zixunshiOrderEntity.setZixunshiOrderYesnoText(data.get(0));                    //审核回复 要改的
//                            zixunshiOrderEntity.setZixunshiOrderShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            zixunshiOrderEntity.setCreateTime(date);//时间
                            zixunshiOrderList.add(zixunshiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //预约单号
                                if(seachFields.containsKey("zixunshiOrderUuidNumber")){
                                    List<String> zixunshiOrderUuidNumber = seachFields.get("zixunshiOrderUuidNumber");
                                    zixunshiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zixunshiOrderUuidNumber = new ArrayList<>();
                                    zixunshiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zixunshiOrderUuidNumber",zixunshiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约单号
                        List<ZixunshiOrderEntity> zixunshiOrderEntities_zixunshiOrderUuidNumber = zixunshiOrderService.selectList(new EntityWrapper<ZixunshiOrderEntity>().in("zixunshi_order_uuid_number", seachFields.get("zixunshiOrderUuidNumber")));
                        if(zixunshiOrderEntities_zixunshiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZixunshiOrderEntity s:zixunshiOrderEntities_zixunshiOrderUuidNumber){
                                repeatFields.add(s.getZixunshiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zixunshiOrderService.insertBatch(zixunshiOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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
        PageUtils page = zixunshiOrderService.queryPage(params);

        //字典表数据转换
        List<ZixunshiOrderView> list =(List<ZixunshiOrderView>)page.getList();
        for(ZixunshiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZixunshiOrderEntity zixunshiOrder = zixunshiOrderService.selectById(id);
            if(zixunshiOrder !=null){


                //entity转view
                ZixunshiOrderView view = new ZixunshiOrderView();
                BeanUtils.copyProperties( zixunshiOrder , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(zixunshiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    ZixunshiEntity zixunshi = zixunshiService.selectById(zixunshiOrder.getZixunshiId());
                if(zixunshi != null){
                    BeanUtils.copyProperties( zixunshi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setZixunshiId(zixunshi.getId());
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
    public R add(@RequestBody ZixunshiOrderEntity zixunshiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zixunshiOrder:{}",this.getClass().getName(),zixunshiOrder.toString());
            ZixunshiEntity zixunshiEntity = zixunshiService.selectById(zixunshiOrder.getZixunshiId());
            if(zixunshiEntity == null){
                return R.error(511,"查不到该咨询师");
            }
            // Double zixunshiNewMoney = zixunshiEntity.getZixunshiNewMoney();

            if(zixunshiOrder.getYuyueTime() ==null){
                return R.error("预约时间不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            zixunshiOrder.setYonghuId(userId); //设置订单支付人id
            zixunshiOrder.setZixunshiOrderUuidNumber(String.valueOf(new Date().getTime()));
            zixunshiOrder.setInsertTime(new Date());
            zixunshiOrder.setCreateTime(new Date());
                zixunshiOrderService.insert(zixunshiOrder);//新增订单
            return R.ok();
    }



}
