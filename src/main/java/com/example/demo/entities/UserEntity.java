package com.example.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Data
@Entity
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer iduser;

    @Column(name = "user")
    private String user;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "token")
    private String token;

    @Column
    private String photo;

    public UserEntity mapperUserEntityDto(Optional<UserEntity> userEntity) {

        UserEntity userDto = new UserEntity();

        userDto.setUser(userEntity.get().getUser());
        userDto.setPassword(userEntity.get().getPassword());
        userDto.setFirstname(userEntity.get().getFirstname());
        userDto.setLastname(userEntity.get().getLastname());
        userDto.setToken(userEntity.get().getToken());
        userDto.setPhoto(userEntity.get().getPhoto());
        return userDto;
    }

}
