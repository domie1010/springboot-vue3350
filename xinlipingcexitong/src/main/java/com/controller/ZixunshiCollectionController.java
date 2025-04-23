
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
 * 咨询师收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zixunshiCollection")
public class ZixunshiCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(ZixunshiCollectionController.class);

    @Autowired
    private ZixunshiCollectionService zixunshiCollectionService;


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
        PageUtils page = zixunshiCollectionService.queryPage(params);

        //字典表数据转换
        List<ZixunshiCollectionView> list =(List<ZixunshiCollectionView>)page.getList();
        for(ZixunshiCollectionView c:list){
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
        ZixunshiCollectionEntity zixunshiCollection = zixunshiCollectionService.selectById(id);
        if(zixunshiCollection !=null){
            //entity转view
            ZixunshiCollectionView view = new ZixunshiCollectionView();
            BeanUtils.copyProperties( zixunshiCollection , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(zixunshiCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                ZixunshiEntity zixunshi = zixunshiService.selectById(zixunshiCollection.getZixunshiId());
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
    public R save(@RequestBody ZixunshiCollectionEntity zixunshiCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zixunshiCollection:{}",this.getClass().getName(),zixunshiCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("咨询师".equals(role))
            zixunshiCollection.setZixunshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("用户".equals(role))
            zixunshiCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZixunshiCollectionEntity> queryWrapper = new EntityWrapper<ZixunshiCollectionEntity>()
            .eq("zixunshi_id", zixunshiCollection.getZixunshiId())
            .eq("yonghu_id", zixunshiCollection.getYonghuId())
            .eq("zixunshi_collection_types", zixunshiCollection.getZixunshiCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixunshiCollectionEntity zixunshiCollectionEntity = zixunshiCollectionService.selectOne(queryWrapper);
        if(zixunshiCollectionEntity==null){
            zixunshiCollection.setInsertTime(new Date());
            zixunshiCollection.setCreateTime(new Date());
            zixunshiCollectionService.insert(zixunshiCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZixunshiCollectionEntity zixunshiCollection, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zixunshiCollection:{}",this.getClass().getName(),zixunshiCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("咨询师".equals(role))
//            zixunshiCollection.setZixunshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("用户".equals(role))
//            zixunshiCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ZixunshiCollectionEntity> queryWrapper = new EntityWrapper<ZixunshiCollectionEntity>()
            .notIn("id",zixunshiCollection.getId())
            .andNew()
            .eq("zixunshi_id", zixunshiCollection.getZixunshiId())
            .eq("yonghu_id", zixunshiCollection.getYonghuId())
            .eq("zixunshi_collection_types", zixunshiCollection.getZixunshiCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixunshiCollectionEntity zixunshiCollectionEntity = zixunshiCollectionService.selectOne(queryWrapper);
        if(zixunshiCollectionEntity==null){
            zixunshiCollectionService.updateById(zixunshiCollection);//根据id更新
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
        zixunshiCollectionService.deleteBatchIds(Arrays.asList(ids));
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
            List<ZixunshiCollectionEntity> zixunshiCollectionList = new ArrayList<>();//上传的东西
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
                            ZixunshiCollectionEntity zixunshiCollectionEntity = new ZixunshiCollectionEntity();
//                            zixunshiCollectionEntity.setZixunshiId(Integer.valueOf(data.get(0)));   //咨询师 要改的
//                            zixunshiCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zixunshiCollectionEntity.setZixunshiCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            zixunshiCollectionEntity.setInsertTime(date);//时间
//                            zixunshiCollectionEntity.setCreateTime(date);//时间
                            zixunshiCollectionList.add(zixunshiCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        zixunshiCollectionService.insertBatch(zixunshiCollectionList);
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
        PageUtils page = zixunshiCollectionService.queryPage(params);

        //字典表数据转换
        List<ZixunshiCollectionView> list =(List<ZixunshiCollectionView>)page.getList();
        for(ZixunshiCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZixunshiCollectionEntity zixunshiCollection = zixunshiCollectionService.selectById(id);
            if(zixunshiCollection !=null){


                //entity转view
                ZixunshiCollectionView view = new ZixunshiCollectionView();
                BeanUtils.copyProperties( zixunshiCollection , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(zixunshiCollection.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    ZixunshiEntity zixunshi = zixunshiService.selectById(zixunshiCollection.getZixunshiId());
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
    public R add(@RequestBody ZixunshiCollectionEntity zixunshiCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zixunshiCollection:{}",this.getClass().getName(),zixunshiCollection.toString());
        Wrapper<ZixunshiCollectionEntity> queryWrapper = new EntityWrapper<ZixunshiCollectionEntity>()
            .eq("zixunshi_id", zixunshiCollection.getZixunshiId())
            .eq("yonghu_id", zixunshiCollection.getYonghuId())
            .eq("zixunshi_collection_types", zixunshiCollection.getZixunshiCollectionTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixunshiCollectionEntity zixunshiCollectionEntity = zixunshiCollectionService.selectOne(queryWrapper);
        if(zixunshiCollectionEntity==null){
            zixunshiCollection.setInsertTime(new Date());
            zixunshiCollection.setCreateTime(new Date());
        zixunshiCollectionService.insert(zixunshiCollection);
            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }


}
