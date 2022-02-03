package top.horizonask.hoawiki.common;

/**
 * @description:
 * @time: 2022/2/3 17:17
 */

public class ValidateUtils {
    static public boolean wrongRequestPageId(String pageId) {
        for (int charIndex = 0; charIndex < pageId.length(); charIndex++)
            if (!Character.isDigit(pageId.charAt(charIndex))) {
                return true;
            }
        return false;
    }
}
