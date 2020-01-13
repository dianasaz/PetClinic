package by.sazanchuk.finalTask.controller.command.action;

import java.util.Objects;

/**
 * The type Command result.
 */
public class CommandResult {
    private String page;
    private boolean isRedirect;

    /**
     * Instantiates a new Command result.
     *
     * @param page       the page
     * @param isRedirect the is redirect
     */
    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    /**
     * Instantiates a new Command result.
     *
     * @param page the page
     */
    public CommandResult(String page) {
        this.page = page;
    }

    /**
     * Instantiates a new Command result.
     */
    public CommandResult(){}

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Is redirect boolean.
     *
     * @return the boolean
     */
    public boolean isRedirect() {
        return isRedirect;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Sets redirect.
     *
     * @param redirect the redirect
     */
    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return isRedirect == that.isRedirect() &&
                Objects.equals(getPage(), that.getPage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPage(), isRedirect());
    }
}
