package com.example.exercise_repository.Controller;

import com.example.exercise_repository.ApiResponse.ApiResponse;
import com.example.exercise_repository.Model.User;
import com.example.exercise_repository.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/check-username-password/{username}/{password}")
    public ResponseEntity checkUsernameAndPasswordCorrect(@PathVariable String username ,@PathVariable String password){
        userService.checkUsernameAndPasswordCorrect(username,password);
        return ResponseEntity.ok(new ApiResponse("User enter the system successfully"));
    }

    @GetMapping("/get-user-by-role/{role}")
    public ResponseEntity getAllUser( @PathVariable String role){

        List<User> users = userService.getAllUserByRole(role);
        return users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.status(400).body(new ApiResponse("Error: users not found"));
    }
    @GetMapping("/get-user-by-range-age/{age}")
    public ResponseEntity getAllUser( @PathVariable Integer age ){

        List<User> users = userService.getAllUserByRangeAge(age);
        return users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.status(400).body(new ApiResponse("Error: users not found"));
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("User is added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id , @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        userService.updateUser(id,user);
        return ResponseEntity.ok(new ApiResponse("User is updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User is deleted"));
    }
}
