<template>
  <div style="display:flex; height:100%; flex-flow:column nowrap;">
    <div class="row">
      <el-page-header icon="el-icon-arrow-left" title="返回"
                      @back="$router.back()"
                      :content='this.$route.params.id === undefined ? `添加期刊会议` : `${typeName}详情`'/>
    </div>
    <div style="flex:1; overflow: hidden">
      <el-scrollbar>
        <transition-group name="el-fade-in-linear" >
          <el-card key="main" shadow="hover" class="row">
            <el-descriptions class="row" :column="3">
              <template #title>
                <el-space v-if="!edit">
                  <el-tag style="font-weight: normal;"> {{ typeName }}</el-tag>
                  <div class="header_text" style="font-weight: normal;"> {{ getDisplayTitle }}</div>
                </el-space>
                <el-input v-if="edit" v-model="getDisplayTitle"
                          class="header_text" style="width:60vh; font-weight: normal;"
                          placeholder="论文标题">
                  <template #prepend>
                    <el-select style="width: 120px;" v-model="type">
                      <el-option
                          v-for="item in type_options"
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
                    <el-button type="info" size="small" @click="toggleEdit" v-if="edit">取消</el-button>
                    <el-button type="primary" size="small" @click="toggleEdit" plain v-if="!edit">编辑</el-button>
                    <el-button v-if="this.$route.params.id !== undefined" type="danger" size="small" plain
                               @click="handleDelete"
                    >删除
                    </el-button>
                  </el-space>
                </el-affix>
              </template>
              <el-descriptions-item label="中文名称">
                <template v-if="!edit">{{ name ?? '-' }}</template>
                <el-input class="table-input" v-model="name" size="small" v-if="edit"/>
              </el-descriptions-item>
              <el-descriptions-item label="英文名称">
                <template v-if="!edit">{{ ename ?? '-' }}</template>
                <el-input class="table-input" v-model="ename" size="small" v-if="edit"/>
              </el-descriptions-item>
              <el-descriptions-item label="缩写">
                <template v-if="!edit">{{ short_name ?? '-' }}</template>
                <el-input class="table-input" v-model="short_name" size="small" v-if="edit"/>
              </el-descriptions-item>
              <el-descriptions-item :label="`${typeName ?? ''}编号`">
                <template v-if="!edit">{{ pk }}</template>
                <el-input class="table-input" v-model="pk" size="small" v-if="edit"/>
              </el-descriptions-item>
              <el-descriptions-item :label="`评级`" :span="2">
                <el-space v-if="!edit">
                  <el-tag type="info"
                          v-for="rt in rate"
                          :key="rt.pk"
                  >
                    {{ callRateRender(rt) }}
                  </el-tag>
                </el-space>
                <UtilSelector class="table-input" multiple
                              v-model="rate"
                              :getter="callRateListGet"
                              :render="callRateRender"
                              v-if="edit" size="small"/>
              </el-descriptions-item>
            </el-descriptions>
          </el-card>
          <el-card key="his" shadow="hover" class="row" v-if="type">
            <div v-if="type === 'j'" class="header_text"> 期刊历史</div>
            <el-table v-if="type === 'j'"
                      :data="journal_history"
                      style="width: 100%">
              <el-table-column label="年">
                <template #default="scope">
                  <template v-if="!edit"> {{ scope.row.y ?? '-' }}</template>
                  <el-input class="table-input" size="small" v-if="edit" v-model="scope.row.y"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="卷">
                <template #default="scope">
                  <template v-if="!edit"> {{ scope.row.v ?? '-' }}</template>
                  <el-input class="table-input" size="small" v-if="edit" v-model="scope.row.v"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="期">
                <template #default="scope">
                  <template v-if="!edit"> {{ scope.row.n ?? '-' }}</template>
                  <el-input class="table-input" size="small" v-if="edit" v-model="scope.row.n"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="封面">
                <template #default="scope">
                  <el-image class="cover-image"
                            :src="scope.row.cover"
                            :preview-src-list="[scope.row.cover]"
                  ></el-image>
                </template>
              </el-table-column>
              <el-table-column v-if="edit" align="right" width="80px">
                <template #default="scope">
                  <el-button type="danger" size="mini" @click="handleTableDelete(scope.$index)"> 删除</el-button>
                </template>
              </el-table-column>
              <template #append>

              </template>
            </el-table>
            <div v-if="type === 'c'" class="header_text"> 会议历史</div>
            <el-table v-if="type === 'c'"
                      :data="conference_history"
                      style="width: 100%">
              <el-table-column label="年">
                <template #default="scope">
                  <template v-if="!edit"> {{ scope.row.year ?? '-' }}</template>
                  <el-input class="table-input" size="small" v-if="edit" v-model="scope.row.year"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="开会时间">
                <template #default="scope">
                  <template v-if="!edit">{{
                      scope.row.date ? `${scope.row.date.getFullYear()}-${scope.row.date.getMonth() + 1}-${scope.row.date.getDate()}` : '-'
                    }}
                  </template>
                  <el-date-picker class="table-input" size="small" v-if="edit" v-model="scope.row.date"
                                  format="YYYY-M-D"></el-date-picker>
                </template>
              </el-table-column>
              <el-table-column label="会议地点">
                <template #default="scope">
                  <template v-if="!edit"> {{ scope.row.place ?? '-' }}</template>
                  <el-input class="table-input" size="small" v-if="edit" v-model="scope.row.place"></el-input>
                </template>
              </el-table-column>
              <el-table-column v-if="edit" align="right" width="80px">
                <template #default="scope">
                  <el-button type="danger" size="mini" @click="handleTableDelete(scope.$index)"> 删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-button icon="el-icon-plus" style="margin-top: 8px" type="text"
                       v-if="edit" @click="handleAdd"> 添加历史
            </el-button>
          </el-card>
        </transition-group>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import {getJournalByUid, journalDelete, rateListGet, rateRender} from "@/utils/connector";
import UtilSelector from "@/utils/UtilSelector";

export default {
  name: "JCDetail",
  components: {UtilSelector},
  data() {
    return {
      edit: false,
      type_options: [
        {value: 'j', label: '期刊'},
        {value: 'c', label: '会议'},
      ],
      pk: '',
      name: '',
      ename: '',
      short_name: '',
      type: '',
      rate: [],
      journal_history: [],
      conference_history: [],
    }
  },
  mounted() {
    if (this.$route.params.id !== undefined) {
      this.initData()
    } else {
      this.edit = true
    }
  },
  computed: {
    typeName() {
      return {
        c: '会议',
        j: '期刊'
      }[this.type]
    },
    getDisplayTitle() {
      if (this.name || this.ename) {
        return this.name ? this.name : this.ename
      } else {
        return '未命名'
      }
    }
  },
  methods: {
    initData() {
      let result = getJournalByUid(this.$route.params.id)
      this.pk = result.pk;
      this.name = result.name ?? '';
      this.ename = result.ename ?? '';
      this.short_name = result.short_name ?? '';
      this.type = result.type ?? '';
      this.rate = result.rate ?? [];
      if (this.type === 'c') {
        this.conference_history = result.history.map(item => {
          item.date = new Date(item.date);
          return item
        })
      } else if (this.type === 'j') {
        this.journal_history = result.history;
      }
    },
    toggleEdit() {
      if (this.edit) {
        if (this.$route.params.id !== undefined) {
          this.initData()
        } else {
          this.$router.back();
        }
      }
      this.edit = !this.edit;
    },
    handleSave() {
      console.info('save');
      console.info(this.$data)
      this.edit = false;
      this.$router.push(`/paper/jc_search/${this.pk}`)
    },
    handleDelete() {
      this.$confirm(`确认删除 ${this.name ? this.name : this.ename} 吗？`, '提醒', {
        type: 'warning',
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        showCancelButton: true,
        closeOnClickModal: false
      }).then(() => {
        journalDelete(this.pk);
        this.$message({
          type: 'success',
          message: '删除成功',
        });
        this.$router.back()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      });
    },
    handleAdd() {
      if (this.type === 'j') {
        this.journal_history.push({y: '', v: '', n: '', cover: ''})
      } else if (this.type === 'c') {
        this.conference_history.push({year: '', date: null, place: ''})
      }
    },
    handleTableDelete(index) {
      if (this.type === 'j') {
        this.journal_history.splice(index, 1)
      } else if (this.type === 'c') {
        this.conference_history.splice(index, 1)
      }
    },
    callRateListGet(q, r) {
      rateListGet(q, r)
    },
    callRateRender(i) {
      return rateRender(i)
    }
  }
}
</script>

<style>
.row {
  margin-bottom: 16px;
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