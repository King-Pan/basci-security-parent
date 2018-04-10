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
        if(request.getParameter(Constant.REMEBER_ME)!=null){
            token.setRememberMe(true);
        }
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
