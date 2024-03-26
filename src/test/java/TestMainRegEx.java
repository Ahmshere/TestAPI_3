import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMainRegEx {
    public static void main(String[] args) {
        phoneMAtcher();
        myEmailPatternCheck();
    }

    public static void phoneMAtcher(){
        String input = "For more information please contact: +1 (123) 456-7890";
        Pattern pattern = Pattern.compile("\\+\\d{1,3}\\s?\\(?(\\d{3})\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String phoneNumber = matcher.group();
            System.out.println("My phone number is  " + phoneNumber);
        }

    }

    public static void myEmailPatternCheck(){
        String input = "For more information please contact: example@mail.com";
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String email = matcher.group();
            System.out.println("Email address found: " + email);
        }

    }
    public void stringMatcher(){
        String text = "This is my email: jgv@dv.com, I want to lell you, bring me the ice.";
        Pattern pattern = Pattern.compile("I.+i");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(text.substring(matcher.start(), matcher.end()));
        }
    }

}
