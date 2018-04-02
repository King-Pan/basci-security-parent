package club.javalearn.basic.security.service.impl;

import club.javalearn.basic.security.domain.SysUser;
import club.javalearn.basic.security.repository.SysUserRepository;
import club.javalearn.basic.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * basci-security-parent
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository userRepository;

    @Override
    public SysUser findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
