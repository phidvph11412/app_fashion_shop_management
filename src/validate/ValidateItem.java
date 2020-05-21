package validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateItem {
    Pattern pattern;
    Matcher matcher;
    public static final String REGEX_ITEM_ID = "^[A-Z0-9]{4,}$";
    public static final String REGEX_ITEM_NAME = "^[A-Za-z0-9\\s]+$";
    public static final String REGEX_ITEM_IMG = "^.+$";
    public static final String REGEX_ITEM_PRICE = "^[0-9.]+$";
    public static final String REGEX_ITEM_AMOUNT = "^[0-9]+$";
    public static final String REGEX_ITEM_CATEGORY = "^[a-zA-Z\\s]+$";
    public static final String REGEX_ITEM_DESCRIBE = "^[a-zA-Z0-9_\\s]+$";

    public boolean validateId(String string) {
        pattern = Pattern.compile(REGEX_ITEM_ID);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean validateName(String string) {
        pattern = Pattern.compile(REGEX_ITEM_NAME);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean validateIMG(String string) {
        pattern = Pattern.compile(REGEX_ITEM_IMG);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean validatePrice(String string) {
        pattern = Pattern.compile(REGEX_ITEM_PRICE);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean validateAmount(String string) {
        pattern = Pattern.compile(REGEX_ITEM_AMOUNT);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean validateCategory(String string) {
        pattern = Pattern.compile(REGEX_ITEM_CATEGORY);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean validateDescribe(String string) {
        pattern = Pattern.compile(REGEX_ITEM_DESCRIBE);
        matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean isItem(String id, String name, String img, String price, String amount, String category, String describe) {
        boolean isValidateId = validateId(id);
        boolean isValidateName = validateName(name);
        boolean isValidateImg = validateIMG(img);
        boolean isValidatePrice = validatePrice(price);
        boolean isValidateAmount = validateAmount(amount);
        boolean isValidateCategory = validateCategory(category);
        boolean isValidateDescribe = validateDescribe(describe);
        if (isValidateId && isValidateName && isValidateImg && isValidatePrice && isValidateAmount && isValidateCategory && isValidateDescribe) {
            return true;
        } else {
            return false;
        }
    }
}
