package club.javalearn.basic.security.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSysPermission is a Querydsl query type for SysPermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysPermission extends EntityPathBase<SysPermission> {

    private static final long serialVersionUID = 368409372L;

    public static final QSysPermission sysPermission = new QSysPermission("sysPermission");

    public final BooleanPath available = createBoolean("available");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath permission = createString("permission");

    public final NumberPath<Integer> permissionId = createNumber("permissionId", Integer.class);

    public final StringPath permissionName = createString("permissionName");

    public final StringPath resourceType = createString("resourceType");

    public final ListPath<SysRole, QSysRole> roles = this.<SysRole, QSysRole>createList("roles", SysRole.class, QSysRole.class, PathInits.DIRECT2);

    public final StringPath url = createString("url");

    public QSysPermission(String variable) {
        super(SysPermission.class, forVariable(variable));
    }

    public QSysPermission(Path<? extends SysPermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysPermission(PathMetadata metadata) {
        super(SysPermission.class, metadata);
    }

}

