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
    import forum from '@/views/modules/forum/list'
    import gonggao from '@/views/modules/gonggao/list'
    import jubensha from '@/views/modules/jubensha/list'
    import jubenshaCollection from '@/views/modules/jubenshaCollection/list'
    import jubenshaCommentback from '@/views/modules/jubenshaCommentback/list'
    import jubenshaLiuyan from '@/views/modules/jubenshaLiuyan/list'
    import jubenshaOrder from '@/views/modules/jubenshaOrder/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryForum from '@/views/modules/dictionaryForum/list'
    import dictionaryForumState from '@/views/modules/dictionaryForumState/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryHuiyuandengji from '@/views/modules/dictionaryHuiyuandengji/list'
    import dictionaryJubensha from '@/views/modules/dictionaryJubensha/list'
    import dictionaryJubenshaCollection from '@/views/modules/dictionaryJubenshaCollection/list'
    import dictionaryJubenshaOrder from '@/views/modules/dictionaryJubenshaOrder/list'
    import dictionaryJubenshaOrderPayment from '@/views/modules/dictionaryJubenshaOrderPayment/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'





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
        path: '/dictionaryForum',
        name: '帖子类型',
        component: dictionaryForum
    }
    ,{
        path: '/dictionaryForumState',
        name: '帖子状态',
        component: dictionaryForumState
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryHuiyuandengji',
        name: '会员等级类型',
        component: dictionaryHuiyuandengji
    }
    ,{
        path: '/dictionaryJubensha',
        name: '剧本杀分类',
        component: dictionaryJubensha
    }
    ,{
        path: '/dictionaryJubenshaCollection',
        name: '收藏表类型',
        component: dictionaryJubenshaCollection
    }
    ,{
        path: '/dictionaryJubenshaOrder',
        name: '订单类型',
        component: dictionaryJubenshaOrder
    }
    ,{
        path: '/dictionaryJubenshaOrderPayment',
        name: '订单支付类型',
        component: dictionaryJubenshaOrderPayment
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
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
        path: '/forum',
        name: '论坛',
        component: forum
      }
    ,{
        path: '/gonggao',
        name: '公告信息',
        component: gonggao
      }
    ,{
        path: '/jubensha',
        name: '剧本杀',
        component: jubensha
      }
    ,{
        path: '/jubenshaCollection',
        name: '剧本杀收藏',
        component: jubenshaCollection
      }
    ,{
        path: '/jubenshaCommentback',
        name: '剧本杀评价',
        component: jubenshaCommentback
      }
    ,{
        path: '/jubenshaLiuyan',
        name: '剧本杀留言',
        component: jubenshaLiuyan
      }
    ,{
        path: '/jubenshaOrder',
        name: '剧本杀订单',
        component: jubenshaOrder
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
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
