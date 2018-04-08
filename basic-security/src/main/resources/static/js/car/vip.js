var vipObj = {
    initTable: function () {
        $('#vipTable').bootstrapTable({
            columns: [
                { field:'state', checkbox:true},''
                { field: 'id', title: 'VIP编号' },
                { field: 'name', title: '用户昵称' },
                { field: 'phoneNum', title: '电话号码' },
                { field: 'idCard', title: '身份证号码' },
                { field: 'balance', title: '余额' },
                { field: 'createTime', title: '入会时间' },
                { field: 'updateTime', title: '最后充值时间' }
            ],
            method: 'get',                      //请求方式（*）
            url: baseUrl +'car/vips',
            queryParams: queryParams,      //传递参数（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            contentType: "application/x-www-form-urlencoded",
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            height: 580,                       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "userId",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "server"           //分页方式：client客户端分页，server服务端分页（*）
        });
    }
};
$(function () {
    vipObj.initTable();
});

function queryParams(params){
    return {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        size: params.limit,   //页面大小
        page: (params.offset / params.limit),
        status: '-1',
        userName: '',
        nickName: ''
    };
}