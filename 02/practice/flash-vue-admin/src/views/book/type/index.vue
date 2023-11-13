<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input
            v-model="listQuery.id"
            size="mini"
            placeholder="请输入id"
          ></el-input>
        </el-col>
        <el-col :span="6">
          <el-button
            type="success"
            size="mini"
            icon="el-icon-search"
            @click.native="search"
          >{{ $t('button.search') }}</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-refresh"
            @click.native="reset"
          >{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button
            type="success"
            size="mini"
            icon="el-icon-plus"
            @click.native="add"
          >{{ $t('button.add') }}</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click.native="edit"
          >{{ $t('button.edit') }}</el-button>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click.native="remove"
          >{{ $t('button.delete') }}</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table
      :data="list"
      v-loading="listLoading"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      @current-change="handleCurrentChange"
    >
      <!-- :default-sort = "{prop: 'num', order: 'descending'}" -->
      <el-table-column type="index">
      </el-table-column>
      <el-table-column label="分类名称">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="创建人">
        <template slot-scope="scope">
          {{scope.row.createBy}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间/注册时间">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>
      <el-table-column label="最后更新人">
        <template slot-scope="scope">
          {{scope.row.modifyBy}}
        </template>
      </el-table-column>
      <el-table-column label="最后更新时间">
        <template slot-scope="scope">
          {{scope.row.modifyTime}}
        </template>
      </el-table-column>
      <el-table-column label="排序" prop="num" sortable>
        <template slot-scope="scope">
          {{scope.row.num}}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="mini"
            icon="el-icon-edit"
            @click.native="editItem(scope.row)"
          >{{ $t('button.edit') }}</el-button>
          <el-button
            type="text"
            size="mini"
            icon="el-icon-delete"
            @click.native="removeItem(scope.row)"
          >{{ $t('button.delete') }}</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 50, 100,500]"
      :page-size="listQuery.limit"
      :total="total"
      @size-change="changeSize"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext"
    >
    </el-pagination>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%"
    >
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="170px"
      >
        <el-row>
          <el-col :span="12">
            <el-form-item label="分类名" prop="name">
              <el-input
                v-model="form.name"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建人">
              <el-input
                v-model="form.createBy"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="创建时间/注册时间">
              <div class="block">
                <el-input disabled>{{form.createTime}}</el-input>
              </div>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="最后更新人">
              <el-input
                v-model="form.modifyBy"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col><el-col :span="12">
            <el-form-item label="最后更新时间">
              <el-input disabled>{{form.modifyTime}}</el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="图书ID">
              <el-input
                v-model="form.bookid"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归还时间">
              <el-input
                v-model="form.returnTime"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button
            type="primary"
            @click="save"
          >{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script src="./type.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
@import "src/styles/common.scss";
</style>

