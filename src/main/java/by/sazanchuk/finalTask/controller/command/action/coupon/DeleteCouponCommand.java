package by.sazanchuk.finalTask.controller.command.action.coupon;

import by.sazanchuk.finalTask.controller.command.ConfigurationManager;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.CommandResult;
import by.sazanchuk.finalTask.controller.command.action.profile.ProfileCommand;
import by.sazanchuk.finalTask.entity.Coupon;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.service.CouponService;
import by.sazanchuk.finalTask.service.DoctorService;
import by.sazanchuk.finalTask.service.ServiceException;
import by.sazanchuk.finalTask.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCouponCommand implements Command {
    private final String COUPON_ID = "coupon_id";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
       String identity = request.getParameter(COUPON_ID);

       Integer id = Integer.valueOf(identity);

       if (id != 0){
           try {
               deleteCoupon(id);
               return new CommandResult("/controller?command=profile", false);
           } catch (ServiceException e) {
               return goBackWithError(request, "error");
           }
       } else return goBackWithError(request, "error");
    }

    private void deleteCoupon(Integer id) throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        CouponService service = factory.getService(CouponService.class);
        Coupon coupon;
        coupon = service.findByIdentity(id);
        if (coupon != null) {
            service.delete(coupon.getIdentity());
        }
    }

    private CommandResult goBackWithError(HttpServletRequest request, String error) {
        request.setAttribute(error, true);
        return new CommandResult(ConfigurationManager.getProperty("path.page.user_profile"), false);
    }
}
