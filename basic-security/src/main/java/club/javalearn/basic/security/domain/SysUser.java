package club.javalearn.basic.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "SysUser",description = "用户信息")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {

    @Id
    @GeneratedValue
    @ApiModelProperty("用户ID")
    private Integer userId;
    /**
     * 帐号
     */
    @Column(unique =true)
    @ApiModelProperty("用户账号")
    private String userName;
    /**
     * 名称（昵称或者真实姓名，不同系统不同定义）
     */
    @ApiModelProperty("昵称")
    private String nickName;
    /**
     * 密码
     */
    @ApiModelProperty("用户密码")
    private String password;
    /**
     * 加密密码的盐
     */
    @ApiModelProperty("加密密码的盐")
    private String salt;
    /**
     * 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定
     */
    @ApiModelProperty("用户状态: 0 未认证，1：正常，2：锁定，3：删除")
    private String status;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
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
