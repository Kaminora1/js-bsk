<template>
    <div class="emp-list-box">
      <div class="title">分类信息列表</div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="序号" type="index" width="50"> </el-table-column>
        <el-table-column property="id" label="编号" width="80">
        </el-table-column>
        <el-table-column property="typeName" label="分类名称" width="120">
        </el-table-column>
        <el-table-column property="typeSort" label="分类排序值" width="180">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页器 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 3, 4, 5]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </template>
  
  <script>
  import request from "@/util/request";
  export default {
    data() {
      return {
        // 页面中展示的数据
        tableData: [],
        //页码
        pageNum: 1,
        //每页显示的记录数
        pageSize: 2,
        //总记录数
        total: 0,
        //总页数
        pages: 0,
      };
    },
    mounted() {
      //数据挂载成功,调用函数
      this.initData();
    },
    methods: {
      async initData() {
        //请求后端,获取数据
        let res = await request.post(
          "category/fenye?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize
        );
        console.info(res);
        if (res.data.code == 200) {
          //给data中定义变量赋值
          this.total = res.data.total;
          this.pages = res.data.pages;
          this.tableData = res.data.records;
        } else {
          this.$message.error("没有数据!");
        }
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      handleSizeChange(val) {
        this.pageSize = val;
        this.pageNum = 1;
        this.initData();
      },
      handleCurrentChange(val) {
        this.pageNum = val;
        //重新请求数据
        this.initData();
      },
    },
  };
  </script>
  
  <style>
  .emp-list-box .title {
    text-align: center;
    padding: 20px;
    font-size: 20px;
    color: grey;
  }
  
  .el-pagination {
    text-align: center;
    margin-top: 20px;
  }
  </style>
  