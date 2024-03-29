package com.hcu.hot6.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoster is a Querydsl query type for Poster
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoster extends EntityPathBase<Poster> {

    private static final long serialVersionUID = -1892281133L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoster poster = new QPoster("poster");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath path = createString("path");

    public final QPost post;

    public QPoster(String variable) {
        this(Poster.class, forVariable(variable), INITS);
    }

    public QPoster(Path<? extends Poster> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoster(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoster(PathMetadata metadata, PathInits inits) {
        this(Poster.class, metadata, inits);
    }

    public QPoster(Class<? extends Poster> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

