package club.javalearn.basic.security.service.impl;

import club.javalearn.basic.security.common.BootstrapMessage;
import club.javalearn.basic.security.common.Message;
import club.javalearn.basic.security.domain.SysUser;
import club.javalearn.basic.security.repository.SysUserRepository;
import club.javalearn.basic.security.service.SysUserService;
import club.javalearn.basic.security.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Message<SysUser> getList(SysUser user, Pageable pageable) {
        BootstrapMessage<SysUser> message = new BootstrapMessage<>();
        Sort sort = new Sort(Sort.Direction.DESC, "updateDate");
        sort.and(new Sort(Sort.Direction.ASC,"status"));
        sort.and(new Sort(Sort.Direction.ASC,"userId"));
        Pageable pageableRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize() , sort);
        Page<SysUser> page = userRepository.findAll(new Specification<SysUser>(){
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> nickNamePath = root.get("nickName");
                Path<String> userNamePath = root.get("userName");
                Path<String> statusPath = root.get("status");
                List<Predicate> wherePredicate = new ArrayList<>();
                //final User user = convertUser(param);
                if(user!=null){
                    if(StringUtils.isNoneBlank(user.getNickName())){
                        wherePredicate.add(cb.like(nickNamePath,"%"+user.getNickName()+"%"));
                    }
                    if(StringUtils.isNoneBlank(user.getUserName())){
                        wherePredicate.add(cb.like(userNamePath,"%"+user.getUserName()+"%"));
                    }
                    if(StringUtils.isNoneBlank(user.getStatus()) && !Constant.ALL_STATUS.equals(user.getStatus())){
                        wherePredicate.add(cb.equal(statusPath,user.getStatus()));
                    }
                }

                Predicate[] predicates = new Predicate[]{};
                //这里可以设置任意条查询条件
                if (!CollectionUtils.isEmpty(wherePredicate)){
                    query.where(wherePredicate.toArray(predicates));
                }
                //这种方式使用JPA的API设置了查询条件，所以不需要再返回查询条件Predicate给Spring Data Jpa，故最后return null;即可。
                return null;
            }
        },pageableRequest);
        List<SysUser> list = userRepository.findAll();
        message.setRows(list);
        message.setLimit(page.getSize());
        message.setStart(page.getNumber());
        message.setTotal(page.getTotalElements());

        return message;
    }
}
