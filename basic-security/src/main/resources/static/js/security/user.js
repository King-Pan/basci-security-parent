var userObj = {
   initTable: function () {
       $('#userTable').bootstrapTable({
           columns: [
               { field: 'userId', title: '用户编号' },
               { field: 'name', title: '用户名称' },
               { field: 'userName', title: '用户登录名' },
               { field: 'status', title: '状态' },
               { field: 'createTime', title: '创建时间' },
               { field: 'updateTime', title: '更新时间' }
           ],
           url: baseUrl +'users'
       });
   }
};
$(function () {
    userObj.initTable();
});