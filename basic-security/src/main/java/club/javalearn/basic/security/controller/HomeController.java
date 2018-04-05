package club.javalearn.basic.security.controller;

import club.javalearn.basic.security.common.ServerResponse;
import club.javalearn.basic.security.domain.SysUser;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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


    @GetMapping(value="/login")
    public ModelAndView login(){
        return new ModelAndView("login_m");
    }

    @GetMapping(value = {"/","/index","/home"})
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @PostMapping(value="/login")
    public ServerResponse login(HttpServletRequest request, @RequestBody SysUser user, Model model){
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
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            subject.login(token);
            response = ServerResponse.createBySuccessMessage("登录成功");
            response.setUrl("userList");
        }catch (LockedAccountException lae) {
            token.clear();
            response = ServerResponse.createByErrorMessage("登录失败: 用户已经被锁定不能登录，请与管理员联系！");
        } catch (AuthenticationException e) {
            token.clear();
            response = ServerResponse.createByErrorMessage("登录失败: 用户或密码不正确");
        }
        return response;
    }


    @GetMapping("/403")
    public ServerResponse unauthorizedRole(){
        return ServerResponse.createByUnAuthorized();
    }
}
