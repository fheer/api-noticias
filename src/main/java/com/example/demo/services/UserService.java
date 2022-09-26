package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @return List of UserEntity data
     */
    public List<UserEntity> listAll() {
        return userRepository.findAll();
    }

    /**
     *
     * @param userEntity
     * @return UserEntity data save
     */
    public UserEntity register(@RequestBody UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    /**
     *
     * @param userEntity
     * @return UserEntity data save
     */
    public UserEntity update(@RequestBody UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    /**
     * Delete User by id Method
     * @param id
     */
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

      public Optional<UserEntity> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public UserEntity login(String user, String password) {
        UserEntity userEntity = userRepository.login(user, password);
        if (userEntity == null) {
            return null;
        }
        return userEntity;
    }
}
