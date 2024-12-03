package com.example.exercise_repository.Service;

import com.example.exercise_repository.ApiResponse.ApiException;
import com.example.exercise_repository.Model.User;
import com.example.exercise_repository.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void checkUsernameAndPasswordCorrect(String username , String password){
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new ApiException("Error: username not found is system");

        if(!user.getUsername().equals(username) || !user.getPassword().equals(password)) throw new ApiException("Error: username or password not correct");
    }

    public List<User> getAllUserByRole(String role){
        return userRepository.findUserByRole(role);
    }

    public List<User> getAllUserByRangeAge(Integer age){
        return userRepository.getAllUserByRangeAge(age);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public User getUserById(Integer id){
        return userRepository.findUserById(id);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id , User user){
        if(userRepository.findUserById(id) == null) throw new ApiException("Error user not found");

        user.setId(id);
        userRepository.save(user);
    }
    public void deleteUser(Integer id){
        if(userRepository.findUserById(id) == null) throw new ApiException("Error: user not found");

        userRepository.deleteById(id);
    }

}
