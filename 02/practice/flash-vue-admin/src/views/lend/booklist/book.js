import studentApi from '@/api/lend/book.js'
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
        id: '',
        status:'',
        lendid:''
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
      },
      // 添加借阅数据的对象
      lendData:{
        name:'',
        createBy:'',
        createTime:'',
        modifyBy:'',
        modifyTime:'',
        id: '',
        bookid:'',
        returnTime:'',
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
        id: '',
        status:'',
        lendid:''
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

    lendItem(record){
      this.selRow = record
      this.lend()
    },
    lend() {
      if (this.checkSel()) {
        const data = this.selRow
        this.$confirm("您确认借阅该图书？", this.$t('common.tooltip'), {
          confirmButtonText: this.$t('button.submit'),
          cancelButtonText: this.$t('button.cancel'),
          type: 'warning'
        }).then(() => {
          data.status = 1
          studentApi.update(data).then(response => {
            this.lendData.bookid = this.selRow.id;
            studentApi.add(this.lendData).then(response => {
              this.$message({
                  message: this.$t('common.optionSuccess'),
                  type: 'success'
              })
              this.fetchData()
            })
          }).catch( err=> {
            this.$notify.error({
              title: '错误',
              message: err
            })
          })
          
        }).catch(() => {
        })
      }
    },
    backItem(record){
      this.selRow = record
      this.back()
    },
    back() {
      if (this.checkSel()) {
        const data = this.selRow
        var id = this.selRow.id
        this.$confirm("您确认归还该图书？", this.$t('common.tooltip'), {
          confirmButtonText: this.$t('button.submit'),
          cancelButtonText: this.$t('button.cancel'),
          type: 'warning'
        }).then(() => {
            data.status = 2
            console.log(data);
            studentApi.update(data).then(response => {
              studentApi.updateLend(data.id).then(response => {
                  this.$message({
                      message: this.$t('common.optionSuccess'),
                      type: 'success'
                  })
                  this.fetchData()
              })
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
