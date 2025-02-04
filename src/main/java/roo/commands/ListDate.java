package roo.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import roo.TaskList;
import roo.Ui;

/**
 * A command to list all tasks on the provided date
 */
public class ListDate extends Command {
    private String input;

    public ListDate(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        if (input.length() < 7) {
            return "Which day u want oh?? Give in dd-MM-yyyy ahhh\n";
        }
        String date = input.substring(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ui.listDateEvents(LocalDate.parse(date, formatter));
    }

}
