package cn.nbt.anno;

import cn.nbt.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lyq
 * @time 2023/12/25 20:52
 */
@Documented
//指定提供校验规则的类
@Constraint(validatedBy = {StateValidation.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface State {
//    校验失败后的提示信息
    String message() default "{state参数的值只能是已发布或草稿}";

//    指定分组
    Class<?>[] groups() default {};

//    负载 获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
