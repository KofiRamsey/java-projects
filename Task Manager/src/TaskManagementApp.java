import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String name;
    private String description;
    private boolean completed;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return status + " " + name + ": " + description;
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markCompleted();
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}

public class TaskManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        boolean running = true;
        while (running) {
            System.out.println("Task Management Application");
            System.out.println("1. Add a Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task task = new Task(name, description);
                    taskManager.addTask(task);
                    System.out.println("Task added successfully.");
                    break;

                case 2:
                    System.out.print("Enter the index of the task to mark as completed: ");
                    int index = scanner.nextInt();
                    taskManager.markTaskCompleted(index - 1);
                    System.out.println("Task marked as completed.");
                    break;

                case 3:
                    taskManager.displayTasks();
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        System.out.println("Task Management Application closed.");
        scanner.close();
    }
}
