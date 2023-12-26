<template>
    <div class="Regis-box">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span style="
                    color:darkgoldenrod;
                    font-size: 28px;">
                    必胜客分类添加</span>
            </div>
            <el-form ref="RegForm" :model="Reg_form" :rules="rules" label-width="80px">

                <el-form-item label="分类名称" prop="typeName">
                    <el-input v-model="Reg_form.typeName"></el-input>
                </el-form-item>

                <el-form-item label="分类排序值" prop="typeSort">
                    <el-input v-model="Reg_form.typeSort"></el-input>
                </el-form-item>
                <el-form-item >
                    <el-button type="primary" @click="toReg">添加</el-button>
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
                    typeName: "",
                    typeSort: "",
                },
            };
        },
        methods: {
            toReg() {
                //表单验证开始  ref="RegForm"
                this.$refs["RegForm"].validate(async(valid) => {
                    if (valid) {
                        this.$message.success("注册成功!");
                        let result = await request.post("category/save",this.Reg_form);
                        if (result.data.code = 200) {
                            this.$route.push("/CateList");
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