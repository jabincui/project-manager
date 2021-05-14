<template>
  <div style="display:flex; height:100%; flex-flow:column nowrap;">
    <el-row class="row" :gutter="8">
      <el-col :span="8">
        <el-input
            placeholder="期刊会议搜索"
            v-model="query"
            clearable
            @change="handleSearch"
        />
      </el-col>
      <el-col :span="1">
        <el-button @click="handleSearch" icon="el-icon-search" circle type="primary"></el-button>
      </el-col>
    </el-row>
    <div style="flex:1; overflow: hidden" >
      <el-scrollbar>
        <el-space direction="vertical">
          <JCSearchResult />
        </el-space>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import JCSearchResult from "@/components/paper/JCSearchResult";
import {getQueryResult} from "@/utils/connector";
export default {
  name: "JCSearch",
  components: {JCSearchResult},
  data(){
    return {
      query: '',
    }
  },
  methods: {
    handleSearch() {
      console.info(this.$data);
      this.results = getQueryResult();
    },
  }
}
</script>

<style>
.row {
  margin-bottom: 16px;
}
</style>