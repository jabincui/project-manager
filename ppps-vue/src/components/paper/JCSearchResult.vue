<template>
  <el-card shadow="hover">
    <div class="row" style="display: flex">
      <el-space style="flex: 1">
        <el-tag v-if="re_data.type">
          {{ getTypeName }}
        </el-tag>
        <div style="font-size: 20px;"> {{ re_data.name ? re_data.name : re_data.ename}}
          <template v-if="re_data.short_name"> ({{re_data.short_name}}) </template>
        </div>
      </el-space>
      <el-tag v-if="getStatue.status !== 0" effect="dark" :type="getStatue.color">
        {{ getStatue.text }}
      </el-tag>
    </div>
    <div class="row" v-if="re_data.rate">
      <span style="font-size: 12px">评级：</span>
      <el-space>
        <el-tag v-for="ra in re_data.rate"
                :key="ra.key"
                type="info" size="small"
        >
          {{callRateRender(ra)}}
        </el-tag>
      </el-space>
    </div>
  </el-card>
</template>

<script>
import {rateRender} from "@/utils/connector";

export default {
  name: "JCSearchResult",
  props: {
    re_data: Object,
  },
  computed: {
    getTypeName(){
      return {
        j: '期刊',
        c: '会议'
      }[this.re_data.type]
    },
    getStatue(){
      let s = 0;
      if(this.re_data && this.re_data.deadline && this.re_data.publish){
        const nowTime = new Date();
        const deadline = new Date(this.re_data.deadline)
        const publish = new Date(this.re_data.publish)
        if (nowTime <= deadline) {
          s = 1
        } else if (nowTime < publish) {
          s = 2
        } else {
          s = 3
        }
      }else{
        s = 0
      }
      return {
        0: {status: 0, color: 'info', text: '未知'},
        1: {status: 1, color: null, text: '征稿中'},
        2: {status: 2, color: 'danger', text: '已截稿'},
        3: {status: 3, color: 'success', text: '已发布'},
      }[s]
    },
  },
  methods: {
    callRateRender(i){
      return rateRender(i)
    }
  }
}
</script>

<style scoped>
.row{
  margin-bottom: 12px;
}
.row:last-child{
  margin-bottom: 0;
}
</style>