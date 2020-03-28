package messageCommons;

public class Text {

    public static String stringWithFirstLowLetter(String wordIn){
        StringBuilder builder = new StringBuilder(wordIn);
        builder.setCharAt(0, Character.toUpperCase(wordIn.charAt(0)));
        return builder.toString();
    }
}
