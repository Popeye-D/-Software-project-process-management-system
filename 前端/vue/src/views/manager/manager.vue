<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('member:add')">添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="项目ID" prop="userID" style="width: 5px;"></el-table-column>
      <el-table-column align="center" label="项目名" prop="pjmID" width="170"></el-table-column>
      <el-table-column align="center" label="项目完成情况" prop="pjmCondition" style="width: 5px;"></el-table-column>
      <el-table-column align="center" label="项目检查时间" style="width: 5px;">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('member:update')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
          <el-button type="danger" icon="delete" @click="deleteArticle(scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempArticle" label-position="left" label-width="80px"
               style='width: 500px; margin-left:50px;'>
        <el-form-item label="用户名" >
          <el-input type="text" v-model="tempArticle.userID">
          </el-input>
        </el-form-item>
        <el-form-item label="项目名" >
          <el-input type="text" v-model="tempArticle.pjmID">
          </el-input>
        </el-form-item>
        <el-form-item label="完成情况" >
          <el-input type="text" v-model="tempArticle.pjmCondition">
          </el-input>
        </el-form-item>
        <!-- <el-form-item label="检查时间">
          <el-input type="text" v-model="tempArticle.dormTime">
          </el-input>
        </el-form-item> -->
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createArticle">添 加</el-button>
        <el-button type="primary" v-else @click="updateArticle">修 改</el-button>
      </div>
      
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          name: ''
        },
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '添加任务完成情况'
        },
        tempArticle: {
          id: '',
          userID: '',
          dormID: '',
          dormClean:'',
          dormTime:''
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('member:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/manager/listmember",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          console.log(data.list);
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getList();
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.tempArticle.userID = "",
        this.tempArticle.dormID = "",
        this.tempArticle.dormClean = "",
        this.tempArticle.dormTime = "",
        this.dialogStatus = "create",
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        //显示修改对话框
        this.tempArticle.id = this.list[$index].id;
        this.tempArticle.userID = this.list[$index].userID;
        this.tempArticle.dormID = this.list[$index].dormID;
        this.tempArticle.dormClean = this.list[$index].dormClean;
        this.tempArticle.dormTime = this.list[$index].dormTime;
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createArticle() {
        //保存新的用户项目完成情况
        this.api({
          url: "/manager/addmember",
          method: "post",
          data: this.tempArticle
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateArticle() {
        //修改用户信息
        this.api({
          url: "/manager/updatemember",
          method: "post",
          data: this.tempArticle
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      deleteArticle($index) {
        //删除用户
        let _vue = this;
        // _vue.tempArticle.id = $index;
        this.$confirm('确定删除此用户信息?'+$index, '提示', {
          confirmButtonText: '确定',
          showCancelButton: false,
          type: 'warning'
          }).then(() => {
            let user = _vue.list[$index];
            _vue.tempArticle.id = user.id;
            _vue.api({
              url: "/manager/deletemember",
              method: "post",
              data: _vue.tempArticle
        }).then(() => {
          _vue.getList()
          // _vue.dialogFormVisible = false
          }).catch(() => {
          _vue.$message.error("删除失败")
          })
        // this.api({
        //   url: "/manager/deletemember",
        //   method: "post",
        //   data: this.tempArticle
        // }).then(() => {
        //   this.getList();
        //   this.dialogFormVisible = false
        })
      }
    }
  }
</script>
