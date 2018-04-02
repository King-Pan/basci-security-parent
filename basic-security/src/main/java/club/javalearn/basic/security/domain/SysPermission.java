package club.javalearn.basic.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限信息
 *
 * @author king-pan
 * @date 2018-04-02
 **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission {


    /**
     * 权限编号
     */
    @Id
    @GeneratedValue
    private Integer permissionId;
    /**
     * 权限名称
     */
    private String permissionName;

    /**
     *  资源类型，[menu|button]
     */
    @Column(columnDefinition="enum('menu','button')")
    private String resourceType;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;
    /**
     * 父编号
     */
    private Long parentId;
    /**
     * 是否可用
     */
    private Boolean available = Boolean.FALSE;
    /**
     *
     */
    @ManyToMany
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SysRole> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "SysPermission{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", available=" + available +
                ", roles=" + roles.size() +
                '}';
    }
}
