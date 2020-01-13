package by.sazanchuk.finalTask.validator;

import by.sazanchuk.finalTask.entity.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Service validator.
 */
public class ServiceValidator implements Validator<Service> {
    private static final String NAME = "^[A-ZА-Я][a-zа-я]+\\s?[A-ZА-Я]?[a-zа-я]+\\s?[A-ZА-Я]?[a-zа-я]+$";
    private static final String PRICE = "^[1-9][0-9]{1,6}$";
    private static final String ENTITY_NULL = "entity_null";
    private static final String NAME_ERROR = "name_error";
    private static final String PRICE_ERROR = "price_error";
    private static final String VALID = "valid";

    private static ServiceValidator validator;

    @Override
    public String isValid(Service entity) {
        if (entity == null) return ENTITY_NULL;

        Pattern pattern = Pattern.compile(NAME);
        Matcher matcher = pattern.matcher(entity.getName());
        if (!matcher.find()) return NAME_ERROR;

        pattern = Pattern.compile(PRICE);
        matcher = pattern.matcher(String.valueOf(entity.getPrice()));
        if (!matcher.find()) return PRICE_ERROR;

        return VALID;
    }

    public static ServiceValidator getValidator() {
        if (validator == null){
            validator = new ServiceValidator();
        }
        return validator;
    }
}
