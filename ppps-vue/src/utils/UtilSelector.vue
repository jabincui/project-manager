<template>
  <el-select
      v-model="value"
      value-key="pk"
      :multiple="multiple"
      filterable
      remote
      reserve-keyword
      :allow-create="allowNew"
      :size="size"
      :placeholder="placeholder"
      :remote-method="debouncedClick"
      :loading="loading"
      @focus="debouncedClick('')"
  >
    <el-option
        v-for="item in newOptions"
        :key="item.pk"
        :label="this.render(item)"
        :value="item">
    </el-option>
  </el-select>
</template>

<script>
const _ = require('lodash');
export default {
  name: "UtilSelector",
  props: {
    placeholder: {
      type: String,
      default: ' ',
    },
    multiple: Boolean,
    modelValue: [Array, Object],
    allowNew: Boolean,
    getter: Function,
    render: Function,
    builder: Function,
    size: String,
  },
  emits: ['update:modelValue'],
  created() {
    this.debouncedClick = _.debounce(this.remoteMethod, 500)
  },
  mounted() {
    this.remoteMethod('')
  },
  unmounted() {
    this.debouncedClick.cancel()
  },
  data() {
    return {
      options: [],
      loading: false,
    }
  },
  computed: {
    value: {
      get() {
        return (this.multiple || !(this.modelValue instanceof Array)) ? this.modelValue : this.modelValue[0]
      },
      set(value) {
        let res = this.multiple ? [...value] : value;
        if(this.allowNew){
          res = this.multiple ? res.map(item => {
            return typeof(item)=='string' ? this.builder(item) : item
          }) : (typeof(res)=='string' ? this.builder(res) : res)
        }
        this.$emit('update:modelValue', (this.multiple || !(this.modelValue instanceof Array)) ? res : [res])
      }
    },
    newOptions(){
      let key_list = this.options.map(item => item.pk)
      if(this.modelValue instanceof Array){
        return this.options.concat(this.modelValue.filter(item => !key_list.includes(item.pk)))
      }else if(this.modelValue){
        return this.options.concat([this.modelValue].filter(item => !key_list.includes(item.pk)))
      }else{
        return this.options
      }
    }
  },
  methods: {
    remoteMethod(query){
        this.loading = true;
        this.getter(query, (r) => {
          this.options = r;
          this.loading = false;
        })
      }
  }
}
</script>
