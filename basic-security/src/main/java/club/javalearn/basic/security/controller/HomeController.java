package club.javalearn.basic.security.controller;

import club.javalearn.basic.security.common.ServerResponse;
import club.javalearn.basic.security.domain.SysUser;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@RestController
public class HomeController {

    @GetMapping(value="/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping(value="/login")
    public ServerResponse login(HttpServletRequest request, @RequestBody SysUser user, Model model){
        ServerResponse response;

        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            response = ServerResponse.createByErrorMessage("登录失败: 用户名或密码不能为空");
            return response;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            subject.login(token);
            response = ServerResponse.createBySuccessMessage("登录成功");
        }catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            response = ServerResponse.createByErrorMessage("登录失败: 用户已经被锁定不能登录，请与管理员联系！");
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            response = ServerResponse.createByErrorMessage("登录失败: 用户或密码不正确");
        }
        return response;
    }

    @GetMapping("/403")
    public ServerResponse unauthorizedRole(){
        return ServerResponse.createByUnAuthorized();
    }
}
