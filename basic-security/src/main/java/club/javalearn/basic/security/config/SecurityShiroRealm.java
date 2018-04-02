package club.javalearn.basic.security.config;

import club.javalearn.basic.security.domain.SysPermission;
import club.javalearn.basic.security.domain.SysRole;
import club.javalearn.basic.security.domain.SysUser;
import club.javalearn.basic.security.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 自定义认证和授权器
 * @author king-pan
 * @date 2018-04-02
 **/
@Slf4j
public class SecurityShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService userInfoService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("-->授权管理<--  begin");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser userInfo  = (SysUser)principals.getPrimaryPrincipal();
        log.info("登录用户信息: " + userInfo);
        for(SysRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRoleCode());
            for(SysPermission p:role.getPermissions()){
                log.info("-->添加用户角色<--" + p.getPermission());
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        log.info("-->授权管理<--  end");
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        log.info("-->身份认证<--  begin");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser userInfo = userInfoService.findByUserName(username);
        log.info("登录用户信息: " + userInfo);
        if(userInfo == null){
            throw new UnknownAccountException("账号不存在");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                userInfo,
                //密码
                userInfo.getPassword(),
                //用户名+salt
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
                //realm名称
                getName()
        );
        log.info("-->身份认证<--  end");
        return authenticationInfo;
    }

    @Override
    public String getName() {
        return "shiro-security-realm";
    }
}
