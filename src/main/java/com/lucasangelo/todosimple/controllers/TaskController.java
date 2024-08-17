package com.lucasangelo.todosimple.controllers;

import com.lucasangelo.todosimple.exceptions.TaskExceptions;
import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.responses.TaskResponse;
import com.lucasangelo.todosimple.services.TaskService;
import com.lucasangelo.todosimple.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    TaskService taskService;
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable @Validated Long id){
        System.out.println("Entrou no controller");
        if(id == null) throw new TaskExceptions("O campo ID é obrigaório", HttpStatus.BAD_REQUEST);
        try{
            Task obj = this.taskService.findById(id);
            return ResponseEntity.ok().body(new TaskResponse("Tarefa localizada", HttpStatus.FOUND, obj, obj.getUser()));
        }catch(TaskExceptions e){
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<TaskResponse> findByUserId(@PathVariable @Validated Long user_id){
        try{
            System.out.println("ENTROU NO CONTROLLER");
            List<Task> obj = this.taskService.findByUserId(user_id);
            if(obj == null){
                throw new TaskExceptions("Não há tarefas cadastradas neste usuário", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok().body(new TaskResponse("Tarefas encontradas", HttpStatus.FOUND, null, obj.get(0).getUser(), obj));
        }catch(TaskExceptions e){
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        }
    }


    @PostMapping("/")
    public ResponseEntity<TaskResponse> create(@RequestBody Task obj){
        try{
//            if(obj.getUser().getId() == null){
//                throw new UserExceptions("Campo ID é obrigatório", HttpStatus.BAD_REQUEST);
//            }
            if(obj.getDescription() == null || obj.getTaskName() == null){
                System.out.println("Entrou aqui");
                throw new TaskExceptions("Campo descrição é obrigatório", HttpStatus.BAD_REQUEST);

            }
            this.taskService.create(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri)
                    .body(new TaskResponse(
                            "Tarefa criada com sucesso",
                            HttpStatus.CREATED,
                            obj
                    ));
        }catch(TaskExceptions e ){
            throw new TaskExceptions(e.getMessage(), e.getStatusCode());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@RequestBody Task obj, @PathVariable Long id){
            obj.setId(id);

            this.taskService.update(obj);
            return ResponseEntity.ok().body(new TaskResponse("Tarefa atualizada com sucesso", HttpStatus.OK));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.ok().body(new TaskResponse("Usuário deletado com sucesos", HttpStatus.OK));
    }
}

