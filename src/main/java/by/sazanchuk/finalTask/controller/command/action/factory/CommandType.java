package by.sazanchuk.finalTask.controller.command.action.factory;

import java.util.stream.Stream;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * Login command type.
     */
    LOGIN("login"),
    /**
     * Logout command type.
     */
    LOGOUT("logout"),
    /**
     * Home page command type.
     */
    HOME_PAGE("home_page"),
    /**
     * Register command type.
     */
    REGISTER("register"),
    /**
     * Register pet command type.
     */
    REGISTER_PET("register_pet"),
    /**
     * Profile command type.
     */
    PROFILE("profile"),
    /**
     * Edit profile command type.
     */
    EDIT_PROFILE("edit_profile"),
    /**
     * Change language command type.
     */
    CHANGE_LANGUAGE("change_language"),
    /**
     * Delete pet command type.
     */
    DELETE_PET("delete_pet"),
    /**
     * Watch service command type.
     */
    WATCH_SERVICE("watch_service"),
    /**
     * Profile user command type.
     */
    PROFILE_USER("profile_user"),
    /**
     * Profile admin command type.
     */
    PROFILE_ADMIN("profile_admin"),
    /**
     * Add service command type.
     */
    ADD_SERVICE("add_service"),
    /**
     * Delete service command type.
     */
    DELETE_SERVICE("delete_service"),
    /**
     * Add doctor command type.
     */
    ADD_DOCTOR("add_doctor"),
    /**
     * Watch doctor command type.
     */
    WATCH_DOCTOR("watch_doctor"),
    /**
     * Delete doctor command type.
     */
    DELETE_DOCTOR("delete_doctor"),
    /**
     * Take coupon command type.
     */
    TAKE_COUPON("take_coupon"),
    /**
     * Edit doctor command type.
     */
    EDIT_DOCTOR("edit_doctor"),
    /**
     * Edit service command type.
     */
    EDIT_SERVICE("edit_service"),

    EDIT_COUPON("edit_coupon"),

    SEARCH("search");


    private String command;

    CommandType(String command) {
        this.command = command;
    }

    /**
     * Of command type.
     *
     * @param command the command
     * @return the command type
     */
    public static CommandType of(String command) {
        return Stream.of(CommandType.values())
                .filter(c -> c.command.equalsIgnoreCase(command))
                .findFirst().orElse(HOME_PAGE);
    }
}
