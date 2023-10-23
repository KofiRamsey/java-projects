//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//class Task {
//    private String name;
//    private String description;
//    private boolean completed;
//
//    public Task(String name, String description) {
//        this.name = name;
//        this.description = description;
//        this.completed = false;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public boolean isCompleted() {
//        return completed;
//    }
//
//    public void markCompleted() {
//        completed = true;
//    }
//
//    @Override
//    public String toString() {
//        String status = completed ? "[X]" : "[ ]";
//        return status + " " + name + ": " + description;
//    }
//}
//
//class TaskManager {
//    private List<Task> tasks;
//
//    public TaskManager() {
//        tasks = new ArrayList<>();
//    }
//
//    public void addTask(Task task) {
//        tasks.add(task);
//    }
//
//    public void markTaskCompleted(int index) {
//        if (index >= 0 && index < tasks.size()) {
//            Task task = tasks.get(index);
//            task.markCompleted();
//        }
//    }
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//}
//
//public class TaskManagementAppGUI {
//    private TaskManager taskManager;
//    private JFrame frame;
//    private DefaultListModel<Task> taskListModel;
//    private JList<Task> taskList;
//
//    public TaskManagementAppGUI() {
//        taskManager = new TaskManager();
//        initialize();
//    }
//
//    private void initialize() {
//        frame = new JFrame();
//        frame.setTitle("Task Management Application");
//        frame.setBounds(100, 100, 400, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel mainPanel = new JPanel();
//        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
//        mainPanel.setLayout(new BorderLayout(0, 0));
//
//        JPanel panel = new JPanel();
//        mainPanel.add(panel, BorderLayout.NORTH);
//        panel.setLayout(new BorderLayout(0, 0));
//
//        JLabel lblTasks = new JLabel("Tasks");
//        lblTasks.setFont(new Font("Tahoma", Font.BOLD, 14));
//        panel.add(lblTasks, BorderLayout.NORTH);
//
//        JScrollPane scrollPane = new JScrollPane();
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//
//        taskListModel = new DefaultListModel<>();
//        taskList = new JList<>(taskListModel);
//        scrollPane.setViewportView(taskList);
//
//        JPanel buttonPanel = new JPanel();
//        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
//
//        JButton btnAddTask = new JButton("Add Task");
//        buttonPanel.add(btnAddTask);
//
//        JButton btnMarkCompleted = new JButton("Mark Completed");
//        buttonPanel.add(btnMarkCompleted);
//
//        btnAddTask.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                addTask();
//            }
//        });
//
//        btnMarkCompleted.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                markCompleted();
//            }
//        });
//    }
//
//    private void addTask() {
//        String name = JOptionPane.showInputDialog(frame, "Enter task name:", "Add Task", JOptionPane.PLAIN_MESSAGE);
//        String description = JOptionPane.showInputDialog(frame, "Enter task description:", "Add Task",
//                JOptionPane.PLAIN_MESSAGE);
//        Task task = new Task(name, description);
//        taskManager.addTask(task);
//        taskListModel.addElement(task);
//    }
//
//    private void markCompleted() {
//        int selectedIndex = taskList.getSelectedIndex();
//        if (selectedIndex != -1) {
//            Task task = taskListModel.getElementAt(selectedIndex);
//            task.markCompleted();
//            taskList.repaint();
//        } else {
//            JOptionPane.showMessageDialog(frame, "Please select a task from the list.", "Mark Completed",
//                    JOptionPane.WARNING_MESSAGE);
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                TaskManagementAppGUI appGUI = new TaskManagementAppGUI();
//                appGUI.frame.setVisible(true);
//            }
//        });
//    }
//}
//
//
