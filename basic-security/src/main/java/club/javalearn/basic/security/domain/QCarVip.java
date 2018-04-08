package club.javalearn.basic.security.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarVip is a Querydsl query type for CarVip
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCarVip extends EntityPathBase<CarVip> {

    private static final long serialVersionUID = 1547668745L;

    public static final QCarVip carVip = new QCarVip("carVip");

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath idCard = createString("idCard");

    public final StringPath name = createString("name");

    public final StringPath phoneNum = createString("phoneNum");

    public QCarVip(String variable) {
        super(CarVip.class, forVariable(variable));
    }

    public QCarVip(Path<? extends CarVip> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarVip(PathMetadata metadata) {
        super(CarVip.class, metadata);
    }

}

