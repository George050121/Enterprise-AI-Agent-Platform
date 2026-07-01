package com.ruoyi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限过滤注解
 */
@Target(ElementType.METHOD) //限制这个 @DataScope 注解能贴在METHOD上//
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 用户表的别名
     */
    public String userAlias() default ""; //告诉框架，SQL 中用户表的别名是什么。//

    /**
     * 部门表的别名
     */
    public String deptAlias() default ""; //告诉框架，SQL 中部门表的别名是什么。//

    /**
     * 用户字段名
     */
    public String userField() default "user_id";

    /**
     * 部门字段名
     */
    public String deptField() default "dept_id";

    /**
     * 权限字符（用于多个角色匹配符合要求的权限）默认根据权限注解@ss获取，多个权限用逗号分隔开来
     */
    public String permission() default "";
}
