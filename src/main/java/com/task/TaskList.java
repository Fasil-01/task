package com.task;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDateTime;
import java.util.function.Consumer;
public class TaskList {
    private List<Task> listTask;
    private int taskIdCounter = 1;
    public TaskList() {
        listTask = new ArrayList<>();
    }
    public void add(String taskDescription) {
        Task newTask = new Task(taskIdCounter++, taskDescription);
        listTask.add(newTask);
        System.out.println("Task added successfully (ID:"+newTask.getId()+")");
    }
    public void updateDescription(int id, String description) {
        updateTask(id, task -> task.setDescription(description));
    }
    
    public void updateStatus(int id, String status) {
        updateTask(id, task -> task.setStatus(status));
    }
    
    private void updateTask(int id, Consumer<Task> updater) {
        listTask.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresent(task -> {
                    updater.accept(task);
                    task.setUpdatedAt(LocalDateTime.now());
                });
    }
    public void delete(int id) {
        boolean isRemoved = listTask.removeIf(task -> task.getId() == id);
        String message = isRemoved ? 
            "Task with id " + id + " has been removed." : 
            "Task with id " + id + " not found.";
        System.out.println(message);
    }
    
    public void listing() {
        listTasksByStatus(null);
    }
    
    public void listing(String status) {
        listTasksByStatus(status);
    }
    
    private void listTasksByStatus(String status) {
        listTask.stream()
            .filter(task -> status == null || task.getStatus().equals(status)) // Filtrer par statut si fourni
            .peek(this::printTask) // Afficher chaque tâche
            .findAny() // Vérifier si des tâches correspondent
            .orElseGet(() -> {
                System.out.println(status == null ? "The list is empty!" : "No tasks found with status: " + status);
                return null;
            });
    }
    
    private void printTask(Task task) {
        System.out.println(task.getId() + "  " + task.getDescription() + " " + task.getStatus()+
        "  " + task.getCreatedAt() + "  " + task.getUpdatedAt());
    }
    
 
}
