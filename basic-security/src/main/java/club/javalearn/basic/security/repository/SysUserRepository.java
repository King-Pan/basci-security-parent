package club.javalearn.basic.security.repository;

import club.javalearn.basic.security.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {
    /**
     * 通过用户名获取用户信息
     * @param userName 用户信息
     * @return 用户信息
     */
    SysUser findByUserName(String userName);
}
