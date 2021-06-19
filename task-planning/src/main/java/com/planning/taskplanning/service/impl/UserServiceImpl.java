package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.User;
import com.planning.taskplanning.model.dto.UserDTO;
import com.planning.taskplanning.repository.UserRepository;
import com.planning.taskplanning.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(StoryServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        log.debug("Request to save Story : {}", user);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        log.debug("Request to get all Users");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOne(String id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete User : {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO converteToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    @Override
    public List<UserDTO> converteToDTOList(List<User> userList) {
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(converteToDTO(user));
        }
        return dtoList;
    }
}
