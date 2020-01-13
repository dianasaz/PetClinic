package by.sazanchuk.finalTask.controller.command;

/**
 * The enum Page.
 */
public enum Page {
    /**
     * Home page page.
     */
    HOME_PAGE("/WEB-INF/index.jsp"),
    /**
     * Login page.
     */
    LOGIN("/WEB-INF/login.jsp"),
    /**
     * Profile user page.
     */
    PROFILE_USER("/WEB-INF/profile_user.jsp"),
    /**
     * Profile admin page.
     */
    PROFILE_ADMIN("/WEB-INF/profile_admin.jsp"),
    /**
     * Register pet page.
     */
    REGISTER_PET("/WEB-INF/register_pet.jsp"),
    /**
     * Edit profile page.
     */
    EDIT_PROFILE("/WEB-INF/edit_profile.jsp"),
    /**
     * Register page.
     */
    REGISTER("/WEB-INF/register.jsp"),
    /**
     * No access page.
     */
    NO_ACCESS("/WEB-INF/access/noAccessPage.jsp"),
    /**
     * Watch service page.
     */
    WATCH_SERVICE("/WEB-INF/watch_service.jsp"),
    /**
     * Add service page.
     */
    ADD_SERVICE("/WEB-INF/add_service.jsp"),
    /**
     * Add doctor page.
     */
    ADD_DOCTOR("/WEB-INF/add_doctor.jsp"),
    /**
     * Watch doctor page.
     */
    WATCH_DOCTOR("/WEB-INF/watch_doctor.jsp"),
    /**
     * Take coupon page.
     */
    TAKE_COUPON("/WEB-INF/take_coupon.jsp"),

    EDIT_COUPON("WEB-INF/edit_coupon.jsp"),

    SEARCH("WEB-INF/search.jsp");


    private final String value;

    Page(String value) {
        this.value = value;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return value;
    }
}
