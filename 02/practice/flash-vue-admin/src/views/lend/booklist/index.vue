<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input
            v-model="listQuery.authorOrName"
            size="mini"
            placeholder="请输入书名或作者"
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
      <!-- <el-row>
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
      </el-row> -->
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
      <el-table-column type="index">
      </el-table-column>
      <el-table-column label="书名">
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
      <el-table-column label="作者">
        <template slot-scope="scope">
          {{scope.row.author}}
        </template>
      </el-table-column>
      <el-table-column label="描述">
        <template slot-scope="scope">
          {{scope.row.description}}
        </template>
      </el-table-column>
      <el-table-column label="总数">
        <template slot-scope="scope">
          {{scope.row.inventory}}
        </template>
      </el-table-column>
      <el-table-column label="语言">
        <template slot-scope="scope">
           <span v-if="scope.row.language == 1">中文</span>
           <span v-if="scope.row.language == 2">英文</span>
           <span v-if="scope.row.language == 3">其它</span>
        </template>
      </el-table-column>
      <el-table-column label="编号">
        <template slot-scope="scope">
          {{scope.row.number}}
        </template>
      </el-table-column>
      <el-table-column label="出版日期">
        <template slot-scope="scope">
          {{scope.row.publicationDate}}
        </template>
      </el-table-column>
      <el-table-column label="出版社">
        <template slot-scope="scope">
          {{scope.row.publishingHouse}}
        </template>
      </el-table-column>
      <el-table-column label="类别ID">
        <template slot-scope="scope">
          {{scope.row.typeid}}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="mini"
            @click.native="lendItem(scope.row)"
          >借阅图书</el-button>
          <el-button
            type="text"
            size="mini"
            @click.native="backItem(scope.row)"
          >归还图书</el-button>
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

    <!-- <el-dialog
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
            <el-form-item label="书名" prop="name">
              <el-input
                v-model="form.name"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建人"  prop="createBy">
              <el-input
                v-model="form.createBy"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建时间/注册时间" prop="createTime" >
              <div class="block">
                <el-input disabled>{{form.createTime}}</el-input>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最后更新人" prop="modifyBy">
              <el-input
                v-model="form.modifyBy"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col><el-col :span="12">
            <el-form-item label="最后更新时间" prop="modifyTime">
              <el-input disabled>{{form.modifyTime}}</el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="作者" prop="author">
              <el-input
                v-model="form.author"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="描述" prop="description">
              <el-input
                v-model="form.description"
                type="textarea"
                :rows="3"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总数" prop="inventory">
              <el-input
                v-model="form.inventory"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="语言" prop="language">
              <el-select v-model="form.language" placeholder="请选择语言">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="编号" prop="number">
              <el-input
                v-model="form.number"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出版日期">
              <div class="block">
                <el-date-picker
                  value-format="yyyy-MM-dd"
                  v-model="form.publicationDate"
                  type="date"
                  placeholder="选择出版日期">
                </el-date-picker>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出版社" prop="publishingHouse">
              <el-input
                v-model="form.publishingHouse"
                minlength=1
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类别ID" prop="typeid">
              <el-input
                v-model="form.typeid"
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
    </el-dialog> -->
  </div>
</template>

<script src="./book.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
@import "src/styles/common.scss";
</style>

