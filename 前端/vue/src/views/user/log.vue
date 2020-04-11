<template>
  <div class="app-container">
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row >
      <el-table-column align="center" label="序号"style="width: 2px;">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" prop="username" style="width: 5px;"></el-table-column>
      <el-table-column align="center" label="操作时间" style="width: 5px;">
        <template slot-scope="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作内容" prop="operation" style="width: 5px;"></el-table-column>
      <el-table-column align="center" label="操作" prop="content" style="width: 5px;"></el-table-column>
      <el-table-column align="center" label="执行的SQL语句" prop="sqlC" style="width: 5px;" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="操作的IP" prop="ip" style="width: 8px;"></el-table-column>
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
          username:'',
          operation: '',
          content:'',
          sqlC:'',
          ip: ''
        }
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('log:list')) {
          return
        }
        this.listLoading = true;
        this.api({
          url: "/log/listLog",
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
