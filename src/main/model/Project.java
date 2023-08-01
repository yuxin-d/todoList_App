package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;

import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order of tasks is preserved
public class Project extends Todo implements Iterable<Todo> {
    private String description;
    private List<Todo> tasks;
    
    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    //  throws EmptyStringException if description is null or empty
    public Project(String description) {
        super(description);
        this.description = description;
        tasks = new ArrayList<>();
    }
    
    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    //   throws NullArgumentException when task is null
    public void add(Todo task) throws NullArgumentException {
        if (task == null)  {
            throw new NullArgumentException();
        }

        if (!tasks.contains(task) && !task.equals(this)) {
            tasks.add(task);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: removes task from this project
    //   throws NullArgumentException when task is null
    public void remove(Todo task) throws NullArgumentException {
        if (task == null) {
            throw new NullArgumentException();
        }
        if (tasks.contains(task)) {
            tasks.remove(task);
        }
    }
    
    // EFFECTS: returns the description of this project
    public String getDescription() {
        return description;
    }

    @Override
    public int getEstimatedTimeToComplete() {
        int sum = 0;
        for (Todo t: tasks) {
            sum += t.getEstimatedTimeToComplete();
        }
        return sum;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    @Deprecated
    public List<Task> getTasks() {
        throw new UnsupportedOperationException();
    }

    // EFFECTS: returns an integer between 0 and 100 which represents
    //     the percentage of completion (rounded down to the nearest integer).
    //     the value returned is the average of the percentage of completion of
    //     all the tasks and sub-projects in this project.
    public int getProgress() {
        int adder = 0;

        if (tasks.isEmpty()) {
            return 0;
        }
        for (Todo t: tasks) {
            adder += t.getProgress();
        }
        return (int) Math.floor(adder / getNumberOfTasks());
    }
    
    // EFFECTS: returns the number of completed tasks in this project
//     private int getNumberOfCompletedTasks() {
//        int done = 0;
//        for (Todo t : tasks) {
//            if (t. == Status.DONE) {
//                done++;
//            }
//        }
//        return done;
//    }

    // EFFECTS: returns the number of tasks (and sub-projects) in this project
    public int getNumberOfTasks() {
        return tasks.size();
    }

    // EFFECTS: returns true if every task (and sub-project) in this project is completed, and false otherwise
    //If this project has no tasks (or sub-projects), return false.
    public boolean isCompleted() {
        return getNumberOfTasks() != 0 && getProgress() == 100;
    }
    
    // EFFECTS: returns true if this project contains the task
    //   throws NullArgumentException when task is null
    public boolean contains(Todo task) {
        if (task == null) {
            throw new NullArgumentException("Illegal argument: task is null");
        }
        return tasks.contains(task);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(description, project.description);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public Iterator<Todo> iterator() {
        return new ProjectIterator();
    }

    private class ProjectIterator implements Iterator<Todo> {
        int firstIterator = 0;
        int secondIterator = 0;// = tasks.iterator();
        int thirdIterator = 0;// = tasks.iterator();
        int forthIterator = 0;// = tasks.iterator();
        int iiashdioasodh = 0;

        public boolean hasNext() {
            return iiashdioasodh != tasks.size();
        }

        public Todo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Todo todo;
            iiashdioasodh++;
            Todo todo2 = getFirstTwoPriority();
            if (todo2 != null) {
                return todo2;
            }
            Todo todo1 = getLastTwoPriority();
            if (todo1 != null) {
                return todo1;
            }
            return null;
        }

        private Todo getFirstTwoPriority() {
            Todo todo;
            while (firstIterator < tasks.size()) {
                todo = tasks.get(firstIterator);
                if (todo.getPriority().isImportant() && todo.getPriority().isUrgent()) {
                    firstIterator++;
                    return todo;
                }
                firstIterator++;
            }
            while (secondIterator < tasks.size()) {
                todo = tasks.get(secondIterator);
                if (todo.getPriority().isImportant() && !todo.getPriority().isUrgent()) {
                    secondIterator++;
                    return todo;
                }
                secondIterator++;
            }
            return null;
        }

        private Todo getLastTwoPriority() {
            Todo todo;
            while (thirdIterator < tasks.size()) {
                todo = tasks.get(thirdIterator);
                if (todo.getPriority().isUrgent() && ! todo.getPriority().isImportant()) {
                    thirdIterator++;
                    return todo;
                }
                thirdIterator++;
            }

            while (forthIterator < tasks.size()) {
                todo = tasks.get(forthIterator);
                if (!todo.getPriority().isUrgent() && !todo.getPriority().isImportant()) {
                    forthIterator++;
                    return todo;
                }
                forthIterator++;
            }
            return null;
        }
    }
}