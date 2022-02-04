package top.horizonask.hoawiki.common.request.validation;

import top.horizonask.hoawiki.common.request.validation.validator.EmojiStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= EmojiStringValidator.class)
public @interface EmojiString {
    String message() default "包含中英文、标点、emoji以外的不合法字符";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
