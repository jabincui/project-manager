<template>
  <el-select
      v-model="value"
      value-key="pk"
      :multiple="multiple"
      filterable
      remote
      reserve-keyword
      :placeholder="placeholder"
      :remote-method="remoteMethod"
      :loading="loading">
    <el-option
        v-for="item in options"
        :key="item"
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
    modelValue: [Array, Object],
    placeholder: {
      type: String,
      default: ' ',
    },
    multiple: Boolean,
    allowNew: Boolean,
    getter: Function,
    render: Function,
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
        return this.modelValue
      },
      set(value) {
        this.$emit('update:modelValue', value)
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
