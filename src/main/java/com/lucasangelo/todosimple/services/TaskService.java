package com.lucasangelo.todosimple.services;

import com.lucasangelo.todosimple.exceptions.TaskExceptions;
import com.lucasangelo.todosimple.exceptions.UserExceptions;
import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new TaskExceptions(
                "Tarefa não encontrada",
                HttpStatus.NOT_FOUND
        ));
    }

    public List<Task> findByUserId(Long id){
        List<Task> tasks = this.taskRepository.findByUser_Id(id);
        return tasks;

    }
    @Transactional
    public Task create(Task obj){
        User user = userService.findById(obj.getUser().getId());
        obj.setId(null);
        if(user == null){
            throw new UserExceptions(
                    "Usuário não encontrado",
                    HttpStatus.NOT_FOUND
            );
        }
        obj.setUser(user);
        this.taskRepository.save(obj);
        return obj;
    }
    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        if (newObj == null){
            throw new TaskExceptions(
                    "Task não encontrada",
                    HttpStatus.NOT_FOUND
            );
        }
        newObj.setDescription(obj.getDescription());
        this.taskRepository.save(newObj);
        return newObj;

    }
    public void delete(Long id){
        try{
            Task obj = findById(id);
            if(obj == null){
                throw new TaskExceptions(
                        "Tarefa não encontrada",
                        HttpStatus.NOT_FOUND
                );
            }
            this.taskRepository.delete(obj);
        }catch(Exception e){
            throw new TaskExceptions(
                    "Há presença de entidades relacionadas no banco",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
