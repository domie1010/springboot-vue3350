import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import exampaper from '@/views/modules/exampaper/list'
    import examquestion from '@/views/modules/examquestion/list'
    import examrecord from '@/views/modules/examrecord/list'
    import examredetails from '@/views/modules/examredetails/list'
    import examrewrongquestion from '@/views/modules/examrewrongquestion/list'
    import gonggao from '@/views/modules/gonggao/list'
    import yonghu from '@/views/modules/yonghu/list'
    import zixunshi from '@/views/modules/zixunshi/list'
    import zixunshiCollection from '@/views/modules/zixunshiCollection/list'
    import zixunshiLiuyan from '@/views/modules/zixunshiLiuyan/list'
    import zixunshiOrder from '@/views/modules/zixunshiOrder/list'
    import config from '@/views/modules/config/list'
    import dictionaryExampaper from '@/views/modules/dictionaryExampaper/list'
    import dictionaryExamquestion from '@/views/modules/dictionaryExamquestion/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryZixunshiCollection from '@/views/modules/dictionaryZixunshiCollection/list'
    import dictionaryZixunshiOrderYesno from '@/views/modules/dictionaryZixunshiOrderYesno/list'

    import adminexam from '@/views/modules/exampaper/exam'




//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
	  ,{
	      path: '/adminexam',
	      name: 'adminexam',
	      component: adminexam,
	      meta: {icon:'', title:'adminexam'}
	    }
    ,{
        path: '/dictionaryExampaper',
        name: '试卷状态',
        component: dictionaryExampaper
    }
    ,{
        path: '/dictionaryExamquestion',
        name: '试题类型',
        component: dictionaryExamquestion
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryZixunshiCollection',
        name: '收藏表类型',
        component: dictionaryZixunshiCollection
    }
    ,{
        path: '/dictionaryZixunshiOrderYesno',
        name: '预约状态',
        component: dictionaryZixunshiOrderYesno
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/exampaper',
        name: '试卷表',
        component: exampaper
      }
    ,{
        path: '/examquestion',
        name: '试题表',
        component: examquestion
      }
    ,{
        path: '/examrecord',
        name: '考试记录表',
        component: examrecord
      }
    ,{
        path: '/examredetails',
        name: '答题详情表',
        component: examredetails
      }
    ,{
        path: '/examrewrongquestion',
        name: '错题表',
        component: examrewrongquestion
      }
    ,{
        path: '/gonggao',
        name: '公告',
        component: gonggao
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }
    ,{
        path: '/zixunshi',
        name: '咨询师',
        component: zixunshi
      }
    ,{
        path: '/zixunshiCollection',
        name: '咨询师收藏',
        component: zixunshiCollection
      }
    ,{
        path: '/zixunshiLiuyan',
        name: '咨询师留言',
        component: zixunshiLiuyan
      }
    ,{
        path: '/zixunshiOrder',
        name: '咨询师预约',
        component: zixunshiOrder
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
