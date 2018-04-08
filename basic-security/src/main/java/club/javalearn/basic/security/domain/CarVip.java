package club.javalearn.basic.security.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data
public class CarVip {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phoneNum;
    private String idCard;
    private Date createTime;
    private Date updateTime;
    private Long balance;
}
