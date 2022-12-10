package ru.nnboo.NNBOO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nnboo.NNBOO.entity.Worker;
import ru.nnboo.NNBOO.repository.WorkerJPA;

import java.util.List;

@Service
public class WorkerService {
    @Autowired
    private WorkerJPA workerJPA;

    public List<Worker> getAllEmployers(){
        return workerJPA.findAll();
    }

    public void saveEmployer(Worker worker){
        workerJPA.save(worker);
    }
}
