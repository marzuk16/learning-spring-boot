package self.learning.lib.utils;

public class AppLocaleUtils {

    static  char[] banglaDigits = { '০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯' };

    public static String englishToBanglaDigitConversion(String englishNumber) {

        if (englishNumber == null || englishNumber.equals(""))
            return "";

        StringBuilder banglaNumber = new StringBuilder();
        try {
            for (char digit : englishNumber.toCharArray()) {
                if (Character.isDigit(digit)) {
                    if ((digit - 48) <= 9) {
                        banglaNumber.append(banglaDigits[(int) (digit) - 48]);
                    } else {
                        banglaNumber.append(digit);
                    }
                } else {
                    banglaNumber.append(digit);
                }
            }
        } catch (Exception e) {
            return "";
        }
        return banglaNumber.toString();
    }
}
