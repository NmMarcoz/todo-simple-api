package com.lucasangelo.todosimple.controllers;


import com.lucasangelo.todosimple.exceptions.UserExceptions;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.responses.UserResponse;
import com.lucasangelo.todosimple.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user/")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ExceptionHandler({UserExceptions.class})
    public ResponseEntity<User> findById(@PathVariable Long id){
        try{
            if(id == null){
                throw new UserExceptions("Id é um campo obrigatório", HttpStatus.BAD_REQUEST);
//                throw new RuntimeException("Id é um campo obrigatório");
            }
            User obj = this.userService.findById(id);

            if(obj == null){
                throw new UserExceptions("Usuário não encontrado", HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok().body(obj);

        }catch(UserExceptions e){
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage());
        }
    }

    @PostMapping("/")
    @Validated(User.CreateUser.class)
    @ResponseBody
    public ResponseEntity<UserResponse> create(@Valid @RequestBody User obj){
        try{
            this.userService.create(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            //return ResponseEntity.created(uri).build();
            return ResponseEntity.created(uri).body(new UserResponse("Usuario criado", obj));
        }catch(UserExceptions e){
            throw new ResponseStatusException(e.getStatusCode(),e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Validated(User.UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
