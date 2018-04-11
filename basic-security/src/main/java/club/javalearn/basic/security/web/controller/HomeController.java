package club.javalearn.basic.security.web.controller;

import club.javalearn.basic.security.common.ServerResponse;
import club.javalearn.basic.security.domain.SysUser;
import club.javalearn.basic.security.utils.Constant;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@RestController
public class HomeController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping(value = {"/","/index","/home"})
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/403")
    public ServerResponse unauthorizedRole(){
        return ServerResponse.createByUnAuthorized();
    }
}
