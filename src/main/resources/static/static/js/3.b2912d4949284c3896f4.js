webpackJsonp([3],{E1P1:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=r("Dd8w"),n=r.n(a),s=r("NYxO"),i=r("Nv8u"),u=r("PJh5"),o=r.n(u),c={name:"",data:function(){return{username:"",currentPage:1,pageSize:10,userData:[],userAmount:"",loading:!0}},props:{size:{type:String},sizesmall:{type:Boolean},twHeight:{type:Number}},methods:n()({},Object(s.b)(["setConfig"]),{search:function(){var e=this;i.SearchUserInfo.r(this.username,1,10).then(function(t){"SUCCESS"===t.data.success&&(e.$router.push("/manager?currentPage=1&search="+e.username),e.currentPage=1,e.userAmount=parseInt(t.data.data.totalCount),e.userData=e._formatOrigin(t.data.data.userList))})},handleEdit:function(e,t){this.setConfig(t),this.$router.push("/config?userId="+t.userId+"&currentPage="+this.currentPage+"&search="+this.username)},handleDelete:function(e,t){var r=this;this.$confirm("确认删除？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"info",center:!0}).then(function(){i.DeleteUser.r(t.userId).then(function(e){e.data;r.$router.push("/manager?currentPage="+r.currentPage+"&search="+r.username),i.SearchUserInfo.r(r.username,r.currentPage,10).then(function(e){"SUCCESS"===e.data.success&&(r.loading=!1,r.userAmount=parseInt(e.data.data.totalCount),r.userData=r._formatOrigin(e.data.data.userList))})}).catch(function(e){r.$message.error("未删除，服务器错误"),console.log(e)})}).catch(function(){})},handleCurrentChange:function(e){var t=this;this.currentPage=e,this.$router.push("/manager?currentPage="+e+"&search="+this.username),i.SearchUserInfo.r(this.username,this.currentPage,10).then(function(e){"SUCCESS"===e.data.success&&(t.loading=!1,t.userAmount=parseInt(e.data.data.totalCount),t.userData=t._formatOrigin(e.data.data.userList))})},_formatOrigin:function(e){var t=[];return e.forEach(function(e){var r={};r.userId=e.userInfo.id,r.username=e.userInfo.userName,r.factory=e.userInfo.userFactory,r.ctime=e.userInfo.userCtime,r.role=e.roleInfos[0].roleName||"",t.push(r)}),t},formatterColumn:function(e,t){var r=e[t.property];return void 0==r?"":o()(r).format("YYYY-MM-DD HH:mm:ss")}}),created:function(){var e=this;this.currentPage=parseInt(this.$route.query.currentPage)||1,this.username=this.$route.query.search||"",i.SearchUserInfo.r(this.username,this.currentPage,10).then(function(t){"SUCCESS"===t.data.success&&(e.loading=!1,e.userAmount=parseInt(t.data.data.totalCount),e.userData=e._formatOrigin(t.data.data.userList),e.$router.push("/manager?currentPage="+e.currentPage+"&search="+e.username))})},mounted:function(){}},l={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"user"},[r("div",{staticClass:"user-search"},[r("el-input",{staticClass:"search-input",attrs:{placeholder:"请输入用户名",size:e.size},model:{value:e.username,callback:function(t){e.username=t},expression:"username"}}),e._v(" "),r("el-button",{staticClass:"search-button",attrs:{type:"primary",size:e.size},on:{click:e.search}},[e._v("搜索")])],1),e._v(" "),r("div",{staticClass:"user-table"},[r("el-table",{staticClass:"table-list",staticStyle:{width:"100%"},attrs:{size:e.size,data:e.userData,height:e.twHeight-170,border:""}},[r("el-table-column",{attrs:{prop:"userId",label:"用户id","header-align":"center"}}),e._v(" "),r("el-table-column",{attrs:{prop:"username",label:"用户名","header-align":"center",fixed:""}}),e._v(" "),r("el-table-column",{attrs:{prop:"ctime",width:"100",label:"创建时间","header-align":"center",formatter:e.formatterColumn}}),e._v(" "),r("el-table-column",{attrs:{prop:"role",width:"100",label:"用户角色","header-align":"center"}}),e._v(" "),r("el-table-column",{attrs:{prop:"factory",label:"分厂","header-align":"center"}}),e._v(" "),r("el-table-column",{attrs:{label:"操作",fixed:"right","header-align":"center",width:"150"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(r){e.handleEdit(t.$index,t.row)}}},[e._v("管理")]),e._v(" "),r("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(r){e.handleDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1),e._v(" "),r("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"total, prev, pager, next",total:e.userAmount,small:e.sizesmall},on:{"current-change":e.handleCurrentChange,"update:currentPage":function(t){e.currentPage=t}}})],1)])},staticRenderFns:[]};var h={name:"",data:function(){return{isUser:!0,size:"medium",sizesmall:!1,twHeight:0}},methods:{enterUser:function(){this.isUser=!0},enterAuth:function(){this.isUser=!1}},components:{User:r("VU/8")(c,l,!1,function(e){r("pZsl")},"data-v-20e7cbee",null).exports},created:function(){this.size=window.innerWidth<767?"mini":"medium",this.sizesmall=window.innerWidth<767},mounted:function(){console.log("1"),this.twHeight=this.$refs.tableWrapper.$el.clientHeight}},d={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"manager"},[t("el-card",{ref:"tableWrapper",staticClass:"table-wrapper"},[t("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[this._v("用户角色配置\n        ")]),this._v(" "),this.isUser?t("user",{attrs:{size:this.size,sizesmall:this.sizesmall,twHeight:this.twHeight}}):this._e()],1)],1)},staticRenderFns:[]};var m=r("VU/8")(h,d,!1,function(e){r("M0it")},"data-v-00263f38",null);t.default=m.exports},M0it:function(e,t){},pZsl:function(e,t){}});
//# sourceMappingURL=3.b2912d4949284c3896f4.js.map