package club.javalearn.basic.security.service;

import club.javalearn.basic.security.domain.SysUser;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
public interface SysUserService {
    /**
     * 通过用户名获取用户信息
     * @param userName 用户名
     * @return 用户信息
     */
   SysUser findByUserName(String userName);
}
