package by.sazanchuk.finalTask.controller.command.action.coupon;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.entity.*;
import by.sazanchuk.finalTask.service.*;
import by.sazanchuk.finalTask.validator.CouponValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditCouponCommand implements Command {
    private static final Logger log = LogManager.getLogger(EditCouponCommand.class);
    private static final String DATE = "date";
    private static final String ERROR_TIME = "error_time";
    private static final String VALID = "valid";
    private static final String COUPON_ID = "coupon_id";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String date = request.getParameter(DATE);
        String coupon_id = request.getParameter(COUPON_ID);
        String identity_coupon = (String) request.getSession().getAttribute(COUPON_ID);

        if (date == null || date.isEmpty()) {
            log.log(Level.INFO, "invalid info in updating coupon");
            request.getSession().setAttribute(COUPON_ID, coupon_id);
            return goBackWithError(request, "error");
        } else {
            try {
                Coupon coupon = find(identity_coupon);
                ServiceFactory factory = new ServiceFactory();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                CouponService couponService = factory.getService(CouponService.class);
                Date date1 = null;
                try {
                    date1 = dateFormat.parse(date);
                } catch (ParseException e) {
                    log.log(Level.INFO, e.getMessage());
                    goBackWithError(request, e.getMessage());
                }
                Coupon newCoupon = new Coupon();
                newCoupon.setDoctor_id(coupon.getDoctor_id());
                newCoupon.setPet_id(coupon.getPet_id());
                newCoupon.setUser_id(coupon.getUser_id());
                newCoupon.setService_id(coupon.getService_id());
                newCoupon.setIdentity(coupon.getIdentity());
                newCoupon.setTime(date1);
                if (!couponService.isTaken(newCoupon.getDoctor_id(), date1)) {
                    if (isValid(newCoupon).equals(VALID)) {
                        couponService.save(newCoupon);
                        request.getSession().removeAttribute("pet_id");
                        return new CommandResult("/controller?command=profile", false);
                    } else return goBackWithError(request, isValid(coupon));
                } else return goBackWithError(request, ERROR_TIME);
            } catch (ServiceException e) {
                log.log(Level.INFO, e.getMessage());
                return goBackWithError(request, e.getMessage());
            }
        }
    }


    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.edit_coupon"), false);
    }

    private String isValid(Coupon coupon){
        CouponValidator couponValidator = CouponValidator.getValidator();
        return couponValidator.isValid(coupon);
    }

    private Coupon find(String id) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        CouponService couponService = factory.getService(CouponService.class);
        Coupon coupon = couponService.findByIdentity(Integer.valueOf(id));
        return coupon;
    }
}
