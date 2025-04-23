
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
 * 咨询师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zixunshi")
public class ZixunshiController {
    private static final Logger logger = LoggerFactory.getLogger(ZixunshiController.class);

    @Autowired
    private ZixunshiService zixunshiService;


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
        else if("咨询师".equals(role))
            params.put("zixunshiId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = zixunshiService.queryPage(params);

        //字典表数据转换
        List<ZixunshiView> list =(List<ZixunshiView>)page.getList();
        for(ZixunshiView c:list){
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
        ZixunshiEntity zixunshi = zixunshiService.selectById(id);
        if(zixunshi !=null){
            //entity转view
            ZixunshiView view = new ZixunshiView();
            BeanUtils.copyProperties( zixunshi , view );//把实体数据重构到view中

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
    public R save(@RequestBody ZixunshiEntity zixunshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zixunshi:{}",this.getClass().getName(),zixunshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZixunshiEntity> queryWrapper = new EntityWrapper<ZixunshiEntity>()
            .eq("username", zixunshi.getUsername())
            .or()
            .eq("zixunshi_phone", zixunshi.getZixunshiPhone())
            .or()
            .eq("zixunshi_id_number", zixunshi.getZixunshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixunshiEntity zixunshiEntity = zixunshiService.selectOne(queryWrapper);
        if(zixunshiEntity==null){
            zixunshi.setCreateTime(new Date());
            zixunshi.setPassword("123456");
            zixunshiService.insert(zixunshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者咨询师手机号或者咨询师身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZixunshiEntity zixunshi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zixunshi:{}",this.getClass().getName(),zixunshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<ZixunshiEntity> queryWrapper = new EntityWrapper<ZixunshiEntity>()
            .notIn("id",zixunshi.getId())
            .andNew()
            .eq("username", zixunshi.getUsername())
            .or()
            .eq("zixunshi_phone", zixunshi.getZixunshiPhone())
            .or()
            .eq("zixunshi_id_number", zixunshi.getZixunshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixunshiEntity zixunshiEntity = zixunshiService.selectOne(queryWrapper);
        if("".equals(zixunshi.getZixunshiPhoto()) || "null".equals(zixunshi.getZixunshiPhoto())){
                zixunshi.setZixunshiPhoto(null);
        }
        if(zixunshiEntity==null){
            zixunshiService.updateById(zixunshi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者咨询师手机号或者咨询师身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zixunshiService.deleteBatchIds(Arrays.asList(ids));
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
            List<ZixunshiEntity> zixunshiList = new ArrayList<>();//上传的东西
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
                            ZixunshiEntity zixunshiEntity = new ZixunshiEntity();
//                            zixunshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //zixunshiEntity.setPassword("123456");//密码
//                            zixunshiEntity.setZixunshiName(data.get(0));                    //咨询师姓名 要改的
//                            zixunshiEntity.setZixunshiPhone(data.get(0));                    //咨询师手机号 要改的
//                            zixunshiEntity.setZixunshiIdNumber(data.get(0));                    //咨询师身份证号 要改的
//                            zixunshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            zixunshiEntity.setZixunshiEmail(data.get(0));                    //电子邮箱 要改的
//                            zixunshiEntity.setZixunshiPhoto("");//详情和图片
//                            zixunshiEntity.setZixunshiCongye(data.get(0));                    //从业时长 要改的
//                            zixunshiEntity.setZixunshiShanchang(data.get(0));                    //擅长 要改的
//                            zixunshiEntity.setZixunshiContent("");//详情和图片
//                            zixunshiEntity.setCreateTime(date);//时间
                            zixunshiList.add(zixunshiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //咨询师手机号
                                if(seachFields.containsKey("zixunshiPhone")){
                                    List<String> zixunshiPhone = seachFields.get("zixunshiPhone");
                                    zixunshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> zixunshiPhone = new ArrayList<>();
                                    zixunshiPhone.add(data.get(0));//要改的
                                    seachFields.put("zixunshiPhone",zixunshiPhone);
                                }
                                //咨询师身份证号
                                if(seachFields.containsKey("zixunshiIdNumber")){
                                    List<String> zixunshiIdNumber = seachFields.get("zixunshiIdNumber");
                                    zixunshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zixunshiIdNumber = new ArrayList<>();
                                    zixunshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("zixunshiIdNumber",zixunshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<ZixunshiEntity> zixunshiEntities_username = zixunshiService.selectList(new EntityWrapper<ZixunshiEntity>().in("username", seachFields.get("username")));
                        if(zixunshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZixunshiEntity s:zixunshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //咨询师手机号
                        List<ZixunshiEntity> zixunshiEntities_zixunshiPhone = zixunshiService.selectList(new EntityWrapper<ZixunshiEntity>().in("zixunshi_phone", seachFields.get("zixunshiPhone")));
                        if(zixunshiEntities_zixunshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZixunshiEntity s:zixunshiEntities_zixunshiPhone){
                                repeatFields.add(s.getZixunshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [咨询师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //咨询师身份证号
                        List<ZixunshiEntity> zixunshiEntities_zixunshiIdNumber = zixunshiService.selectList(new EntityWrapper<ZixunshiEntity>().in("zixunshi_id_number", seachFields.get("zixunshiIdNumber")));
                        if(zixunshiEntities_zixunshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZixunshiEntity s:zixunshiEntities_zixunshiIdNumber){
                                repeatFields.add(s.getZixunshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [咨询师身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zixunshiService.insertBatch(zixunshiList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ZixunshiEntity zixunshi = zixunshiService.selectOne(new EntityWrapper<ZixunshiEntity>().eq("username", username));
        if(zixunshi==null || !zixunshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(zixunshi.getId(),username, "zixunshi", "咨询师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","咨询师");
        r.put("username",zixunshi.getZixunshiName());
        r.put("tableName","zixunshi");
        r.put("userId",zixunshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ZixunshiEntity zixunshi){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<ZixunshiEntity> queryWrapper = new EntityWrapper<ZixunshiEntity>()
            .eq("username", zixunshi.getUsername())
            .or()
            .eq("zixunshi_phone", zixunshi.getZixunshiPhone())
            .or()
            .eq("zixunshi_id_number", zixunshi.getZixunshiIdNumber())
            ;
        ZixunshiEntity zixunshiEntity = zixunshiService.selectOne(queryWrapper);
        if(zixunshiEntity != null)
            return R.error("账户或者咨询师手机号或者咨询师身份证号已经被使用");
        zixunshi.setCreateTime(new Date());
        zixunshiService.insert(zixunshi);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ZixunshiEntity zixunshi = new ZixunshiEntity();
        zixunshi.setPassword("123456");
        zixunshi.setId(id);
        zixunshiService.updateById(zixunshi);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        ZixunshiEntity zixunshi = zixunshiService.selectOne(new EntityWrapper<ZixunshiEntity>().eq("username", username));
        if(zixunshi!=null){
            zixunshi.setPassword("123456");
            boolean b = zixunshiService.updateById(zixunshi);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrZixunshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ZixunshiEntity zixunshi = zixunshiService.selectById(id);
        if(zixunshi !=null){
            //entity转view
            ZixunshiView view = new ZixunshiView();
            BeanUtils.copyProperties( zixunshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
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
        PageUtils page = zixunshiService.queryPage(params);

        //字典表数据转换
        List<ZixunshiView> list =(List<ZixunshiView>)page.getList();
        for(ZixunshiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZixunshiEntity zixunshi = zixunshiService.selectById(id);
            if(zixunshi !=null){


                //entity转view
                ZixunshiView view = new ZixunshiView();
                BeanUtils.copyProperties( zixunshi , view );//把实体数据重构到view中

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
    public R add(@RequestBody ZixunshiEntity zixunshi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zixunshi:{}",this.getClass().getName(),zixunshi.toString());
        Wrapper<ZixunshiEntity> queryWrapper = new EntityWrapper<ZixunshiEntity>()
            .eq("username", zixunshi.getUsername())
            .or()
            .eq("zixunshi_phone", zixunshi.getZixunshiPhone())
            .or()
            .eq("zixunshi_id_number", zixunshi.getZixunshiIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixunshiEntity zixunshiEntity = zixunshiService.selectOne(queryWrapper);
        if(zixunshiEntity==null){
            zixunshi.setCreateTime(new Date());
        zixunshi.setPassword("123456");
        zixunshiService.insert(zixunshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者咨询师手机号或者咨询师身份证号已经被使用");
        }
    }


}
