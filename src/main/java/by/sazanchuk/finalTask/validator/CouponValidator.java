package by.sazanchuk.finalTask.validator;

import by.sazanchuk.finalTask.entity.Coupon;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Coupon validator.
 */
public class CouponValidator implements Validator<Coupon> {
    private static final String TIME = "^\\w{3}\\s\\w{3}\\s\\d{0,2}\\s([0](8|9)|[1][0-9])\\:";
    private static final String ENTITY_NULL = "entity_null";
    private static final String DATE_ERROR= "date_error";
    private static final String DATE_MONTH_ERROR= "date_month_error";
    private static final String VALID = "valid";

    private static CouponValidator validator;

    @Override
    public String isValid(Coupon entity) {
        if (entity == null) return ENTITY_NULL;

        if (entity.getTime().before(new Date())) {
            return DATE_ERROR;
        }

        Calendar d = Calendar.getInstance();
        d.add(Calendar.MONTH, 1);

        if (entity.getTime().after(d.getTime())) {
            return DATE_MONTH_ERROR;
        }

        Pattern pattern = Pattern.compile(TIME);
        Matcher matcher = pattern.matcher(entity.getTime().toString());
        if (!matcher.find()) return DATE_ERROR;

        return VALID;
    }

    public static CouponValidator getValidator() {
        if (validator == null) validator = new CouponValidator();
        return validator;
    }
}
