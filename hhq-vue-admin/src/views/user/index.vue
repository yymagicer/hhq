<template>
  <div class="app-container">
    <el-header>
      <div class="el-row">
        <div class="el-col el-col-24">
          <div class="el-card is-always-shadow">
            <div class="el-card__header">
              <div class="clearfix">
                <span>查询条件</span>
              </div>
            </div>
            <div class="el-card__body">
              <form class="el-form demo-form-inline el-form--label-right el-form--inline">
                <div class="el-form-item">
                  <el-input placeholder="请输入内容" clearable></el-input>
                </div>
                <div class="el-form-item">
                  <el-date-picker
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                  ></el-date-picker>
                  <el-button type="primary" icon="el-icon-search">搜索</el-button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </el-header>
    <el-main>
      <div class="el-row">
        <div class="el-col el-col-24">
          <div class="el-card box-card is-always-shadow">
            <div class="el-card__header">
              <div class="clearfix">
                <div class="mv-list-buttons float-r">
                  <button
                    type="button"
                    class="el-button el-button--primary el-button--small"
                    title="新增"
                    @click="dialogFormVisible=true"
                  >
                    <!---->
                    <i class="el-icon-plus"></i>
                    <!---->
                  </button>
                  <button
                    type="button"
                    class="el-button el-button--primary el-button--small"
                    title="删除"
                  >
                    <!---->
                    <i class="el-icon-delete"></i>
                    <!---->
                  </button>
                </div>
              </div>
            </div>
            <div class="el-card__body">
              <div class="el-row">
                <div
                  class="el-table el-table--fit el-table--enable-row-transition"
                  style="width: 100%;"
                >
                  <el-table
                    ref="multipleTable"
                    :data="resultList"
                    tooltip-effect="dark"
                    style="width: 100%"
                  >
                    <el-table-column type="selection" width="55"></el-table-column>
                    <el-table-column prop="userName" label="姓名" width="120"></el-table-column>
                    <el-table-column prop="mobile" label="手机号" show-overflow-tooltip></el-table-column>
                    <el-table-column prop="email" label="电子邮箱" show-overflow-tooltip></el-table-column>
                    <el-table-column label="操作">
                      <template slot-scope="scope">
                        <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button
                          size="mini"
                          type="danger"
                          @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </div>
                <div class="page-navigation"></div>
              </div>
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                :page-count="pageTotal"
              ></el-pagination>
            </div>
          </div>
        </div>
      </div>
      <el-dialog :title="dialogStatus" :visible.sync="dialogFormVisible">
        <el-form :model="editForm" label-width="80px" :rules="rules" ref="editForm">
          <el-form-item label="姓名" prop="common">
            <el-input v-model="editForm.userName" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="common">
            <el-input v-model="editForm.password" type="password"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="common">
            <el-radio-group v-model="editForm.sex">
              <el-radio class="radio" :label="0">男</el-radio>
              <el-radio class="radio" :label="1">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="手机号" prop="mobile">
            <el-input v-model="editForm.mobile"></el-input>
          </el-form-item>
          <el-form-item label="电子邮箱">
            <el-input v-model="editForm.email"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input-number v-model="editForm.age" :min="0" :max="200"></el-input-number>
          </el-form-item>
          <el-form-item label="生日">
            <el-date-picker type="date" placeholder="选择日期" v-model="editForm.birth"></el-date-picker>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="editForm.addr"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click.native="dialogFormVisible=false">取消</el-button>
          <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">添加</el-button>
          <el-button v-else type="primary" @click="updateData">修改</el-button>
          <!-- <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button> -->
        </div>
      </el-dialog>
    </el-main>
  </div>
</template> 
<script>
import request from "@/utils/request";
import customValid from "@/utils/customValidate";
export default {
  data() {
    return {
      options: {
        label: "",
        value: ""
      },
      key: "",
      label: "",
      value: "",
      dialogFormVisible: false,
      dialogStatus: "",
      editForm: {},
      resultList: [],
      //当前页数
      pageNum: 1,
      //每页显示的条数
      pageSize: 10,
      //总条数
      total: 1,
      //总页数
      pageTotal: 1,
      rules: {
        common: [{ required: true, message: "不能为空", trigger: "blur" }],
        mobile: [
          { required: true, message: "不能为空", trigger: "blur" },
          {
            validator: customValid.isMobile,
            message: "填写正确的手机号",
            trigger: "blur"
          }
        ],
        number: [
          { required: true, message: "不能为空", trigger: "blur" },
          {
            validator: customValid.isInteger,
            message: "填写正确的数值",
            trigger: "blur"
          }
        ]
      }
    };
  },

  methods: {
    handleSizeChange(val) {
      let self = this;
      self.pageSize = val;
      self.queryList();
    },
    handleCurrentChange(val) {
      let self = this;
      self.pageNum = val;
      self.queryList();
    },
    queryList() {
      var self = this;
      request({
        url: "/user/queryListByPage",
        method: "post",
        data: {
          pageSize: self.pageSize,
          pageNum: self.pageNum
        }
      }).then(function(res) {
        self.pageNum = res.pageNum;
        self.pageSize = res.pageSize;
        self.total = res.total;
        self.pageTotal = res.pageTotal;
        self.resultList = res.resultList;
      });
    },
    handleEdit(index, row) {
      let self = this;
      self.dialogFormVisible = true;
    },
    handleDelete(index, row) {},
    createData() {},
    updateData() {}
  },
  mounted: function() {
    let self = this;
    self.queryList();
  }
};
</script>