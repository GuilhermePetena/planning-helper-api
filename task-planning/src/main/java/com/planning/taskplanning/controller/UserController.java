package com.planning.taskplanning.controller;

import com.planning.taskplanning.model.User;
import com.planning.taskplanning.repository.UserRepository;
import com.planning.taskplanning.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to save Story : {}", user);
        if (Objects.isNull(user.getId()) || userRepository.existsById(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        User result = userService.save(user);
            return ResponseEntity
                    .created(new URI("/user/" + result.getId()))
                    .body(userService.converteToDTO(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id", required = false) final String id, @RequestBody User user, @RequestParam(required = false) boolean expand) {
        log.debug("REST request to update User : {}, {}", id, user);
        if (user.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        if (!Objects.equals(id, user.getId())) {
            return ResponseEntity.badRequest().build();
        }

        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        User result = userService.save(user);
        return ResponseEntity
                .ok()
                .body(userService.converteToDTO(result));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePatchUser(@PathVariable(value = "id", required = false) final String id, @RequestBody Map<String, String> password, @RequestParam(required = false) boolean expand) {
        log.debug("REST request to update User : {}, {}", id);
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        User userResult = userService.findOne(id).get();
        userResult.setPassword(password.get("password"));
        userService.save(userResult);
        return ResponseEntity
                .ok()
                .body(userService.converteToDTO(userResult));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllUser(@RequestParam(required = false) boolean expand ) {
        log.debug("REST request to get a page of Stories");
        List<User> list = userService.findAll();
        if (expand){
            return ResponseEntity.ok().body(list);
        }else {
            return ResponseEntity.ok().body(userService.converteToDTOList(list));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id, @RequestParam(required = false) boolean expand) {
        log.debug("REST request to get User : {}", id);
        if (userRepository.existsById(id) && expand) {
            return ResponseEntity.ok(userService.findOne(id).get());
        } else if (userRepository.existsById(id) && expand == false) {
            return ResponseEntity.ok(userService.converteToDTO(userService.findOne(id).get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        log.debug("REST request to delete User : {}", id);
        userService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
