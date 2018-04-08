package club.javalearn.basic.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色信息
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {

    /**
     * 角色编码
     */
    @Id@GeneratedValue
    private Integer roleId;
    /**
     * 角色标识
     */
    @Column(unique = true)
    private String roleCode;
    /**
     *  角色描述,UI界面显示使用
     */
    private String description;

    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;


    /**
     * 角色 -- 权限关系：多对多关系
     */
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions = new ArrayList<>();

    /**
     * 用户 - 角色关系定义;
     * 一个角色对应多个用户
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="userId")})
    private List<SysUser> userList = new ArrayList<>();

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleCode='" + roleCode + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}
