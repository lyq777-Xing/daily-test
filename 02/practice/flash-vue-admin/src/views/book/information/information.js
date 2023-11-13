import studentApi from '@/api/book/information.js'
import permission from '@/directive/permission/index.js'
export default {
  //如果需要标签页缓存生效，则需要保证name值和菜单管理中的编码值一致
  name: 'information',
  directives: { permission },
  data() {
    return {
      formVisible: false,
      formTitle: '添加书籍信息',
      isAdd: true,
      form: {
        name:'',
        createBy:'',
        createTime:'',
        modifyBy:'',
        modifyTime:'',
        author:'',
        description:'',
        inventory:'',
        language:'',
        number:'',
        publicationDate:'',
        publishingHouse:'',
        typeid:'',
        id: ''
      },
      listQuery: {
        page: 1,
        limit: 20,
        authorOrName: undefined
      },
      total: 0,
      list: null,
      listLoading: true,
      selRow: {},
      options: [{
        value: '1',
        label: '中文'
      }, {
        value: '2',
        label: '英语'
      }, {
        value: '3',
        label: '其它'
      }],
      rules:{
        name: [
          { required: true, message: '请输入书籍名称', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        language: [
          { required: true, message: '请选择语言', trigger: 'blur' },
        ],
      }
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  computed: {

    //表单验证
    // rules() {
    //   return {
    //     name: [
    //       { required: true, message: this.$t('config.name') + this.$t('common.isRequired'), trigger: 'blur' },
    //       { min: 3, max: 20, message: this.$t('config.name') + this.$t('config.lengthValidation'), trigger: 'blur' }
    //     ],
    //     author: [
    //       { required: true, message: this.$t('config.name') + this.$t('common.isRequired'), trigger: 'blur' },
    //       { min: 3, max: 20, message: this.$t('config.name') + this.$t('config.lengthValidation'), trigger: 'blur' }
    //     ]
    //   }
    // }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
        studentApi.getList(this.listQuery).then(response => {
        this.list = response.data.records
        this.listLoading = false
        this.total = response.data.total
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.id = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleClose() {

    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    fetchPrev() {
      this.listQuery.page = this.listQuery.page - 1
      this.fetchData()
    },
    fetchPage(page) {
      this.listQuery.page = page
      this.fetchData()
    },
    changeSize(limit) {
      this.listQuery.limit = limit
      this.fetchData()
    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {
        name:'',
        createBy:'',
        createTime:'',
        modifyBy:'',
        modifyTime:'',
        author:'',
        description:'',
        inventory:'',
        language:'',
        number:'',
        publicationDate:'',
        publishingHouse:'',
        typeid:'',
        id: ''
      }
    },
    add() {
      this.formTitle = '添加书籍信息'
      this.formVisible = true
      this.isAdd = true

      if(this.$refs['form'] !== undefined) {
        this.$refs['form'].resetFields()
      }
      //如果表单初始化有特殊处理需求,可以在resetForm中处理
          },
    save() {
      var th = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
            const formData = th.form
            console.log("form....");
            if(formData.id){
                studentApi.update(formData).then(response => {
                    this.$message({
                        message: this.$t('common.optionSuccess'),
                        type: 'success'
                    })
                    this.fetchData()
                    this.formVisible = false
                })
            }else{
                studentApi.add(formData).then(response => {
                    this.$message({
                        message: this.$t('common.optionSuccess'),
                        type: 'success'
                    })
                    this.fetchData()
                    this.formVisible = false
                })
            }
        } else {
          return false
        }
      })
    },
    checkSel() {
      if (this.selRow && this.selRow.id) {
        return true
      }
      this.$message({
        message: this.$t('common.mustSelectOne'),
        type: 'warning'
      })
      return false
    },
    editItem(record){
      this.selRow = record
      this.edit()
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        let form = Object.assign({}, this.selRow)
        this.form = form
        this.formTitle = '编辑书籍信息'
        this.formVisible = true

        if(this.$refs['form'] !== undefined) {
          this.$refs['form'].resetFields()
        }
        //如果表单初始化有特殊处理需求,可以在resetForm中处理
              }
    },
    removeItem(record){
      this.selRow = record
      this.remove()
    },
    remove() {
      if (this.checkSel()) {
        var id = this.selRow.id
        this.$confirm(this.$t('common.deleteConfirm'), this.$t('common.tooltip'), {
          confirmButtonText: this.$t('button.submit'),
          cancelButtonText: this.$t('button.cancel'),
          type: 'warning'
        }).then(() => {
            studentApi.remove(id).then(response => {
            this.$message({
              message: this.$t('common.optionSuccess'),
              type: 'success'
            })
            this.fetchData()
          }).catch( err=> {
            this.$notify.error({
              title: '错误',
              message: err
            })
          })
        }).catch(() => {
        })
      }
    }

  }
}
