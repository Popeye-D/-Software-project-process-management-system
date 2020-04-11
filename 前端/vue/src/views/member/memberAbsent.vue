<!-- 缺勤登记 -->

<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
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
      <el-table-column align="center" label="缺勤时间" prop="absenceTime" style="width: 5px;"></el-table-column>
      <el-table-column align="center" label="备注" prop="message" style="width: 5px;"></el-table-column>
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
        if (!this.hasPerm('absent:select')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/member/getAbsentInfo",
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
    }
  }
</script>
