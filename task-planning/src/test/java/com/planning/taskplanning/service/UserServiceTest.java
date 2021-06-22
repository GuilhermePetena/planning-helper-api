package com.planning.taskplanning.service;

import com.planning.taskplanning.mock.UserMock;
import com.planning.taskplanning.model.User;
import com.planning.taskplanning.model.dto.UserDTO;
import com.planning.taskplanning.repository.UserRepository;
import com.planning.taskplanning.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private final User user = UserMock.getUserMock();

    @Test
    public void saveUserTest() {
        when(encoder.encode(Mockito.anyString())).thenReturn("senha");
        when(userRepository.save(Mockito.any())).thenReturn(this.user);
        User user = userServiceImpl.save(this.user);
        assertNotNull(user);
        assertEquals("1234-ABCD", user.getId());
        assertEquals("gui@email.com", user.getEmail());
        assertEquals("senha", user.getPassword());
        assertEquals("Qual a sua idade?", user.getQuestion());
        assertEquals("22", user.getAnswer());
    }

    @Test
    public void findAllUsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(this.user));
        User user = userServiceImpl.findAll().get(0);
        assertNotNull(user);
        assertEquals("1234-ABCD", user.getId());
        assertEquals("gui@email.com", user.getEmail());
        assertEquals("senha", user.getPassword());
        assertEquals("Qual a sua idade?", user.getQuestion());
        assertEquals("22", user.getAnswer());
    }

    @Test
    public void findOneUserTest() {
        when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(this.user));
        Optional<User> user = userServiceImpl.findOne("1234-ABCD");
        assertNotNull(user);
        assertEquals("1234-ABCD", user.get().getId());
        assertEquals("gui@email.com", user.get().getEmail());
        assertEquals("senha", user.get().getPassword());
        assertEquals("Qual a sua idade?", user.get().getQuestion());
        assertEquals("22", user.get().getAnswer());
    }

    @Test
    public void deleteUserTest() {
        userServiceImpl.delete("1234-ABCD");
        verify(userRepository, times(1)).deleteById("1234-ABCD");
    }

    @Test
    public void convertToDtoTest() {
        UserDTO userDTO = userServiceImpl.converteToDTO(user);
        assertNotNull(userDTO);
        assertEquals("1234-ABCD", userDTO.getId());
        assertEquals("gui@email.com", userDTO.getEmail());
    }

    @Test
    public void convertToDtoListTest() {
        List<UserDTO> userDTOList = userServiceImpl.converteToDTOList(List.of(user));
        assertNotNull(userDTOList);
        assertEquals("1234-ABCD", userDTOList.get(0).getId());
        assertEquals("gui@email.com", userDTOList.get(0).getEmail());
    }
}
