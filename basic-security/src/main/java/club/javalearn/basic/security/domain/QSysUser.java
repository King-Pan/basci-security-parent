package club.javalearn.basic.security.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSysUser is a Querydsl query type for SysUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSysUser extends EntityPathBase<SysUser> {

    private static final long serialVersionUID = -1558716712L;

    public static final QSysUser sysUser = new QSysUser("sysUser");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final ListPath<SysRole, QSysRole> roleList = this.<SysRole, QSysRole>createList("roleList", SysRole.class, QSysRole.class, PathInits.DIRECT2);

    public final StringPath salt = createString("salt");

    public final StringPath status = createString("status");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath userName = createString("userName");

    public QSysUser(String variable) {
        super(SysUser.class, forVariable(variable));
    }

    public QSysUser(Path<? extends SysUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysUser(PathMetadata metadata) {
        super(SysUser.class, metadata);
    }

}

