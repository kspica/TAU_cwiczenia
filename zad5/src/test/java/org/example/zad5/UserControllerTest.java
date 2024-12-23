package org.example.zad5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;
    private UserDummyRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserDummyRepository.class);
        UserController userController = new UserController(userRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                new User(1L, "John", "Doe"),
                new User(2L, "Jane", "Doe")
        ));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"));

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() throws Exception {
        when(userRepository.findById(1L)).thenReturn(new User(1L, "John", "Doe"));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        mockMvc.perform(get("/users/2"))
                .andExpect(status().is4xxClientError());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUser() throws Exception {
        User savedUser = new User(1L, "John", "Doe");

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\n" +
                                "\"lastName\":\"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane\"}"))
                .andExpect(status().is4xxClientError());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() throws Exception {
        User exisitingUser = new User(1L, "John", "Existing");
        User updatedUser = new User(1L, "John", "Updated");

        when(userRepository.findById(1L)).thenReturn(exisitingUser);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\n" +
                                "\"lastName\":\"Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.lastName").value("Updated"));

        mockMvc.perform(put("/users/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        User userToDelete = new User(1L, "John", "Existing");

        when(userRepository.findById(1L)).thenReturn(userToDelete);
        doNothing().when(userRepository).deleteById(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/users/2"))
                .andExpect(status().is4xxClientError());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
