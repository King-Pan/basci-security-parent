package club.javalearn.basic.security.web.controller;

import club.javalearn.basic.security.common.Message;
import club.javalearn.basic.security.common.ServerResponse;
import club.javalearn.basic.security.domain.SysUser;
import club.javalearn.basic.security.service.SysUserService;
import club.javalearn.basic.security.utils.Constant;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@Api(value = "用户相关接口",
        description = "用户信息操作API",
        tags = "UserApi",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class SysUserController {


    @Autowired
    private SysUserService userService;

    @GetMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("login_m");
    }

    @PostMapping(value = "/login")
    public ServerResponse login(HttpServletRequest request, @RequestBody SysUser user, Model model) {
        ServerResponse response;

//        if (StringUtils.isBlank(user.getCode())){
//            response = ServerResponse.createByErrorMessage("登录失败: 验证码为空");
//            return response;
//        }else{
//            //生成的验证码
//            String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//            if(!code.equals(user.getCode())){
//                response = ServerResponse.createByErrorMessage("登录失败: 验证码不正确");
//                return response;
//            }
//        }

        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            response = ServerResponse.createByErrorMessage("登录失败: 用户名或密码不能为空");
            return response;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        if (request.getParameter(Constant.REMEBER_ME) != null) {
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            response = ServerResponse.createBySuccessMessage("登录成功");
            response.setUrl("index");
        } catch (LockedAccountException lae) {
            token.clear();
            response = ServerResponse.createByErrorMessage("登录失败: 用户已经被锁定不能登录，请与管理员联系！");
        } catch (AuthenticationException e) {
            token.clear();
            response = ServerResponse.createByErrorMessage("登录失败: 用户或密码不正确");
        }
        return response;
    }

    @ApiOperation(value = "用户页面")
    @RequiresPermissions("user")
    @GetMapping(value = {"/user/", "/user"})
    public ModelAndView userPage() {
        return new ModelAndView("security/user");
    }


    @ApiOperation(value = "用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户查询参数", required = true, dataType = "SysUser"),
            @ApiImplicitParam(name = "pageable", value = "分页参数", required = true, dataType = "Pageable")
    })
    @GetMapping("/users")
    @RequiresPermissions("user:view")
    public Message<SysUser> userList(SysUser user, @PageableDefault Pageable pageable) {
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
