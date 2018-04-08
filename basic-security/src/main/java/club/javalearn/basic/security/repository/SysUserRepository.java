package club.javalearn.basic.security.repository;

import club.javalearn.basic.security.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * 用户持久化
 *
 * @author king-pan
 * @date 2018-04-02
 **/
public interface SysUserRepository extends JpaRepository<SysUser,Integer>,JpaSpecificationExecutor<SysUser>,QuerydslPredicateExecutor<SysUser> {
    /**
     * 通过用户名获取用户信息
     * @param userName 用户信息
     * @return 用户信息
     */
    SysUser findByUserName(String userName);
}
