<template>
  <div style="display:flex; height:100%; flex-flow:column nowrap;">
    <el-row class="row" :gutter="8">
      <el-col :span="8">
        <el-input
            placeholder="论文搜索"
            v-model="query"
            clearable
            @change="handleSearch"
        />
      </el-col>
      <el-col :span="1">
        <el-button @click="handleSearch" icon="el-icon-search" circle type="primary"></el-button>
      </el-col>
    </el-row>
    <el-switch class="row" v-model="advance" active-text="高级筛选"/>
    <el-collapse-transition>
      <div v-show="advance" class="row">
        <el-form :model="form" label-width="80px">
          <el-row :gutter="8">
            <el-col :span="8">
              <el-form-item label="论文类型">
                <el-select
                    v-model="form.tjc"
                    multiple
                    reserve-keyword
                    placeholder="学位 | 期刊 | 会议"
                >
                  <el-option
                      v-for="item in tjc_options"
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
          <el-row :gutter="8">
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
          <el-row :gutter="8">
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
    <el-row style="margin-bottom: 8px" v-show="results.length !== 0">
      <el-space>
        <el-radio-group v-model="display" size="mini">
          <el-radio-button label="卡片表示"></el-radio-button>
          <el-radio-button label="表格表示"></el-radio-button>
        </el-radio-group>
        <el-button size="mini" >导出搜索结果</el-button>
      </el-space>
    </el-row>
    <div style="flex:1; overflow: hidden" >
      <el-scrollbar>
        <el-space direction="vertical">
          <PaperSearchResult
              v-for="item in results"
              @click="this.$router.push(`/paper/search/${item.pk}`)"
              :key="item.pk"
              :re_data="item"
          />
        </el-space>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import UtilSelector from "@/utils/UtilSelector";
import PaperSearchResult from "@/components/paper/PaperSearchResult";
import {
  authorListGet,
  authorRender,
  getQueryResult,
  keywordListGet,
  keywordRender,
  topicListGet,
  topicRender
} from "@/utils/connector";


export default {
  data() {
    return {
      advance: false,
      tjc_options: [
        {value: 'thesis', label: '学位论文'},
        {value: 'journal_paper', label: '期刊论文'},
        {value: 'conference_paper', label: '会议论文'},
      ],
      query: '',
      form: {
        tjc: '',
        date: null,
        mentors: [],
        firstAuthors: [],
        comAuthors: [],
        studentAuthors: [],
        topics: [],
        keywords: [],
      },
      results: [],
      display: '卡片表示',
    }
  },
  methods: {
    handleSearch() {
      console.info(this.$data);
      this.results = getQueryResult();
    },
    callAuthorListGet(q, o, r) {
      authorListGet(q, o, r)
    },
    callAuthorRender(i) {
      return authorRender(i)
    },
    callTopicListGet(q, r) {
      topicListGet(q, r)
    },
    callTopicRender(i) {
      return topicRender(i)
    },
    callKeywordListGet(q, r) {
      keywordListGet(q, r)
    },
    callKeywordRender(i) {
      return keywordRender(i)
    }
  },
  components: {UtilSelector, PaperSearchResult}
}
</script>

<style>
.row {
  margin-bottom: 16px;
}
.row:last-child{
  margin-bottom: 0;
}
</style>