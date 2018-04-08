package club.javalearn.basic.security.service;

import club.javalearn.basic.security.common.Message;
import club.javalearn.basic.security.domain.CarVip;
import org.springframework.data.domain.Pageable;

public interface CarVipService {
    Message<CarVip> getList(CarVip vip, Pageable pageable);
}
