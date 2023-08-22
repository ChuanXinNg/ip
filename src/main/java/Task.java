public class Task {
    private boolean finish;
    private String task;

    public Task (String task) throws DukeException{
        if (task.isEmpty() || task.equals(" ")) {
            throw new DukeException("Description is EMPTY!!!\n");
        }
        this.finish = false;
        this.task = task;
    }

    public boolean done() {
        return this.finish;
    }

    public void markDone() {
        this.finish = true;
    }

    public void markUndone() {
        this.finish = false;
    }

    @Override
    public String toString() {
        if (this.finish) {
            return "[x] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
