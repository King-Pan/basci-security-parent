package club.javalearn.basic.security.web.controller;

import club.javalearn.basic.security.common.Message;
import club.javalearn.basic.security.domain.SysUser;
import club.javalearn.basic.security.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@Controller
public class SysUserController {


    @Autowired
    private SysUserService userService;

    @GetMapping(value = {"/user/", "/user"})
    public ModelAndView userPage() {
        return new ModelAndView("security/user");
    }


    @GetMapping("/users")
    @RequiresPermissions("user:view")
    @ResponseBody
    public Message<SysUser> userList(@RequestBody SysUser user,@PageableDefault Pageable pageable) {
        return userService.getList(user, pageable);
    }

    /**
     * 用户查询.
     *
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo() {
        return "userInfo";
    }

    /**
     * 用户添加;
     *
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd() {
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     *
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String userDel() {
        return "userInfoDel";
    }
}
