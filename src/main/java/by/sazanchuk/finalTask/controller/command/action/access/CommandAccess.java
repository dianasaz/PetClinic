package by.sazanchuk.finalTask.controller.command.action.access;

import by.sazanchuk.finalTask.controller.command.action.factory.CommandType;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command access.
 */
public class CommandAccess {


    /**
     * Gets available command types by user.
     *
     * @param role the role
     * @return the available command types by user
     */
    public List<CommandType> getAvailableCommandTypesByUser(String role) {
        List<CommandType> listAvailableCommands = new ArrayList<>(getCommandsForNotAuthorizedUsers());
        if(role != null) {
            listAvailableCommands.addAll(getCommonCommands());
            switch (role) {
                case "visitor": {
                    listAvailableCommands.addAll(getVisitorCommands());
                    break;
                }
                case "administrator": {
                    listAvailableCommands.addAll(getAdminCommands());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unsupported role");
                }
            }
        }
        return listAvailableCommands;
    }

    private List<CommandType> getVisitorCommands() {
        List<CommandType> commandTypes = new ArrayList<>();
        commandTypes.add(CommandType.DELETE_PET);
        commandTypes.add(CommandType.REGISTER_PET);
        commandTypes.add(CommandType.TAKE_COUPON);
        commandTypes.add(CommandType.EDIT_COUPON);
        return commandTypes;
    }

    private List<CommandType> getCommonCommands() {
        List<CommandType> commandTypes = new ArrayList<>();
        commandTypes.add(CommandType.CHANGE_LANGUAGE);
        commandTypes.add(CommandType.REGISTER);
        commandTypes.add(CommandType.LOGIN);
        commandTypes.add(CommandType.HOME_PAGE);
        commandTypes.add(CommandType.LOGOUT);
        commandTypes.add(CommandType.EDIT_PROFILE);
        return commandTypes;
    }


    private List<CommandType> getAdminCommands() {
        List<CommandType> commandTypes = new ArrayList<>();
        commandTypes.add(CommandType.ADD_SERVICE);
        commandTypes.add(CommandType.DELETE_SERVICE);
        commandTypes.add(CommandType.ADD_DOCTOR);
        commandTypes.add(CommandType.DELETE_DOCTOR);
        commandTypes.add(CommandType.EDIT_DOCTOR);
        commandTypes.add(CommandType.EDIT_SERVICE);

        return commandTypes;
    }

    private List<CommandType> getCommandsForNotAuthorizedUsers() {
        List<CommandType> commandTypes = new ArrayList<>();
        commandTypes.add(CommandType.LOGIN);
        commandTypes.add(CommandType.CHANGE_LANGUAGE);
        commandTypes.add(CommandType.REGISTER);
        commandTypes.add(CommandType.WATCH_SERVICE);
        commandTypes.add(CommandType.HOME_PAGE);
        commandTypes.add(CommandType.PROFILE);
        commandTypes.add(CommandType.WATCH_DOCTOR);
        commandTypes.add(CommandType.SEARCH);


        return commandTypes;
    }

}
