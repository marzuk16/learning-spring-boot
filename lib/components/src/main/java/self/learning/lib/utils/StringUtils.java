package self.learning.lib.utils;

public final class StringUtils {

    private StringUtils() {}

    public static String processRequestUri(String requestUri) {
        if (org.springframework.util.StringUtils.isEmpty(requestUri) == true)
            return null;

        return requestUri.replaceAll("/[0-9]+$", "/****").replaceAll("/[0-9]+/", "/****/");
    }

    /*
     * Ref: http://www.java2s.com/example/java-utility-method/string-camel-case-to-snake-case/
     * cameltosnake-final-string-camelstr-24c33.html
     */
    public static String camelToSnake(String camelStr) {
        String ret = camelStr.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2");
        return ret.toLowerCase();
    }
}
