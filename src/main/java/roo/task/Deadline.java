package roo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import roo.RooException;

/**
 * Represents a task with deadline.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Constructs a Deadline Task with specific task details and date. It is initialised as not finished.
     * @param task The description of the deadline task.
     * @param date Deadline of the task with dd-MM-yyyy HH:mm format.
     * @throws RooException If the date is empty or consists only of spaces.
     */
    public Deadline(String task, String date, ArrayList<String> tags) throws RooException {
        super(task.strip(), false, tags);
        this.check(task.strip(), date.strip());
    }

    /**
     * Constructs a Deadline Task with specific task details and date.
     * @param task The description of the deadline task.
     * @param date Deadline of the task with dd-MM-yyyy HH:mm format.
     * @param isFinish The completion status of the task.
     * @throws RooException If the date is empty or consists only of spaces.
     */
    public Deadline(String task, String date, boolean isFinish, ArrayList<String> tags) throws RooException {
        super(task.strip(), isFinish, tags);
        this.check(task.strip(), date.strip());
    }

    /**
     * Checks the conditions of creating a Deadline Task with specific task details and date.
     * @param task The description of the deadline task.
     * @param date Deadline of the task with dd-MM-yyyy HH:mm format.
     * @throws RooException If the date is empty or consists only of spaces.
     */
    private void check(String task, String date) throws RooException {
        if (date.isEmpty() || date.equals(" ") || date.length() < 16) {
            throw new RooException("What is your DEADLINE???\n");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        try {
            this.date = LocalDateTime.parse(date, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.date = LocalDateTime.parse(date, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your time: dd-MM-yyyy HH:mm\n");
            }
        }
    }

    /**
     * Returns the deadline of the task.
     * @return the deadline of the task.
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Returns a string representation of the task.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: "
                + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                + super.tagString();
    }
}
