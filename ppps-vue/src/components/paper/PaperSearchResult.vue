<template>
  <el-card shadow="hover">
    <el-space class="row">
      <el-tag>{{ getTypeName }}</el-tag>
      <div style="font-size: 20px;"> {{ re_data.title }}</div>
      <div v-for="item in re_data.author"
           :key="item.pk"
           style="font-size: 12px"
      >
        {{ item.name }}
      </div>
    </el-space>
    <el-button circle
               style="float: right"
               :icon="re_data.favor ? 'el-icon-star-on' : 'el-icon-star-off'"
               :type="re_data.favor ? 'warning' : null" />
    <div class="row" style="font-size: 14px">
      <b>摘要：</b> {{re_data.intro}}
    </div>
    <div class="row">
      <b style="font-size: 14px">关键词：</b>
      <el-space >
        <el-tag v-for="kw in re_data.keywords" :key="kw.pk" size="small" type="info">{{kw.name}}</el-tag>
      </el-space>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "PaperSearchResult",
  props: {
    re_data: Object,
  },
  computed: {
    getTypeName(){
      const valueDict = {
        thesis: "学位论文",
        journal_paper: "期刊论文",
        conference_paper: "会议论文"
      }
      return valueDict[this.re_data.tjc]
    },
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