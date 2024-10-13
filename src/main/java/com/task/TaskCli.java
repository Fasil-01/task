package com.task;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TaskCli {
    private TaskList listTask = new TaskList();
    private static final List<String> COMMANDS = Arrays.asList("add", "update", "delete", "list", "mark-in-progress", "mark-done", "exit");

    public static void main(String[] args) {
        TaskCli cli = new TaskCli();
        cli.run();
    }

    public void run() {
        Console console = System.console();
        if (console == null) {
            System.out.println("No console available.");
            return;
        }

        while (true) {
            String commandInput = console.readLine("task-cli> ").trim();
            String[] arguments = commandInput.split(" ");

            if (arguments.length == 0 || !COMMANDS.contains(arguments[0])) {
                System.out.println("Unknown command. Try: add, update, delete, mark-in-progress, mark-done, list, exit");
                continue;
            }

            handleCommand(arguments);
        }
    }

    private void handleAdd(String[] arguments) {
        handleOperation(arguments, 1, desc -> listTask.add(formatText(desc)));
    }

    private String formatText(String[] arguments) {
        int startIndex = Arrays.asList(arguments).contains("update") ? 2 : 1;
        return String.join(" ", Arrays.copyOfRange(arguments, startIndex, arguments.length)).trim();
    }
    private void handleUpdate(String[] arguments) {
        handleOperation(arguments, 2, (args) -> {
            int updateId = Integer.parseInt(arguments[1]);
            listTask.updateDescription(updateId, formatText(arguments));
        });
    }

    private void handleDelete(String[] arguments) {
        handleOperation(arguments, 1, (args) -> {
            int deleteId = Integer.parseInt(arguments[1]);
            listTask.delete(deleteId);
        });
    }

    private void handleShowlist(String[] arguments) {
        if (arguments.length == 2) {
            listTask.listing(arguments[1]);
        } else {
            listTask.listing();
        }
    }

    private void handleMark(String[] arguments, String status) {
        handleOperation(arguments, 1, (args) -> {
            int markId = Integer.parseInt(arguments[1]);
            listTask.updateStatus(markId, status);
        });
    }

    private void handleExit() {
        System.out.println("Exiting Task CLI. Goodbye!");
        System.exit(0);
    }

    private void handleCommand(String[] arguments) {
        String command = arguments[0];

        switch (command) {
            case "add":
                handleAdd(arguments);
                break;
            case "update":
                handleUpdate(arguments);
                break;
            case "delete":
                handleDelete(arguments);
                break;
            case "list":
                handleShowlist(arguments);
                break;
            case "mark-in-progress":
                handleMark(arguments, "in-progress");
                break;
            case "mark-done":
                handleMark(arguments, "done");
                break;
            case "exit":
                handleExit();
                break;
            default:
                System.out.println("Unknown command. Try: add, update, delete, mark-in-progress, mark-done, list, exit");
                break;
        }
    }

    // Functional handling of argument validation and command execution
    private void handleOperation(String[] arguments, int minArgs, Consumer<String[]> action) {
        if (arguments.length < minArgs + 1) {
            System.out.println("Invalid arguments. Please check the usage.");
        } else {
            try {
                action.accept(arguments);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please provide a numeric value.");
            }
        }
    }
}
