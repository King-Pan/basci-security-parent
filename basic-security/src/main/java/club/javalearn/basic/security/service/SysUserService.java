package club.javalearn.basic.security.service;

import club.javalearn.basic.security.common.Message;
import club.javalearn.basic.security.domain.SysUser;
import org.springframework.data.domain.Pageable;


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


    /**
     * 分页查询用户信息
     * @param user 查询参数
     * @return 用户分页信息
     */
   Message<SysUser> getList(SysUser user, Pageable pageable);
}
