<template>
  <el-container>
    <el-header>
      <div class="logo">
        <img src="../assets/logo.png" alt=""/>
      </div>
      <el-dropdown @command="commandHandler">
        <span class="el-dropdown-link">
          {{empName}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="empCenter">个人中心</el-dropdown-item>
          <el-dropdown-item command="logout">安全退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    <el-container>
      <el-aside width="200px"  style="overflow: hidden;">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            router
            width="200px"
            style="height: 100%;"
          >
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>员工模块</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/addemp"><i class="el-icon-edit"></i>录入员工</el-menu-item>
                <el-menu-item index="/EmpList"><i class="el-icon-s-grid"></i>员工列表</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-s-grid"></i>
                <span>分类模块</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/addcate"><i class="el-icon-edit"></i>添加分类</el-menu-item>
                <el-menu-item index="/CateList"><i class="el-icon-s-grid"></i>分类列表</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="3" disabled>
              <template slot="title">
                <i class="el-icon-document"></i>
                <span>导航三</span>
              </template>
            </el-menu-item>
            <el-menu-item index="4">
              <template slot="title">
                <i class="el-icon-setting"></i>
                <span>导航四</span>
              </template>
            </el-menu-item>
          </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    data(){
      return {
        empName: sessionStorage.getItem("empName")
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      commandHandler(cmd){
        switch(cmd){
          case "empCenter":
            window.location.href="/#/empCenter";
            this.$message.success("显示个人中心！");
            break;
          case "logout":
            this.$confirm('此操作将退出系统, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
              .then(() => {
                sessionStorage.removeItem("empName");
                this.$router.push("/login");
              })
              .catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消退出'
                });          
              });
        }
      }
    }
  }
</script>

<style>
  .el-header{
    background-color: lavender;
    display:flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
  }

  .el-aside{
    background-color:rgb(84,92,100);
  }
</style>