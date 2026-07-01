package com.ruoyi.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import tools.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.common.config.serializer.SensitiveJsonSerializer;
import com.ruoyi.common.enums.DesensitizedType;

/**
 * 数据脱敏注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@JacksonAnnotationsInside //告诉 Jackson："这是一个自定义注解，里面包含了其他 Jackson 注解"
@JsonSerialize(using = SensitiveJsonSerializer.class) //指定自定义的序列化器
public @interface Sensitive
{
    DesensitizedType desensitizedType();
}
