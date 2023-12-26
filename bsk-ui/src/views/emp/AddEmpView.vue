<template>
    <div class="Regis-box">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span style="
                    color:darkgoldenrod;
                    font-size: 28px;">
                    必胜客员工注册</span>
            </div>
            <el-form ref="RegForm" :model="Reg_form" :rules="rules" label-width="80px">

                <el-form-item label="登录账号" prop="loginName">
                    <el-input v-model="Reg_form.loginName"></el-input>
                </el-form-item>

                <el-form-item label="登录密码" prop="loginPwd">
                    <el-input type="password" v-model="Reg_form.loginPwd" placeholder="123456"></el-input>
                </el-form-item>

                <el-form-item label="员工姓名" prop="empName">
                    <el-input v-model="Reg_form.empName"></el-input>
                </el-form-item>

                <el-form-item label="手机号码" prop="empPhone">
                    <el-input v-model="Reg_form.empPhone"></el-input>
                </el-form-item>

                <el-form-item label="员工性别" prop="empSex">
                    <el-radio v-model="Reg_form.empSex" label="男">男</el-radio>
                    <el-radio v-model="Reg_form.empSex" label="女">女</el-radio>
                </el-form-item>

                <el-form-item label="身份证号码" prop="empIdcard">
                    <el-input v-model="Reg_form.empIdcard"></el-input>
                </el-form-item>

                <el-form-item label="入职时间" prop="empHirdate">
                    <el-date-picker v-model="Reg_form.empHirdate">选择时间</el-date-picker>
                </el-form-item>

                <el-form-item >
                    <el-button type="primary" @click="toReg">注册</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
    import request from "@/util/request";
    export default {
        data(){
            return{
                Reg_form:{
                    loginName: "",
                    loginPwd: "",
                    empName: "", 
                    empPhone: "", 
                    empSex: "",
                    empIdcard: "",
                },
                rules:{
                    loginName:[{
                            required: true,
                            message: "不能为空!",
                            trigger:"change",

                        },
                        { required: true, validator: this.checkLoginName, trigger: "change" },
                        
                    ],
                    loginPwd:[{
                            required: true,
                            message: "不能为空!",
                            trigger:"change",

                        },
                        {
                            pattern:/^\d{8,24}$/,
                            message:"密码控制在8-24位字符!",
                            trigger:"change",
                        },
                        {
                            pattern:/^[^\u4e00-\u9fa5]*$/,
                            message:"不能有中文!",
                            trigger:"change",
                        }
                    ],
                    empPhone:[{
                            required: true,
                            message: "不能为空!",
                            trigger:"change",
                        },
                        {
                            pattern:/^1[3456789]\d{9}$/,
                            message: "请输入合法手机号码!",
                            trigger:"change",
                        },
                        { required: true, validator: this.checkEmpPhone, trigger: "blur" }    
                    ],
                    empIdcard:[{
                            required: true,
                            message: "不能为空!",
                            trigger:"change",

                        },
                        {
                            pattern:/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/,
                            message: "请输入合法身份证号!",
                            trigger:"change",
                        },
                        { required: true, validator: this.checkIdCard, trigger: "blur" }
                    ],
                    empName:[{
                            required: true,
                            message: "不能为空!",
                            trigger:"change",

                        }
                    ],
                    empSex:[{
                            required: true,
                            message: "不能为空!",
                            trigger:"blur",
                        }
                    ]
                }
            };
        },
        methods: {

            async checkIdCard(rule, value, callback) {
                // get 请求方式
                let result = await request.get("emp/checkIdCard/" + value);
                if (result.data.code != 200) {
                    //验证失败
                    callback(new Error("此身份证号码已被绑定!"));
                } else {
                    //验证通过
                    callback();
                }
            },

            async checkLoginName(rule, value, callback) {
                // get 请求方式
                let result = await request.get("emp/checkLoginName/" + value);
                if (result.data.code != 200) {
                    //验证失败
                    callback(new Error("此登录名称已被使用!"));
                } else {
                    //验证通过
                    callback();
                }
            },

            async checkEmpPhone(rule, value, callback) {
                let result = await request.get("emp/checkEmpPhone/" + value);
                if (result.data.code != 200) {
                    //验证失败
                    callback(new Error("此手机号码已被使用!"));
                } else {
                    //验证通过
                    callback();
                }
            },
            toReg() {
                //表单验证开始  ref="RegForm"
                this.$refs["RegForm"].validate(async(valid) => {
                    if (valid) {
                        this.$message.success("注册成功!");
                        let result = await request.post("emp/save",this.Reg_form);
                        if (result.data.code = 200) {
                            this.$route.push("/empList");
                        } else {
                            this.$message.error("添加失败");
                        }
                    } else {
                        console.log("注册失败!");
                    return false;
                    }
                });
                //表单验证结束
            },
        }
    };
</script>

<style>
body{
    
}

.Regis-box{
    height: 100%;
    background-image: url("../../assets/loginback.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: right;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.box-card{
        width:500px;
        padding:30px;
        margin:0 auto;
}

.box-card > span{
        color:black;
}
</style>