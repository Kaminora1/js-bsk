<template>
    <div class="login-box">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>必胜客后台登录</span>
            </div>
            <el-form ref="loginForm" :model="FormLogin" label-width="80px" :rules="rules">

                <el-form-item label="账号" prop="loginName">
                    <el-input v-model="FormLogin.loginName"></el-input>
                </el-form-item>

                <el-form-item label="密码" prop="loginPwd">
                    <el-input type="password" v-model="FormLogin.loginPwd"></el-input>
                </el-form-item>

                <el-form-item >
                    <el-button type="primary" @click="toLogin">登录</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
    import request from '@/util/request';

    export default {
        data(){
            return{
                FormLogin:{
                    loginName: "",
                    loginPwd: "",
                },
                rules:{
                    loginName:[{
                            required: true,message: "请输入登录名称!",trigger:"blur"
                        }
                    ],
                    loginPwd:[{
                            required: true,message: "请输入登录密码!",trigger:"blur"
                        }
                    ]
                }
            }
        },
        methods:{
            toLogin(){
                this.$refs["loginForm"].validate(
                    async(valid)=>{
                        if(valid){
                            let result = await request.post("emp/login",this.FormLogin);
                            console.info(result);
                            if(result.data.code == 200){
                                sessionStorage.setItem("empName",result.data.empInfo.empName);
                                this.$router.push("/");
                            }else{
                                this.$message.error(result.data.msg);
                            }
                        }else{
                            console.log("Error submit!");
                            return false;
                        }
                    }
                )
            }
        }
    };
</script>

<style>


.login-box{
    height: 100%;
    background-image: url("../assets/loginback.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: right;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.box-card{
        width:300px;
        padding:30px;
        margin:0 auto;
}

.box-card > div span{
        color:blue;
        font-size: 28px;
}
</style>