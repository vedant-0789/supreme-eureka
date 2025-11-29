package com.dsl.simulator.Service;

import com.dsl.simulator.Entity.CommandHistory;
import com.dsl.simulator.Repository.CommandHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandHistoryService {

    private final CommandHistoryRepository commandHistoryRepository;

    @Autowired
    public CommandHistoryService(CommandHistoryRepository commandHistoryRepository) {
        this.commandHistoryRepository = commandHistoryRepository;
    }

    public CommandHistory saveCommandHistory(CommandHistory commandHistory) {
        return commandHistoryRepository.save(commandHistory);
    }

    public List<CommandHistory> getAllCommandHistories() {
        return commandHistoryRepository.findAll();
    }
}
