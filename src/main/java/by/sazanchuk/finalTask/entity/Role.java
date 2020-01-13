package by.sazanchuk.finalTask.entity;

/**
 * enum role
 */
public enum Role {
    ADMINISTRATOR("administrator"),
    VISITOR("visitor");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    /**
     * gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param role role
     *
     * @return role
     */
    public static Role setRole(String role){
        Role r = null;
        if (role.equalsIgnoreCase("administrator")) r = ADMINISTRATOR;
        if (role.equalsIgnoreCase("visitor")) r = VISITOR;
        return r;
    }

    @Override
    public String toString() {
        return name;
    }
}