package club.javalearn.basic.security.repository;

import club.javalearn.basic.security.domain.CarVip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface CarVipRepository extends JpaRepository<CarVip,Long>,JpaSpecificationExecutor<CarVip>,QuerydslPredicateExecutor<CarVip> {
}
