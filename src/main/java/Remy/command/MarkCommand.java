package remy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import remy.ChatbotException;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

/**
 * A Command that marks a given Task as completed upon executing.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    /* Define and compile the regex pattern */
    public static final Pattern PATTERN = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);
    private int index;

    /**
     * Creates new Mark Command that parses user input to identify the Task to be marked as completed.
     *
     * @param input Input submitted by the user.
     * @throws ChatbotException if input is in the wrong format or has missing information.
     */
    // Modified from addressBook Level 2
    // Source: https://github.com/se-edu/addressbook-level2
    public MarkCommand(String input) throws ChatbotException {

        Matcher matcher = PATTERN.matcher(input);

        // Wrong input
        if (!matcher.matches()) {
            throw new ChatbotException("missing info lah. You must give a number.");
        }

        // Obtain index
        int index = Integer.parseInt(matcher.group(1)) - 1;

        // Check index is valid
        if (index < 0) {
            throw new ChatbotException("invalid index bro");
        }

        this.index = index;
    }

    /**
     * Marks the previously indicated Task as completed.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui       Handles User interaction.
     * @param storage  Handles saving the updated TaskList.
     * @throws ChatbotException if given index does not match any Task on the TaskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        if (this.index >= taskList.size()) {
            throw new ChatbotException("Your Task list don't have this number lah.");
        }
        assert this.index > 0;

        taskList.get(index).markAsDone();
        String content = "Done. You happy?\n" + taskList.get(index).toString();
        storage.save(taskList);
        return content;
    }

}
