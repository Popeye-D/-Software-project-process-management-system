<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-if="hasPerm('absent:add')">添加</el-button>
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





      <el-table-column align="center" label="用户ID" prop="userID" style="width: 5px;"></el-table-column>
       <el-table-column align="center" label="用户名" prop="nickname" style="width: 5px;"></el-table-column>
      <el-table-column align="center" label="缺勤时间" prop="absenceTime" style="width: 5px;">
        <!-- <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template> -->
      </el-table-column>
      <el-table-column align="center" label="备注" prop="message" width="170"></el-table-column>
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





        <el-form-item label="用户ID" >
          <el-input type="text" v-model="tempArticle.userID">
          </el-input>
        </el-form-item>

         <el-form-item label="用户名" >
          <el-input type="text" v-model="tempArticle.nickname">
          </el-input>
        </el-form-item>

        <el-form-item label="缺勤时间" >
          <el-input type="date" v-model="tempArticle.absenceTime" >
          </el-input>
        </el-form-item>
        <el-form-item label="备注" >
          <el-input type="text" v-model="tempArticle.message">
       
          </el-input>
        </el-form-item>
        <!-- <el-form-item label="检查时间">
          <el-input type="text" v-model="tempArticle.Time">
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
          create: '添加缺勤情况'
        },
        tempArticle: {
          id: '',
          userID: '',
          nickname:'',
          absenceTime:'',
          message:''
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('absent:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/manager/listAbsent",
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
        this.tempArticle.nickname = "",
        this.tempArticle.message = "",
        this.tempArticle.absenceTime = "",
        this.dialogStatus = "create",
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        //显示修改对话框
        this.tempArticle.id = this.list[$index].id;
        this.tempArticle.userID = this.list[$index].userID;
         this.tempArticle.nickname = this.list[$index].nickname;
        this.tempArticle.message = this.list[$index].message;
        this.tempArticle.absenceTime = this.list[$index].absenceTime.toString();
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createArticle() {
        //新的缺勤记录
        this.api({
          url: "/manager/addAbsent",
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
          url: "/manager/updateAbsent",
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
              url: "/manager/deleteAbsent",
              method: "post",
              data: _vue.tempArticle
        }).then(() => {
          _vue.getList()
          // _vue.dialogFormVisible = false
          }).catch(() => {
          _vue.$message.error("删除失败")
          })
        })
      }
    }
  }
</script>
