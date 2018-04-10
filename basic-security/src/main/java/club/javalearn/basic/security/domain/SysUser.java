package club.javalearn.basic.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {

    @Id
    @GeneratedValue
    private Integer userId;
    /**
     * 帐号
     */
    @Column(unique =true)
    private String userName;
    /**
     * 名称（昵称或者真实姓名，不同系统不同定义）
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 加密密码的盐
     */
    private String salt;
    /**
     * 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定
     */
    private String status;

    private Date createTime;

    private Date updateTime;
    /**
     * 用户角色,
     * FetchType.EAGER 立即从数据库中进行加载数据
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList = new ArrayList<>();

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + "***" + '\'' +
                ", salt='" + salt + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * 密码盐.
     * @return 密码+salt
     */
    public String getCredentialsSalt(){
        return this.userName+this.salt;
    }
}
