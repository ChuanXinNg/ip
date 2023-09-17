package roo.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import roo.RooException;

/**
 * Represents a task in the Duke application.
 * Tasks can be marked as done or undone, and have a description and completion status.
 */
public abstract class Task {
    private boolean isFinish;
    private String task;
    private ArrayList<String> tags;

    /**
     * Constructs a Task object with the given description.
     * @param task   The description of the task.
     * @param isFinish The completion status of the task.
     */
    public Task(String task, boolean isFinish, ArrayList<String> tags) throws RooException {
        if (task.isEmpty() || task.equals(" ")) {
            throw new RooException("Description is EMPTY!!!\n");
        }
        this.isFinish = isFinish;
        this.task = task;
        this.tags = tags;
    }

    /**
     * Checks if the task is marked as done.
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean done() {
        return this.isFinish;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isFinish = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isFinish = false;
    }

    /**
     * Abstract method to be implemented by subclasses. Returns the date associated with the task.
     * @return The date associated with the task.
     */
    public abstract LocalDateTime getDate();

    public void tag(ArrayList<String> tag) {
        this.tags.addAll(tag);
    }

    public void unTag() {
        this.tags = new ArrayList<String>();
    }

    /**
     * Returns a string representation of the task.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if (this.tags.isEmpty()) {
            if (this.isFinish) {
                return "[x] " + this.task;
            } else {
                return "[ ] " + this.task;
            }
        } else {
            if (this.isFinish) {
                return "[x] " + this.task + " tags: " + this.tags.toString().replace(", ", " ");
            } else {
                return "[ ] " + this.task + " tags: " + this.tags.toString().replace(", ", " ");
            }
        }
    }
}
