<template>
  <div style="display:flex; height:100%; flex-flow:column nowrap;">
    <div class="row">
      <el-page-header icon="el-icon-arrow-left" title="返回"
                      @back="$router.back()"
                      :content='this.$route.params.id === undefined ? `添加新论文` : `论文详情`'/>
    </div>
    <div style="flex:1; overflow: hidden">
      <el-scrollbar>
        <transition-group name="el-fade-in-linear" >
        <el-card key="main" shadow="hover" class="row">
          <el-descriptions class="row" :column="3">
            <template #title>
              <el-space v-if="!edit">
                <el-tag style="font-weight: normal;"> {{ getTypeName }}</el-tag>
                <div class="header_text" style="font-weight: normal;"> {{ title ?? '论文详情' }}</div>
              </el-space>
              <el-input v-if="edit" v-model="title"
                        class="header_text" style="width:60vh; font-weight: normal;"
                        placeholder="论文标题">
                <template #prepend>
                  <el-select style="width: 120px;" v-model="tjc">
                    <el-option
                        v-for="item in tjc_options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                  </el-select>
                </template>
              </el-input>
            </template>
            <template #extra>
              <el-affix :offset="75">
                <el-space>
                  <el-button type="primary" size="small" @click="handleSave" v-if="edit">保存</el-button>
                  <el-button type="primary" size="small" @click="toggleEdit" plain v-if="!edit">编辑</el-button>
                  <el-button type="info" size="small" @click="toggleEdit" v-if="edit">取消</el-button>
                  <el-button v-if="this.$route.params.id !== undefined" type="danger" size="small" plain>删除</el-button>
                </el-space>
              </el-affix>
            </template>
            <el-descriptions-item label="论文编号">
              <template v-if="!edit">{{ pk }}</template>
              <el-input class="table-input" v-model="pk" size="small" v-if="edit"/>
            </el-descriptions-item>
            <el-descriptions-item :label="tjc === 'thesis' ? '结题时间' : '发表时间'" :span="1">
              <template v-if="!edit">{{ date ? `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`:'未设置' }}</template>
              <el-date-picker v-if="edit" class="table-input" v-model="date"
                              size="small"
                              format="YYYY-M-D"/>
            </el-descriptions-item>
            <el-descriptions-item label="是否推荐" :span="1">
              <template v-if="!edit"> {{ favor ? '是' : '否' }}</template>
              <el-check-tag v-if="edit" @change="() => {this.favor = !this.favor}" :checked="favor"
              >{{ favor ? '推荐' : '不推荐' }}
              </el-check-tag>
            </el-descriptions-item>
            <el-descriptions-item label="作者" :span="3">
              <el-space v-if="!edit">
                <el-tag type="info"
                        v-for="au in authors"
                        :key="au.pk"
                >
                  {{ au.name }}
                </el-tag>
              </el-space>
              <UtilSelector class="table-input"
                            :multiple="tjc !== 'thesis'"
                            v-model="authors"
                            :getter="callAuthorListGet"
                            :render="callAuthorRender"
                            v-if="edit" size="small"/>
            </el-descriptions-item>
            <el-descriptions-item label="研究方向" :span="3">
              <el-space v-if="!edit">
                <el-tag type="info"
                        v-for="tp in topics"
                        :key="tp.pk"
                >
                  {{ tp.name }}
                </el-tag>
              </el-space>
              <UtilSelector class="table-input" multiple allow-new
                            v-model="topics"
                            :getter="callTopicListGet"
                            :render="callTopicRender"
                            :builder="callTopicBuilder"
                            v-if="edit" size="small"/>
            </el-descriptions-item>
            <el-descriptions-item label="关键词" :span="3">
              <el-space v-if="!edit">
                <el-tag type="info"
                        v-for="kw in keywords"
                        :key="kw.pk"
                >
                  {{ kw.name }}
                </el-tag>
              </el-space>
              <UtilSelector class="table-input" multiple allow-new
                            v-model="keywords"
                            :getter="callKeywordListGet"
                            :render="callKeywordRender"
                            :builder="callKeywordBuilder"
                            v-if="edit" size="small"/>
            </el-descriptions-item>
          </el-descriptions>
          <p class="main_text" v-if="!edit">
            摘要：{{ intro }}
          </p>
          <div v-if="edit">
            <p class="main_text">摘要：</p>
            <el-input class="main_text" v-model="intro" type="textarea" :autosize="{minRows: 3}" clearable/>
          </div>
        </el-card>
        <div key="spec" class="row">
            <el-card key="t" shadow="hover" v-if="tjc === 'thesis'">
              <el-descriptions :column="4">
                <el-descriptions-item label="录取导师">
                  <span v-if="!edit">{{ thesis_paper_detail.named_mentor }}</span>
                  <el-input class="table-input" v-model="thesis_paper_detail.named_mentor" size="small" v-if="edit" disabled/>
                </el-descriptions-item>
                <el-descriptions-item label="项目导师">
                  <span v-if="!edit">{{ thesis_paper_detail.proj_mentor }}</span>
                  <el-input class="table-input" v-model="thesis_paper_detail.proj_mentor" size="small" v-if="edit" disabled/>
                </el-descriptions-item>
                <el-descriptions-item label="查重率">
                  <span v-if="!edit"> {{ thesis_paper_detail.rate }}% </span>
                  <el-input class="table-input" v-model="thesis_paper_detail.rate" size="small" v-if="edit">
                    <template #suffix>%</template>
                  </el-input>
                </el-descriptions-item>
                <el-descriptions-item label="论文类型">
                  <span v-if="!edit"> {{ thesis_paper_detail.type ? thesis_paper_detail.type.name : '' }} </span>
                  <UtilSelector class="table-input"
                                v-model="thesis_paper_detail.type"
                                :getter="callTypeListGet"
                                :render="callTypeRender"
                                v-if="edit" size="small"/>
                </el-descriptions-item>
                <el-descriptions-item :span="4" label="原文链接">
                  <el-link type="primary" :href="full_path">{{ full_path }}</el-link>
                </el-descriptions-item>
                <el-descriptions-item :span="4" label="PPT链接">
                  <el-link type="primary" :href="thesis_paper_detail.ppt">{{ thesis_paper_detail.ppt }}</el-link>
                </el-descriptions-item>
              </el-descriptions>
            </el-card>
            <el-card key="j" shadow="hover" v-if="tjc === 'journal_paper'">
              <el-descriptions :column="4">
                <el-descriptions-item label="通信作者" :span="4">
                  <el-space v-if="!edit">
                    <el-tag v-for="au in journal_paper_detail.com_authors"
                            :key="au.pk"
                            type="info"
                    >
                      {{ au.name }}
                    </el-tag>
                  </el-space>
                  <UtilSelector class="table-input" multiple
                                v-model="journal_paper_detail.com_authors"
                                :getter="(q, r) => r(this.authors)"
                                :render="callAuthorRender"
                                v-if="edit" size="small"/>
                </el-descriptions-item>
                <el-descriptions-item label="期刊名称">
                  <template v-if="!edit"> {{ getJournalName }}</template>
                  <UtilSelector class="table-input"
                                v-model="journal_paper_detail.journal"
                                :getter="callJournalListGet"
                                :render="callJournalRender"
                                v-if="edit" size="small"/>
                </el-descriptions-item>
                <el-descriptions-item label="年卷期" :span="2">
                  <template v-if="!edit">
                    {{ journal_paper_detail.journal_yvn.y }}年 第{{journal_paper_detail.journal_yvn.v }}卷 第{{ journal_paper_detail.journal_yvn.n }}期
                  </template>
                  <el-space class="table-input" v-if="edit">
                    <el-input v-model="journal_paper_detail.journal_yvn.y" size="small">
                      <template #suffix>年</template>
                    </el-input>
                    <el-input v-model="journal_paper_detail.journal_yvn.v" size="small">
                      <template #prefix>第</template>
                      <template #suffix>卷</template>
                    </el-input>
                    <el-input v-model="journal_paper_detail.journal_yvn.n" size="small">
                      <template #prefix>第</template>
                      <template #suffix>期</template>
                    </el-input>
                  </el-space>
                </el-descriptions-item>
                <el-descriptions-item label="页码">
                  <template v-if="!edit"> {{journal_paper_detail.page}} </template>
                  <el-input class="table-input" v-if="edit" v-model="journal_paper_detail.page" size="small">
                  </el-input>
                </el-descriptions-item>
                <el-descriptions-item label="联合发表情况" :span="4">
                  <el-space v-if="!edit">
                    <el-tag v-for="jt in filterJoint"
                            :key="jt"
                            type="info"
                    >{{jt}}</el-tag>
                  </el-space>
                  <el-space v-if="edit">
                    <el-checkbox v-for="jt in Object.keys(journal_paper_detail.joint)"
                                 v-model="journal_paper_detail.joint[jt]"
                                 size="small"
                                 border
                                 :key="jt"
                    >
                      {{joint_options[jt]}}
                    </el-checkbox>
                  </el-space>
                </el-descriptions-item>
                <el-descriptions-item label="原文链接" :span="4">
                  <el-link type="primary" :href="full_path">{{ full_path }}</el-link>
                </el-descriptions-item>
              </el-descriptions>
              <el-divider></el-divider>
              <el-descriptions direction="vertical" :column="2">
                <el-descriptions-item label="期刊封面">
                  <el-image class="cover-image"
                            :src="null"
                            :preview-src-list="[null]"
                  ></el-image>
                </el-descriptions-item>
                <el-descriptions-item label="论文首页">
                  <el-image class="cover-image"
                            :src="journal_paper_detail.home"
                            :preview-src-list="[journal_paper_detail.home]"></el-image>
                </el-descriptions-item>
              </el-descriptions>
            </el-card>
            <el-card key="c" shadow="hover" v-if="tjc === 'conference_paper'">
              <el-descriptions :column="4">
                <el-descriptions-item label="通信作者" :span="4">
                  <el-space v-if="!edit">
                    <el-tag v-for="au in conference_paper_detail.com_authors"
                            :key="au.pk"
                            type="info"
                    >
                      {{ au.name }}
                    </el-tag>
                  </el-space>
                  <UtilSelector class="table-input" multiple
                                v-model="conference_paper_detail.com_authors"
                                :getter="(q, r) => r(this.authors)"
                                :render="callAuthorRender"
                                v-if="edit" size="small"/>
                </el-descriptions-item>
                <el-descriptions-item label="会议名称">
                  <template v-if="!edit"> {{ conference_paper_detail.conference.name }} ({{
                      conference_paper_detail.conference_date ? conference_paper_detail.conference_date.getFullYear() : ''
                    }}) </template>
                  <UtilSelector v-if="edit" class="table-input"
                                v-model="conference_paper_detail.conference"
                                :getter="callConferenceListGet"
                                :render="callJournalRender"
                                size="small"/>
                </el-descriptions-item>
                <el-descriptions-item label="会议地点">
                  <template v-if="!edit">
                    {{ conference_paper_detail.conference_locate }}
                  </template>
                  <el-input class="table-input" v-if="edit" v-model="conference_paper_detail.conference_locate" size="small">
                  </el-input>
                </el-descriptions-item>
                <el-descriptions-item label="会议日期">
                  <template v-if="!edit">{{ conference_paper_detail.conference_date ?`${conference_paper_detail.conference_date.getFullYear()}-${conference_paper_detail.conference_date.getMonth() + 1}-${conference_paper_detail.conference_date.getDate()}`:'未设置'}}</template>
                  <el-date-picker v-if="edit" class="table-input" v-model="conference_paper_detail.conference_date"
                                  size="small"
                                  format="YYYY-M-D"/>
                </el-descriptions-item>
                <el-descriptions-item label="页码">
                  <template v-if="!edit"> {{conference_paper_detail.page}} </template>
                  <el-input class="table-input" v-if="edit" v-model="conference_paper_detail.page" size="small">
                  </el-input>
                </el-descriptions-item>
                <el-descriptions-item label="联合发表情况" :span="4">
                  <el-space v-if="!edit">
                    <el-tag v-for="jt in filterJoint"
                            :key="jt"
                            type="info"
                    >{{jt}}</el-tag>
                  </el-space>
                  <el-space v-if="edit">
                    <el-checkbox v-for="jt in Object.keys(conference_paper_detail.joint)"
                                 v-model="conference_paper_detail.joint[jt]"
                                 size="small"
                                 border
                                 :key="jt"
                    >
                      {{joint_options[jt]}}
                    </el-checkbox>
                  </el-space>
                </el-descriptions-item>
                <el-descriptions-item label="原文链接" :span="4">
                  <el-link type="primary" :href="full_path">{{ full_path }}</el-link>
                </el-descriptions-item>
              </el-descriptions>
            </el-card>
        </div>
        <el-card key="con" class="row" shadow="hover" style="margin-bottom: 16px">
          <el-descriptions :column="1">
            <el-descriptions-item label="关联项目">
              <el-space v-if="!edit">
                <el-tag v-for="p in projects"
                        :key="p.pk"
                        type="info"
                >
                  {{ p.name }}
                </el-tag>
              </el-space>
              <UtilSelector class="table-input"
                            v-if="edit" v-model="projects"
                            multiple
                            :getter="callProjectListGet"
                            :render="callProjectRender"
                            size="small"/>
            </el-descriptions-item>
            <el-descriptions-item label="关联成果">
              <el-space v-if="!edit">
                <el-tag v-for="o in outcomes"
                        :key="o.pk"
                        type="info"
                >
                  {{ o.name }}
                </el-tag>
              </el-space>
              <UtilSelector class="table-input"
                            v-if="edit" v-model="outcomes"
                            multiple
                            :getter="callOutcomeListGet"
                            :render="callOutcomeRender"
                            size="small"/>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
        </transition-group>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import {
  authorListGet,
  authorRender,
  getDataByUid, journalListGet, journalRender, keywordBuilder,
  keywordListGet,
  keywordRender,
  outcomeListGet,
  outcomeRender,
  projectListGet,
  projectRender, topicBuilder,
  topicListGet,
  topicRender,
  typeListGet,
  typeRender
} from "@/utils/connector";
import UtilSelector from "@/utils/UtilSelector";

export default {
  name: "PaperDetail",
  components: {UtilSelector},
  data() {
    return {
      edit: false,
      result: {},
      tjc_options: [
        {value: 'thesis', label: '学位论文'},
        {value: 'journal_paper', label: '期刊论文'},
        {value: 'conference_paper', label: '会议论文'},
      ],
      joint_options: {
        isInduct: "与行业联合发表",
        isGov: "与地方联合发表",
        isInter: "与国际联合发表",
        isKua: "跨学科论文",
        inter: "国际论文"
      },
      pk: '',
      tjc: '',
      title: '',
      date: null,
      intro: '',
      authors: [],
      keywords: [],
      topics: [],
      projects: [],
      outcomes: [],
      favor: false,
      full_path: '',
      thesis_paper_detail: {
        named_mentor: '',
        proj_mentor: '',
        rate: '',
        type: [],
        ppt: '',
      },
      journal_paper_detail: {
        com_authors: [],
        journal: null,
        journal_yvn: {y: '', v: '', n: ''},
        cover: '',
        page: '',
        home: '',
        joint: {
          isInduct: false,
          isGov: false,
          isInter: false,
          isKua: false,
          inter: false,
        },
      },
      conference_paper_detail: {
        com_authors: [],
        conference: null,
        conference_year: '',
        conference_locate: '',
        conference_date: null,
        page: '',
        joint: {
          isInduct: false,
          isGov: false,
          isInter: false,
          isKua: false,
          inter: false,
        },
      }
    }
  },
  mounted() {
    if(this.$route.params.id !== undefined){
      this.initData()
    }else{
      this.edit = true
    }
  },
  computed: {
    getTypeName() {
      const valueDict = {
        thesis: "学位论文",
        journal_paper: "期刊论文",
        conference_paper: "会议论文"
      }
      return valueDict[this.tjc]
    },
    getJournalName() {
      return journalRender(this.journal_paper_detail.journal)
    },
    filterJoint() {
      let joint = {
        thesis: undefined,
        journal_paper: this.journal_paper_detail.joint,
        conference_paper: this.conference_paper_detail.joint
      }[this.tjc]
      if(joint !== undefined){
        let keys = Object.keys(joint).filter(item => joint[item])
        return keys.map(item => this.joint_options[item])
      }else{
        return []
      }
    }
  },
  methods: {
    initData() {
      let result = getDataByUid(this.$route.params.id);
      this.result = result;
      this.pk = result.pk;
      this.tjc = result.tjc;
      this.title = result.title;
      this.date = new Date(result.date);
      this.authors = result.authors;
      this.intro = result.intro;
      this.keywords = result.keywords;
      this.topics = result.topics;
      this.projects = result.projects;
      this.outcomes = result.outcomes;
      this.full_path = result.full_path;
      this.favor = result.favor;
      if (this.tjc === 'thesis') {
        this.thesis_paper_detail.named_mentor = result.authors[0].named_mentor;
        this.thesis_paper_detail.proj_mentor = result.authors[0].proj_mentor;
        this.thesis_paper_detail.rate = result.dup_rate;
        this.thesis_paper_detail.type = result.type;
        this.thesis_paper_detail.ppt = result.ppt;
      }
      if (this.tjc === 'journal_paper') {
        this.journal_paper_detail.com_authors = result.comAuthors;
        this.journal_paper_detail.journal = result.journal;
        this.journal_paper_detail.journal_yvn = result.yvn
        this.journal_paper_detail.page = result.page;
        this.journal_paper_detail.home = result.home;
        this.journal_paper_detail.joint = result.joint;
      }
      if (this.tjc === 'conference_paper') {
        this.conference_paper_detail.com_authors = result.comAuthors;
        this.conference_paper_detail.conference = result.conference;
        this.conference_paper_detail.conference_year = result.conference_year;
        this.conference_paper_detail.conference_locate = result.conference_locate;
        this.conference_paper_detail.conference_date = new Date(result.conference_date);
        this.conference_paper_detail.page = result.page;
        this.conference_paper_detail.joint = result.joint;
      }
    },
    toggleEdit() {
      if (this.edit) {
        if (this.$route.params.id !== undefined){
          this.initData()
        }else{
          this.$router.back();
        }
      }
      this.edit = !this.edit;
    },
    handleSave() {
      console.info('save');
      console.info(this.$data)
      this.edit = false;
    },
    callAuthorListGet(q, r) {
      if (this.tjc === 'thesis') {
        authorListGet(q, 'stu', r)
      } else {
        authorListGet(q, '', r)
      }
    },
    callAuthorRender(i) {
      return authorRender(i)
    },
    callTypeListGet(q, r) {
      typeListGet(q, r)
    },
    callTypeRender(i) {
      return typeRender(i)
    },
    callKeywordListGet(q, r) {
      keywordListGet(q, r)
    },
    callKeywordRender(i) {
      return keywordRender(i)
    },
    callKeywordBuilder(s) {
      return keywordBuilder(s)
    },
    callTopicListGet(q, r) {
      topicListGet(q, r)
    },
    callTopicRender(i) {
      return topicRender(i)
    },
    callTopicBuilder(s) {
      return topicBuilder(s)
    },
    callProjectListGet(q, r) {
      projectListGet(q, r)
    },
    callProjectRender(i) {
      return projectRender(i)
    },
    callOutcomeListGet(q, r) {
      outcomeListGet(q, r)
    },
    callOutcomeRender(i) {
      return outcomeRender(i)
    },
    callJournalListGet(q, r){
      journalListGet(q, 'j', r)
    },
    callConferenceListGet(q, r){
      journalListGet(q, 'c', r)
    },
    callJournalRender(i){
      return journalRender(i)
    }
  }
}
</script>

<style>
.row {
  margin-bottom: 16px;
}

.el-descriptions__body {
  background: transparent;
}

.el-image {
  overflow: visible;
}

.el-divider {
  margin-top: 0;
  margin-bottom: 12px;
}

.main_text {
  color: #303133;
  font-size: 14px;
}

.header_text {
  color: #303133;
  font-size: 18px;
}

.table-input {
  width: 60%
}

.cover-image {
  width: 100px;
  height: 150px;
}
</style>