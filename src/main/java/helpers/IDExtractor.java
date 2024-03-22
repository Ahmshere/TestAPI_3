package helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDExtractor {

    public static void main(String[] args) {
        String res = extractId("ljgdf dipfug ID: khf6-juyr6u-gbeth-35hbb");
        System.out.println(res);
    }
    public static String extractId(String input) {

        Pattern pattern = Pattern.compile("ID: (\\S+)");
        Matcher matcher = pattern.matcher(input);


        if (matcher.find()) {

            return matcher.group(1);
        } else {

            return null;
        }
    }

}
