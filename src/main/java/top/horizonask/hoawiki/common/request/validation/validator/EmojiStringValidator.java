package top.horizonask.hoawiki.common.request.validation.validator;

import com.vdurmont.emoji.EmojiParser;
import top.horizonask.hoawiki.common.request.validation.EmojiString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.regex.Pattern;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

/**
 * @description:
 * @time: 2022/2/2 0:02
 */

public class EmojiStringValidator implements ConstraintValidator<EmojiString, Object> {

    private java.util.regex.Pattern pattern;

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(EmojiString constraintAnnotation) {
        pattern = Pattern.compile("\\w*|\\W*|[\\u4e00-\\u9fa5]");
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            List<String> emojisCollection = EmojiParser.extractEmojis(value.toString());
            if (emojisCollection.isEmpty()) {
                return pattern.matcher(EmojiParser.removeAllEmojis(value.toString())).matches();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
