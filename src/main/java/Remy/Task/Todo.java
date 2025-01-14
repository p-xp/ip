package remy.task;

/**
 * A Todo Task that contains a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description and priority.
     *
     * @param description The name of the Todo.
     */
    public Todo(String description, String priority) {
        super(description, priority);
    }

    /**
     * Returns String representation of the Todo, labelled [T].
     *
     * @return String representation of the Todo, labelled [T].
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
