package com.mugu.blog.core.constant;

public class KeyConstant {

    public final static String REDIS_KEY_PREFIX="mugu_blog:";

    public final static String UV=REDIS_KEY_PREFIX+"uv:";

    public final static String UV_TOTAL=REDIS_KEY_PREFIX+"uv:total";

    public final static String ARTICLE_V=REDIS_KEY_PREFIX+"article:";

    public final static String ARTICLE_MESSAGE_KEY=REDIS_KEY_PREFIX+"article:msg:";

    public final static String AVOID_REPEATABLE_COMMIT=REDIS_KEY_PREFIX+"avoid_repeatable_commit";

}
