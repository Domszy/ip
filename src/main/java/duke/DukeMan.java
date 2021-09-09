package duke;

import java.io.File;

/**
 * DukeMan is a new update from the Duke program previously. It manages the tasks of the user.
 *
 * @author Dominic Siew Zhen Yu
 */

public class DukeMan {

    private Storage storage;
    private taskList tasks;
    private Ui ui;

    /**
     * this is the main constructor for the DukeMan class.
     */

    public DukeMan() {
        String filePath = "data/memory.txt";
        ui = new Ui();
        File file = new File(filePath);
        storage = new Storage(filePath);

        try {
            tasks = new taskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new taskList();
        }
    }

    /**
     * getResponse() method takes in a response from the user, and returns a string
     * in response.
     * @param userInput the user's input string
     * @return A response from DukeMan
     */

    public String getResponse(String userInput) {
        Parser parser = new Parser();
        parser.parsing(userInput);
        String command = parser.getCommand();
        String output = null;

        switch (command) {
        case "todo":
            String todoName = parser.getTaskName();
            output = tasks.addTodo(todoName, true);
            break;
        case "deadline":
            String deadlineName = parser.getTaskName();
            String deadline = parser.getTimeline();
            output = tasks.addDeadline(deadlineName, deadline, true);
            break;
        case "event":
            String eventName = parser.getTaskName();
            String timeline = parser.getTimeline();
            output = tasks.addEvent(eventName, timeline, true);
            break;
        case "bye":
            output = "Bye. Hope to see you again soon!";
            break;
        case "list":
            output = tasks.printList();
            break;
        case "done":
            int doneRank = Integer.parseInt(parser.getTaskName());
            output = tasks.updateTaskStatus(doneRank, true);
            break;
        case "remove":
            int removeRank = Integer.parseInt(parser.getTaskName());
            output = tasks.removeTask(removeRank - 1);
            break;
        case "find":
            String keyWord = parser.getTaskName();
            output = tasks.findWord(keyWord);
            break;
        default:
            output = "Please give an appropriate response.";
            throw new DukeException("generic");
        }
        return output;
    }
}
