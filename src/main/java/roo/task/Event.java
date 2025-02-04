package roo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import roo.RooException;

/**
 * Represents a event with start and end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a Event with given event details and date. It is initialised as not finished.
     * @param task The description of the event.
     * @param start Start date and time of the event with dd-MM-yyyy HH:mm format.
     * @param end End date and time of the event with dd-MM-yyyy HH:mm format.
     * @throws RooException If the dates are empty or consist only of spaces.
     */
    public Event(String task, String start, String end, ArrayList<String> tags) throws RooException {
        super(task.strip(), false, tags);
        this.check(task.strip(), start.strip(), end.strip());
    }


    /**
     * Constructs a Event with given event details and date.
     * @param task The description of the event.
     * @param start Start date and time of the event with dd-MM-yyyy HH:mm format.
     * @param end End date and time of the event with dd-MM-yyyy HH:mm format.
     * @param isFinish The completion status of the task.
     * @throws RooException If the dates are empty or consist only of spaces.
     */
    public Event(String task, String start, String end, boolean isFinish, ArrayList<String> tags) throws RooException {
        super(task.strip(), isFinish, tags);
        this.check(task.strip(), start.strip(), end.strip());
    }

    /**
     * Checks the conditions of creating a Event with given event details and date.
     * @param task The description of the event.
     * @param start Start date and time of the event with dd-MM-yyyy HH:mm format.
     * @param end End date and time of the event with dd-MM-yyyy HH:mm format.
     * @throws RooException If the dates are empty or consist only of spaces.
     */
    private void check(String task, String start, String end) throws RooException {
        if (start.isEmpty() || start.equals(" ") || start.length() < 16) {
            throw new RooException("Missing time!!!\n");
        } else if (end.isEmpty() || end.equals(" ") || end.length() < 16) {
            throw new RooException("Missing time!!!\n");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        try {
            this.start = LocalDateTime.parse(start, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.start = LocalDateTime.parse(start, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your start time: dd-MM-yyyy HH:mm\n");
            }
        }

        try {
            this.end = LocalDateTime.parse(end, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.end = LocalDateTime.parse(end, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your end time: dd-MM-yyyy HH:mm\n");
            }
        }
    }

    /**
     * Returns the start date of the task
     * @return the start date of the task
     */
    @Override
    public LocalDateTime getDate() {
        return this.start;
    }

    /**
     * Returns a string representation of the task
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        String s = "[E]" + super.toString()
                + " from: " + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                + super.tagString();
        return s;
    }
}
