package com.ruoyi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 匿名访问不鉴权注解
 */
@Target({ ElementType.METHOD, ElementType.TYPE }) //限制这个 @Anonymous 注解能贴在哪里。TYPE表示类、接口、枚举//
@Retention(RetentionPolicy.RUNTIME) //运行时仍保留，此时可以通过反射//
@Documented
public @interface Anonymous
{
}
