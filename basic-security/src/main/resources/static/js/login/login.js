var vm = new Vue({
    // 选项
    el: '#loginVm',
    data: {
        title: null,
        user: {
            userName:null,
            password:null
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


        submit:function () {
            if(!vm.user.userName)
            {
                toastr.warning("请输入用户名或者邮箱");
                return;
            }
            if(!vm.user.password){
                toastr.warning("请输入密码");
                return;
            }

            var formData = JSON.stringify(vm.user); // 这里才是你的表单数据

            $.ajax({
                type:'post',
                url:'/login',
                data: JSON.stringify(vm.user),
                contentType: "application/json",
                success:function (data) {
                    if(data.status===200){
                        toastr.success(data.msg);
                        window.location.href = "userList";
                    }else{
                        toastr.warning(data.msg);
                    }
                },
                error:function () {
                    toastr.warn("服务器异常，请联系管理员");
                }
            });
        }
    }
});