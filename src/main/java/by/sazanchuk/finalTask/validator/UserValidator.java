package by.sazanchuk.finalTask.validator;

import by.sazanchuk.finalTask.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type User validator.
 */
public class UserValidator implements Validator<User> {
    //с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ обязательно буква
    private static final String LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    //Строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов
    private static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]*)(?!.*\\s).{8,33}$";
    private static final String EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    //в формате (ул.\проспект\...) улица дом(-корпус*) и квартира
    private static final String ADDRESS = "^([A-Za-zА-Яа-я]{1,10}\\.?\\s?)?[a-zA-ZА-Яа-я]{1,20}\\s\\d{1,3}(\\-\\,\\d{1})?([\\.\\s\\,a-zа-я]{1,10}[\\d]{1,5})?";
    //имя фамилия с ограничением в 16 символов
    private static final String NAME = "^[A-ZА-Я][a-zа-я]{1,16}\\s[A-ZА-Я][a-zа-я]{1,16}$";
    private static final String PHONE = "[0-9]{9}";
    private static final String LOGIN_ERROR = "login_error";
    private static final String PASSWORD_ERROR = "password_error";
    private static final String ADDRESS_ERROR = "address_error";
    private static final String PHONE_ERROR = "phone_error";
    private static final String NAME_ERROR = "name_error";
    private static final String EMAIL_ERROR = "email_error";
    private static final String VALID = "valid";
    private static final String NULL_ENTITY = "null_entity";

    private static UserValidator validator;

    @Override
    public String isValid(User entity) {
        if (entity == null) return NULL_ENTITY;

        Pattern pattern = Pattern.compile(LOGIN);
        Matcher matcher = pattern.matcher(entity.getLogin());
        if (!matcher.find()) return LOGIN_ERROR;

        pattern = Pattern.compile(PASSWORD);
        matcher = pattern.matcher(entity.getPassword());
        if (!matcher.find()) return PASSWORD_ERROR;

        pattern = Pattern.compile(NAME);
        matcher = pattern.matcher(entity.getName());
        if (!matcher.find()) return NAME_ERROR;

        pattern = Pattern.compile(EMAIL);
        matcher = pattern.matcher(entity.getEmail());
        if (!matcher.find()) return EMAIL_ERROR;

        pattern = Pattern.compile(ADDRESS);
        matcher = pattern.matcher(entity.getAddress());
        if (!matcher.find()) return ADDRESS_ERROR;

        pattern = Pattern.compile(PHONE);
        matcher = pattern.matcher(String.valueOf(entity.getPhoneNumber()));
        if (!matcher.find()) return PHONE_ERROR;

        return VALID;
    }

    public static UserValidator getValidator() {
        if (validator == null){
            validator = new UserValidator();
        }
        return validator;
    }
}
