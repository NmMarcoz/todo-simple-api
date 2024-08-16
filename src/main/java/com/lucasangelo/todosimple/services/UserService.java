package com.lucasangelo.todosimple.services;

import com.lucasangelo.todosimple.exceptions.UserExceptions;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;
import com.lucasangelo.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;


    public User findById(Long id){
        if(id == null){
            throw new UserExceptions(
                    "Campo Id é obrigatório",
                    HttpStatus.BAD_REQUEST
            );
        }
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new UserExceptions(
                "Usuário não encontrado",
                HttpStatus.NOT_FOUND
        ));
    }

    @Transactional // Garante a atomicidade
    public User create(User obj){
        obj.setId(null);
        if(obj.getUsername() == null || obj.getPassword() == null){
            throw new UserExceptions(
                    "Campo username e password são obrigatórios",
                    HttpStatus.BAD_REQUEST
            );
        }
        this.userRepository.save(obj);
        if(obj.getTasks().size() > 0) {
            this.taskRepository.saveAll(obj.getTasks());
        }
        return obj;
    }
    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        User obj = findById(id);
        if(obj == null){
            throw new RuntimeException("Usuário não existe");
        }
        try{
            this.userRepository.delete(obj);
        }catch (Exception e){
            throw new UserExceptions(
                    "Não é possível, pois há presença de entidades relacionadas no banco",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
