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

    public void checkUsernameAndPasswordCorrect(String username , String Password){
        if (userRepository.findUserByUsernameAndPassword(username,Password) == null) throw new ApiException("Error: username or password is not correct");
    }

    public List<User> getAllUserByRole(String role){
        return userRepository.findUserByRole(role);
    }

    public List<User> getAllUserByRangeAge(Integer min ,Integer max){
        return userRepository.getAllUserByRangeAge(min,max);
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
