import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    Duke (String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
        this.ui = new Ui(tasks);
    }

    public void run() {
        this.storage.initialise(tasks);
        this.ui.greet();
        String input;
        Commands c;
        while (true) {
            input = this.ui.read();
            c = Parse.parse(input);
            try {
                switch (c) {
                    case LIST:
                        this.ui.list();
                        break;

                    case CLEAR:
                        this.ui.clear();
                        break;

                    case DATE:
                        if (input.length() < 7) {
                            throw new DukeException("Which day u want oh?? Give in dd-MM-yyyy ahhh\n");
                        }
                        String date = input.substring(6);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        this.ui.listDateEvents(LocalDate.parse(date, formatter));
                        break;

                    case UNMARK:
                        if (input.length() < 8) {
                            throw new DukeException("Please unmark your task using this format: "
                                    + "\"unmark [serial number]\"\n");
                        }
                        int t = Integer.parseInt(input.substring(7));
                        if (t > this.tasks.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\n");
                        } else if (!this.tasks.isDone(t - 1)) {
                            throw new DukeException("Weihh... It's unmark ehhh\n");
                        }
                        this.ui.markUndone(t - 1);
                        break;

                    case MARK:
                        if (input.length() < 6) {
                            throw new DukeException("Please mark your task using this format: "
                                    + "\"mark [serial number]\"\n");
                        }
                        int u = Integer.parseInt(input.substring(5));
                        if (u > this.tasks.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\nq");
                        } else if (this.tasks.isDone(u - 1)) {
                            throw new DukeException("Weihh... It's already mark ehhh\n");
                        }
                        this.ui.markDone(u - 1);
                        break;

                    case DELETE:
                        if (input.length() < 8) {
                            throw new DukeException("Please delete your task using this format: "
                                    + "\"delete [serial number]\"\n");
                        }
                        int v = Integer.parseInt(input.substring(7));
                        if (v > this.tasks.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\n");
                        }
                        this.ui.delete(v - 1);
                        break;

                    case TODO:
                        String tsk = input.substring(4);
                        this.ui.add(new Todo(tsk));
                        break;

                    case DEADLINE:
                        if (!input.contains("/by")) {
                            throw new DukeException("Please enter your task with this format: "
                                    + "\"deadline task_description /by dd-MM-yyyy HH:mm (deadline)\"\n");
                        }
                        String tk = input.substring(8, input.indexOf("/") - 1);
                        this.ui.add(new Deadline(tk, input.substring(input.indexOf("/by") + 4)));
                        break;

                    case EVENT:
                        if (!input.contains("/from") || !input.contains("/to")) {
                            throw new DukeException("Please enter your task with this format: "
                                    + "\"event task_description /from dd-MM-yyyy HH:mm (start) "
                                    + " /to dd-MM-yyyy HH:mm (end)\"\n");
                        }
                        String ts = input.substring(5, input.indexOf("/from") - 1);
                        String start = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                        String end = input.substring(input.indexOf("/to") + 4);
                        this.ui.add(new Event(ts, start, end));
                        break;

                    case END:
                        this.ui.close();
                        return;

                    case UNKNOWN:
                        throw new DukeException("I dunno what u mean!!!\n");
                }
            } catch (DukeException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke roo = new Duke("roo.txt");
        roo.run();
    }
}
