package com.dsl.simulator.controller;

import com.dsl.simulator.Entity.CommandHistory;
import com.dsl.simulator.Service.CommandHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/command-history")
public class CommandHistoryController {

    private final CommandHistoryService commandHistoryService;

    @Autowired
    public CommandHistoryController(CommandHistoryService commandHistoryService) {
        this.commandHistoryService = commandHistoryService;
    }

    @PostMapping
    public ResponseEntity<CommandHistory> createCommandHistory(@RequestBody CommandHistory commandHistory) {
        CommandHistory savedCommandHistory = commandHistoryService.saveCommandHistory(commandHistory);
        return ResponseEntity.ok(savedCommandHistory);
    }

    @GetMapping
    public ResponseEntity<List<CommandHistory>> getAllCommandHistories() {
        List<CommandHistory> commandHistories = commandHistoryService.getAllCommandHistories();
        return ResponseEntity.ok(commandHistories);
    }
}
