package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCustomer {
    Pattern pattern;
    Matcher matcher;
    private static final String REGEX_NAME = "[A-Za-z]{5,15}$";
    private static final String REGEX_PASS = "[A-Za-z]{6,15}\\d{1,5}";
    private static final String REGEX_REPASS = "[A-Za-z]{6,15}\\d{1,5}";
    private static final String REGEX_PHONE = "^[+]\\d{2}-\\d{8,13}";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final String REGEX_ADDRESS = "[A-Za-z]{1,15}[-|/][A-Za-z]{1,15}[-|/][A-Za-z]{1,15}";


    public boolean validateName(String name) {
        pattern = Pattern.compile(REGEX_NAME);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean validatePass(String pass) {
        pattern = Pattern.compile(REGEX_PASS);
        matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public boolean validateRepass(String repass) {
        pattern = Pattern.compile(REGEX_REPASS);
        matcher = pattern.matcher(repass);
        return matcher.matches();
    }

    public boolean validatePhone(String phone) {
        pattern = Pattern.compile(REGEX_PHONE);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean validateEmail(String email) {
        pattern = Pattern.compile(REGEX_EMAIL);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateAddress(String address) {
        pattern = Pattern.compile(REGEX_ADDRESS);
        matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
