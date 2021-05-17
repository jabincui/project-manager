<template>
  <div style="display:flex; height:100%; flex-flow:column nowrap;">
    <el-row class="row" :gutter="8">
      <el-col :span="8">
        <el-input
            placeholder="期刊会议搜索"
            v-model="query"
            clearable
            @input="debouncedClick"
        />
      </el-col>
      <el-col :span="1">
        <el-button @click="handleSearch" icon="el-icon-search" circle type="primary"></el-button>
      </el-col>
    </el-row>
    <div style="flex:1; overflow: hidden" >
      <el-scrollbar>
        <el-space direction="vertical" class="force_width">
          <JCSearchResult
              v-for="item in results"
              :key="item.pk"
              :re_data="item"
              @click="this.$router.push(`/paper/jc_search/${item.pk}`)"
          />
        </el-space>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import JCSearchResult from "@/components/paper/JCSearchResult";
import {journalListGet} from "@/utils/connector";
const _ = require('lodash');
export default {
  name: "JCSearch",
  components: {JCSearchResult},
  created() {
    this.debouncedClick = _.debounce(this.handleSearch, 500)
  },
  mounted() {
    this.handleSearch('')
  },
  unmounted() {
    this.debouncedClick.cancel()
  },
  data(){
    return {
      query: '',
      results: [],
    }
  },
  methods: {
    handleSearch() {
      console.info(this.query);
      journalListGet(this.query, 'a', res => {this.results = res});
    },
  }
}
</script>

<style>
.row {
  margin-bottom: 16px;
}

.force_width,
.force_width > .el-space__item{
  width: 100%;
}
</style>