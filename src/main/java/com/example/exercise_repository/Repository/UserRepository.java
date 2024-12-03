package com.example.exercise_repository.Repository;

import com.example.exercise_repository.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

    User findUserByUsernameAndPassword(String username , String password);

    User findUserByEmail(String email);

    List<User> findUserByRole(String role);

    @Query("select u from User u where u.age between ?1 and ?2")
    List<User> getAllUserByRangeAge(Integer min , Integer max);
}
