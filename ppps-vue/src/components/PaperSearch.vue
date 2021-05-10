<template>
  <div style="margin-top: 15px">
    <el-row class="row">
      <el-col :span="22">
        <el-input
            placeholder="论文搜索"
            v-model="query"
            clearable
            @change="handleSearch"
        />
      </el-col>
      <el-col :span="1">
        <el-button @click="handleSearch" icon="el-icon-search"></el-button>
      </el-col>
    </el-row>
    <el-switch class="row" v-model="advance" active-text="高级筛选"/>
    <el-row class="row">
      <el-collapse-transition >
        <div v-show="advance">
          <el-form :model="form" label-width="80px">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="论文类型">
                  <el-select
                      v-model="form.tpc"
                      multiple
                      reserve-keyword
                      placeholder="学位 | 期刊 | 会议"
                  >
                    <el-option
                        v-for="item in tpc_options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="16">
                <el-form-item label="发表日期">
                  <el-date-picker
                      v-model="form.date"
                      type="daterange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期">
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="指导老师">
                  <UtilSelector v-model="form.mentors"
                                multiple
                                :getter="(q, r) => callAuthorListGet(q, 'men', r)"
                                :render="callAuthorRender"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="第一作者">
                  <UtilSelector v-model="form.firstAuthors"
                                multiple
                                :getter="(q, r) => callAuthorListGet(q, 'fst', r)"
                                :render="callAuthorRender"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="通信作者">
                  <UtilSelector v-model="form.comAuthors"
                                multiple
                                :getter="(q, r) => callAuthorListGet(q, 'com', r)"
                                :render="callAuthorRender"/>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="学生">
                  <UtilSelector v-model="form.studentAuthors"
                                multiple
                                :getter="(q, r) => callAuthorListGet(q, 'stu', r)"
                                :render="callAuthorRender"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="研究方向">
                  <UtilSelector v-model="form.topics"
                                multiple
                                :getter="callTopicListGet"
                                :render="callTopicRender"/>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="关键词">
                  <UtilSelector v-model="form.keywords"
                                multiple
                                :getter="callKeywordListGet"
                                :render="callKeywordRender"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-collapse-transition>
    </el-row>
  </div>
</template>

<script>
import UtilSelector from "@/utils/UtilSelector";
import {authorListGet, authorRender, keywordListGet, keywordRender, topicListGet, topicRender} from "@/utils/connector";

export default {
  data() {
    return {
      advance: false,
      tpc_options: [
        {value: 'thesis', label: '学位论文'},
        {value: 'journal_paper', label: '期刊论文'},
        {value: 'conference_paper', label: '会议论文'},
      ],
      query: '',
      form: {
        tpc: '',
        date: null,
        mentors: [],
        firstAuthors: [],
        comAuthors: [],
        studentAuthors: [],
        topics: [],
        keywords: [],
      }
    }
  },
  methods: {
    handleSearch() {
      console.info(this.$data)
    },
    callAuthorListGet(q, o, r) {
      authorListGet(q, o, r)
    },
    callAuthorRender(i){
      return authorRender(i)
    },
    callTopicListGet(q, r) {
      topicListGet(q, r)
    },
    callTopicRender(i){
      return topicRender(i)
    },
    callKeywordListGet(q, r){
      keywordListGet(q, r)
    },
    callKeywordRender(i){
      return keywordRender(i)
    }
  },
  components: {UtilSelector}
}
</script>

<style>
.row {
  margin-bottom: 15px;
  &:last-child {
    margin-bottom: 0;
  }
}
</style>