<template>
  <el-container style="height: 100vh">
    <el-aside width="200px">
      <el-scrollbar>
      <el-menu unique-opened router
               :default-active="$route.path" >
        <el-submenu index="1">
          <template #title>
            <i class="el-icon-takeaway-box"></i>
            <span>项目管理系统</span>
          </template>
          <!-- TODO:Insert Project system here. -->
          <!-- Step3: Add a new menu item in UI.
            Single item: <el-menu-item index="/path/to/your/component">A title</el-menu-item>
            Item group:
              <el-menu-item-group>
                <template #title>A group name</template>
                <el-menu-item index="/path/to/your/component">期刊会议搜索</el-menu-item>
              </el-menu-item-group>
           -->
        </el-submenu>
        <el-submenu index="2">
          <template #title>
            <i class="el-icon-collection-tag"></i>
            <span>专利管理系统</span>
          </template>
          <!-- TODO:Insert Patent system here. -->
          <!-- Step3: Add a new menu item in UI.
            Single item: <el-menu-item index="/path/to/your/component">A title</el-menu-item>
            Item group:
              <el-menu-item-group>
                <template #title>A group name</template>
                <el-menu-item index="/path/to/your/component">期刊会议搜索</el-menu-item>
              </el-menu-item-group>
            -->
        </el-submenu>
        <el-submenu index="3">
          <template #title>
            <i class="el-icon-document"></i>
            <span>论文管理系统</span>
          </template>
          <el-menu-item-group>
            <template #title>论文管理</template>
            <el-menu-item index="/paper/search">论文检索</el-menu-item>
            <el-menu-item index="/paper/paper_new">添加新论文</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <template #title>期刊会议管理</template>
            <el-menu-item index="/paper/jc_search">期刊会议搜索</el-menu-item>
            <el-menu-item index="/paper/jc_new">添加新期刊会议</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
      </el-scrollbar>
    </el-aside>
    <el-container>
      <el-header>
        <span>欢迎你，观察者</span>
      </el-header>
      <el-main>
        <transition name="el-fade-in-linear">
          <router-view :key="$route.path"> </router-view>
        </transition>
      </el-main>
      <el-footer>北京邮电大学电子工程学院通信与网络研究中心（2021）</el-footer>
    </el-container>
  </el-container>
</template>

<Script>

</Script>

<style>
html, body{
  margin:0;
  padding: 0;
}

.el-header, .el-footer {
  background-color: #003399;
  color: rgb(255, 253, 253);
  line-height: 60px;
}

.el-footer{
  text-align: center;
}

.el-header{
  text-align: right;
  font-size: 15px
}

.el-aside {
  color: #333;
  background-color: rgb(238, 241, 246)
}

.el-main {
  background-color: rgb(245, 248, 251);
  padding-bottom: 0;
  overflow: hidden;
}

</style>