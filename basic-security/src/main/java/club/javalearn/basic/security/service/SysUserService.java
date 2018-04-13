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
     * 判断用户名是否存在
     * @param userName 用户名
     * @return 存在返回true，不存在返回false
     */
   Boolean existsUser(String userName);


    /**
     * 分页查询用户信息
     * @param user 查询参数
     * @param pageable 分页参数
     * @return 用户分页信息
     */
   Message<SysUser> getList(SysUser user, Pageable pageable);

    /**
     * 新增或者更新用户信息
     * @param user 用户信息
     * @return 用户信息
     */
   SysUser save(SysUser user);


    /**
     * 删除用户
     * @param user 删除用户
     * @return 删除成功返回true，返回失败返回false
     */
   boolean delete(SysUser user);

    /**
     * 通过用户ID删除用户
     * @param userId 用户ID
     * @return 删除成功返回true，返回失败返回false
     */
   boolean delete(Long userId);

}
