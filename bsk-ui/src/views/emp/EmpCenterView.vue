<template>
    <div class="emp-list-box">
        <el-table :data="tableData" style="width: 100%">
            <el-table-column property="empName" label="姓名" width="180"></el-table-column>
            <el-table-column property="empPhone" label="手机号码" width="180"></el-table-column>
            <el-table-column property="empIdcard" label="身份证号码" width="180"></el-table-column>
            <el-table-column property="empSalary" label="工资" width="180"></el-table-column>
            <el-table-column property="empHirdate" label="入职时间" width="180"></el-table-column>
        </el-table>
    </div>
</template>

<script>
import request from "@/util/request";
export default {
    data() {
        return {
            tableData: [{
                empName: "tony",
                empPhone:'',
                empIdcard: '',
                empSalary:'',
                empHirdate:'',
            }],
        };
    },
    mounted() {
        //进入页面执行此方法
        this.getCurrentUser();
    },
    methods: {
        async getCurrentUser(){
            //发送get请求获取当前用户的信息
            let res= await request.get("/emp/getCurrentUser");
            this.tableData = res.data.list;
        }
    },
};
</script>