import router from '@/router';
//路由守卫
router.beforeEach((to,from,next)=>{
    //白名单
    const whiteList =['/login'];
    //获取员工名称
    let empName = sessionStorage.getItem("empName");
    if(empName){
        //empName不为''，说明登录成功了
        //放行
        next();
    }else{
        if(whiteList.includes(to.path)){
            next();
        }else{
            //跳转到登录页面
            next('/login');
        }
    }
});