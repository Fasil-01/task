package com.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Task {
    /**
     *
     */
    private int id;
    public static int incr;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public Task(int id,String description){
        this.description = description;
        this.status = "todo";
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public Task(){}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public static int getIncr() {
        return incr;
    }
    public static void setIncr(int incr) {
        Task.incr = incr;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getCreatedAt() {
        return createdAt.format(formatter);
    }
    public String getUpdatedAt() {
        return updatedAt.format(formatter);
    }
    
    
}
