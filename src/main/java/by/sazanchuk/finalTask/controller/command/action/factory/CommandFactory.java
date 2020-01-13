package by.sazanchuk.finalTask.controller.command.action.factory;

import by.sazanchuk.finalTask.controller.command.action.ChangeLanguageCommand;
import by.sazanchuk.finalTask.controller.command.action.Command;
import by.sazanchuk.finalTask.controller.command.action.coupon.EditCouponCommand;
import by.sazanchuk.finalTask.controller.command.action.coupon.TakeCouponCommand;
import by.sazanchuk.finalTask.controller.command.action.doctor.AddDoctorCommand;
import by.sazanchuk.finalTask.controller.command.action.doctor.DeleteDoctorCommand;
import by.sazanchuk.finalTask.controller.command.action.doctor.EditDoctorCommand;
import by.sazanchuk.finalTask.controller.command.action.doctor.WatchDoctorCommand;
import by.sazanchuk.finalTask.controller.command.action.pet.DeletePetCommand;
import by.sazanchuk.finalTask.controller.command.action.profile.ProfileAdminCommand;
import by.sazanchuk.finalTask.controller.command.action.profile.ProfileCommand;
import by.sazanchuk.finalTask.controller.command.action.profile.EditProfileCommand;
import by.sazanchuk.finalTask.controller.command.action.HomePageCommand;
import by.sazanchuk.finalTask.controller.command.action.authorization.LoginCommand;
import by.sazanchuk.finalTask.controller.command.action.authorization.LogoutCommand;
import by.sazanchuk.finalTask.controller.command.action.profile.ProfileUserCommand;
import by.sazanchuk.finalTask.controller.command.action.authorization.RegisterCommand;
import by.sazanchuk.finalTask.controller.command.action.pet.RegisterPetCommand;
import by.sazanchuk.finalTask.controller.command.action.search.SearchCommand;
import by.sazanchuk.finalTask.controller.command.action.service.AddServiceCommand;
import by.sazanchuk.finalTask.controller.command.action.service.DeleteServiceCommand;
import by.sazanchuk.finalTask.controller.command.action.service.EditServiceCommand;
import by.sazanchuk.finalTask.controller.command.action.service.WatchServiceCommand;

import java.util.EnumMap;
import java.util.Map;

import static by.sazanchuk.finalTask.controller.command.action.factory.CommandType.*;

/**
 * The type Command factory.
 */
public class CommandFactory {

    private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);
    static {
        commands.put(LOGIN, new LoginCommand());
        commands.put(HOME_PAGE, new HomePageCommand());
        commands.put(REGISTER, new RegisterCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(PROFILE, new ProfileCommand());
        commands.put(REGISTER_PET, new RegisterPetCommand());
        commands.put(EDIT_PROFILE, new EditProfileCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(DELETE_PET, new DeletePetCommand());
        commands.put(WATCH_SERVICE, new WatchServiceCommand());
        commands.put(ADD_SERVICE, new AddServiceCommand());
        commands.put(PROFILE_USER, new ProfileUserCommand());
        commands.put(PROFILE_ADMIN, new ProfileAdminCommand());
        commands.put(DELETE_SERVICE, new DeleteServiceCommand());
        commands.put(ADD_DOCTOR, new AddDoctorCommand());
        commands.put(WATCH_DOCTOR, new WatchDoctorCommand());
        commands.put(DELETE_DOCTOR, new DeleteDoctorCommand());
        commands.put(TAKE_COUPON, new TakeCouponCommand());
        commands.put(EDIT_DOCTOR, new EditDoctorCommand());
        commands.put(EDIT_SERVICE, new EditServiceCommand());
        commands.put(EDIT_COUPON, new EditCouponCommand());
        commands.put(SEARCH, new SearchCommand());
    }

    /**
     * Create command.
     *
     * @param command the command
     * @return the command
     */
    public static Command create(String command) {
       return commands.get(CommandType.of(command));
    }
}
