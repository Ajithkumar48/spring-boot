package com.example.demo.user.service;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User("Test User", "test@example.com")));
        List<User> users = userService.getAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    void getUserById() {
        User user = new User("Test User", "test@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals("Test User", result.get().getName());
    }

    @Test
    void createUser() {
        User user = new User("Test User", "test@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User created = userService.createUser(user);
        assertEquals("Test User", created.getName());
    }

    @Test
    void updateUser() {
        User existingUser = new User("Old Name", "old@example.com");
        User updatedDetails = new User("New Name", "new@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedDetails);

        User updated = userService.updateUser(1L, updatedDetails);

        assertEquals("New Name", updated.getName());
        assertEquals("new@example.com", updated.getEmail());
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
