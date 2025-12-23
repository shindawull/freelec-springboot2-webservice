package com.dawull.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* interface가 아닌 @interface로 해야 @Target(), @Retention() 정상 동작
* 왜?
* @Target(ElementType.PARAMETER), @Retention(RetentionPolicy.RUNTIME) 가
* not applicable to type error
* */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
