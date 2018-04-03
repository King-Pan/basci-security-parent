var vm = new Vue({
    // 选项
    el: '#loginVm',
    data: {
        title: null,
        loginUser: {

        },
        registerUser:{

        },
        boxShow:true
    },
    methods: {
        /**
         * 切换登录注册界面
         * @param flag 切换标识
         */
        switchBox:function (flag) {
            vm.boxShow = flag;
        },
        login:function () {
            toastr.success("登录成功");
        }
    }
});