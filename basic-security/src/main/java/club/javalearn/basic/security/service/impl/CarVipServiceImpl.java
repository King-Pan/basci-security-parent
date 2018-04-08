package club.javalearn.basic.security.service.impl;

import club.javalearn.basic.security.common.BootstrapMessage;
import club.javalearn.basic.security.common.Message;
import club.javalearn.basic.security.domain.CarVip;
import club.javalearn.basic.security.repository.CarVipRepository;
import club.javalearn.basic.security.service.CarVipService;
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

@Service
public class CarVipServiceImpl implements CarVipService {

    @Autowired
    private CarVipRepository vipRepository;

    @Override
    public Message<CarVip> getList(CarVip vip, Pageable pageable) {
        BootstrapMessage<CarVip> message = new BootstrapMessage<>();
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        sort.and(new Sort(Sort.Direction.ASC,"status"));
        sort.and(new Sort(Sort.Direction.ASC,"userId"));
        Pageable pageableRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize() , sort);
        Page<CarVip> page = vipRepository.findAll(new Specification<CarVip>(){
            @Override
            public Predicate toPredicate(Root<CarVip> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> namePath = root.get("name");
                Path<String> phoneNumPath = root.get("phoneNum");
                Path<String> idCardPath = root.get("idCard");
                List<Predicate> wherePredicate = new ArrayList<>();
                //final User user = convertUser(param);
                if(vip!=null){
                    if(StringUtils.isNoneBlank(vip.getName())){
                        wherePredicate.add(cb.like(namePath,"%"+vip.getName()+"%"));
                    }
                    if(StringUtils.isNoneBlank(vip.getPhoneNum())){
                        wherePredicate.add(cb.like(phoneNumPath,"%"+vip.getPhoneNum()+"%"));
                    }
                    if(StringUtils.isNoneBlank(vip.getIdCard())){
                        wherePredicate.add(cb.equal(idCardPath,vip.getIdCard()));
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
        message.setRows(page.getContent());
        message.setLimit(page.getSize());
        message.setStart(page.getNumber());
        message.setTotal(page.getTotalElements());

        return message;
    }
}
