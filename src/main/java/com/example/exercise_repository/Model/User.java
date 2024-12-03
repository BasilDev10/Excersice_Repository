package com.example.exercise_repository.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role in('user','admin')")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Error: name is empty!")
    @Size(min = 4 , message = "Error: name must length more then 4 ")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;
    @NotEmpty(message = "Error: username is empty!")
    @Size(min = 4 , message = "Error: username must length more then 4 ")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;
    @NotEmpty(message = "Error: password is empty!")
    @Column(columnDefinition = "varchar(25) not null")
    private String password;
    @NotEmpty(message = "Error: email is empty!")
    @Email(message = "Error: email format is wrong")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "Error: role is empty!")
    @Pattern(regexp = "user|admin" , message = "Error: role must be is user or admin !")
    @Column(columnDefinition = "varchar(25) not null")
    private String role;
    @NotNull(message = "Error: age is null!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Integer age;
}
