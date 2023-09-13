package roo.commands;

import java.util.ArrayList;

import roo.task.Event;
import roo.TaskList;
import roo.Ui;
import roo.RooException;

public class EventCommand extends Command {
    private String input;
    private ArrayList<String> tags;

    public EventCommand(String input, ArrayList<String> tags) {
        this.input = input;
        this.tags = tags;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        try {
            if (!input.contains("/from") || !input.contains("/to")) {
                return "Please enter your task with this format: "
                        + "\"event task_description /from dd-MM-yyyy HH:mm (start) "
                        + " /to dd-MM-yyyy HH:mm (end)\"\n";
            }
            String ts = input.substring(5, input.indexOf("/from") - 1);
            String start = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
            String end = input.substring(input.indexOf("/to") + 4);
            return ui.add(new Event(ts, start, end, this.tags));
        } catch (RooException exception) {
            return exception.getMessage();
        }
    }

}
