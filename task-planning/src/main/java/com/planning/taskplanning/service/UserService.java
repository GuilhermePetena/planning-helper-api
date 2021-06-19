package com.planning.taskplanning.service;

import com.planning.taskplanning.model.User;
import com.planning.taskplanning.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User save(User user);

    List<User> findAll();

    Optional<User> findOne(String id);

    void delete(String id);

    UserDTO converteToDTO(User user);

    List<UserDTO> converteToDTOList(List<User> user);

}
