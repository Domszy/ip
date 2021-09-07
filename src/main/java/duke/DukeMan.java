package duke;

import java.io.File;
import java.util.Scanner;

/**
 * DukeMan is a new update from the Duke program previously. It manages the tasks of the user.
 *
 * @author Dominic Siew Zhen Yu
 */

public class DukeMan {

    private Storage storage;
    private taskList tasks;
    private Ui ui;

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
     * this is the constructor for the DukeMan function. the input of the program is the
     * file path of the .txt file that contains the previous tasks inputted by the user.
     * @param filePath the file path of the user's previously entered tasks
     */

    public DukeMan(String filePath) {
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
     * run() is a method that runs the program.
     */

    public void run() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\nHello! I'm Duke\n"
                + "What can I do for you?\n____________________________________________________________");

        boolean isEnded = false;
        Scanner sc = new Scanner(System.in);

        while (!isEnded) {
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________");

            Parser parser = new Parser();
            parser.parsing(userInput);
            String command = parser.getCommand();

            assert isEnded = true;

            switch(command) {
            case "todo":
                String todoName = parser.getTaskName();
                tasks.addTodo(todoName, true);
                break;
            case "deadline":
                String deadlineName = parser.getTaskName();
                String deadline = parser.getTimeline();
                tasks.addDeadline(deadlineName, deadline, true);
                break;
            case "event":
                String eventName = parser.getTaskName();
                String timeline = parser.getTimeline();
                tasks.addEvent(eventName, timeline, true);
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isEnded = true;
                break;
            case "list":
                tasks.printList();
                break;
            case "done":
                int doneRank = Integer.parseInt(parser.getTaskName());
                tasks.updateTaskStatus(doneRank, true);
                break;
            case "remove":
                int removeRank = Integer.parseInt(parser.getTaskName());
                tasks.removeTask(removeRank - 1 );
                break;
            case "find":
                String keyWord = parser.getTaskName();
                tasks.findWord(keyWord);
                break;
            default:
                System.out.println("Please give an appropriate response.");
                throw new DukeException("generic");

            }

            System.out.println("____________________________________________________________");
        }

    }

    public String getResponse(String userInput) {
        Parser parser = new Parser();
        parser.parsing(userInput);
        String command = parser.getCommand();
        String output = null;
        boolean isEnded = false;


            switch (command) {
                case "todo":
                    String todoName = parser.getTaskName();
                    output = tasks.addTodo(todoName,true);
                    break;
                case "deadline":
                    String deadlineName = parser.getTaskName();
                    String deadline = parser.getTimeline();
                    output = tasks.addDeadline(deadlineName,deadline,true);
                    break;
                case "event":
                    String eventName = parser.getTaskName();
                    String timeline = parser.getTimeline();
                    output = tasks.addEvent(eventName,timeline,true);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
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
                    System.out.println("Please give an appropriate response.");
                    output = "Please give an appropriate response.";
                    throw new DukeException("generic");
            }
            return output;
        }
}
