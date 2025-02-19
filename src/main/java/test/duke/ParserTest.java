package test.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Parser;

class ParserTest {

    @Test
    void parsingEvents() {
        Parser parser = new Parser();
        parser.parsing("todo homework");
        assertEquals(parser.getCommand(), "todo");
        assertEquals(parser.getTaskName(), "homework");

        parser.parsing("deadline homework /by 2021-08-26 02:00");
        assertEquals(parser.getCommand(), "deadline");
        assertEquals(parser.getTaskName(), "homework");
        assertEquals(parser.getTimeline(), "2021-08-26 02:00");

        parser.parsing("event homework /at 2021-02-02 03:00");
        assertEquals(parser.getCommand(), "event");
        assertEquals(parser.getTaskName(), "homework");
        assertEquals(parser.getTimeline(), "2021-02-02 03:00");
    }

    @Test
    void parsingCommands() {
        Parser parser = new Parser();
        parser.parsing("done 2");
        assertEquals(parser.getCommand(), "done");
        assertEquals(parser.getTaskName(), "2");

        parser.parsing("remove 3");
        assertEquals(parser.getCommand(), "remove");
        assertEquals(parser.getTaskName(), "3");

        Parser parser2 = new Parser();
        parser2.parsing("bye");
        assertEquals(parser2.getCommand(), "bye");
    }
}
