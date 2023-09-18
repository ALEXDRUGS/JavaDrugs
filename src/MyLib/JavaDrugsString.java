package MyLib;

public class JavaDrugsString {
    /**
     * Переворот строки.
     *
     * @param stringToReverse принимает строку, которую нужно перевернуть.
     * @return перевёрнутую строку.
     */
    public static String stringReverse(String stringToReverse) {
        boolean b = !stringToReverse.isBlank();
        char[] reverseArr = stringToReverse.toCharArray();
        if (b) {
            char reverse;
            for (int i = 0; i < reverseArr.length / 2; i++) {
                reverse = reverseArr[reverseArr.length - i - 1];
                reverseArr[reverseArr.length - i - 1] = reverseArr[i];
                reverseArr[i] = reverse;
            }
        }
        return String.copyValueOf(reverseArr);
    }
}