package com.example.demo.controllers;

import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.StorageException;
import com.example.demo.services.FileSystemStorageService;
import com.example.demo.services.UserService;
import com.example.demo.util.JsonWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    FileSystemStorageService fileSystemStorageService;
    /**
     * Get method
     * @return Response Entity
     */
    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> list()
    {
        List<UserEntity> reponse = userService.listAll();
        if (reponse.isEmpty())   {
            return new ResponseEntity<>(reponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    /**
     * Post method
     * @return Response Entity
     */
    @PostMapping("/save")
    public UserEntity register(@RequestParam("user") String user,
                               @RequestParam("password") String password,@RequestParam("firstname") String firstname,
                               @RequestParam("lastname") String lastname,@RequestPart("photo") MultipartFile photo)
    {
        if (!photo.isEmpty()) {
            String fileName= fileSystemStorageService.storage(photo);
            UserEntity userEntity = new UserEntity();
            userEntity.setUser(user);
            userEntity.setPassword(password);
            userEntity.setFirstname(firstname);
            userEntity.setLastname(lastname);
            userEntity.setPhoto(fileName);
            return userService.register(userEntity);
        }else {
            throw new StorageException("Can not storage a empty file.");
        }
    }

    /**
     * Put method
     * @return Response Entity
     */
    @PutMapping(value = "/update")
    public UserEntity update(@RequestParam("iduser") int iduser,@RequestParam("user") String user,
                             @RequestParam("password") String password,@RequestParam("firstname") String firstname,
                             @RequestParam("lastname") String lastname,@RequestPart("photo") MultipartFile photo)
    {
        if (iduser != 0) {
            if (!photo.isEmpty()) {
                Optional validateUser = userService.getUserById(iduser);
                if (!validateUser.isEmpty()) {
                    String fileName = fileSystemStorageService.storage(photo);
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUser(user);
                    userEntity.setPassword(password);
                    userEntity.setFirstname(firstname);
                    userEntity.setLastname(lastname);
                    userEntity.setPhoto(fileName);
                    return userService.update(userEntity);
                }else {
                    return null;
                }
            }else {
                throw new StorageException("Can not storage a empty file.");
            }
        }else {
            return  null;
        }
    }

    /**
     * Delete method
     * @return Response Entity
     */
    @DeleteMapping(value="/deleteUser/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        Optional validateUser = userService.getUserById(id);
        if (!validateUser.isEmpty()) {
            userService.delete(id);
            return "User deleted";
        }else {
            return "Cannot User delete";
        }
    }

    /**
     * Get method
     * @return Response Entity
     */
    @GetMapping(value="/getUser/{id}")
    public ResponseEntity<UserEntity> listById(@PathVariable("id") Integer id) {


        Optional<UserEntity> movieEntity = userService.getUserById(id);
        UserEntity userEntityDtoMapper = new UserEntity();

        if(movieEntity.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // convert entity to DTO
        UserEntity userEntity = userEntityDtoMapper.mapperUserEntityDto(movieEntity);

        return ResponseEntity.ok().body(userEntity);
    }

    /**
     * Allows to user login.
     * @param user credential value.
     * @param password credential value.
     * @return ResponseEntity with token generated.
     */
    @PutMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("user") String user, @RequestParam("password")
    String password) {
        UserEntity userEntity = userService.login(user, password);

        if (userEntity != null) {
            JsonWebToken jwt = new JsonWebToken();
            String token = jwt.createJwt(userEntity);
            userEntity.setToken(token);
            userService.update(userEntity);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.BAD_REQUEST);
    }
}
