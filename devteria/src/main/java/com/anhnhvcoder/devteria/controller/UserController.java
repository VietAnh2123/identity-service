package com.anhnhvcoder.devteria.controller;

import com.anhnhvcoder.devteria.dto.ApiResponse;
import com.anhnhvcoder.devteria.dto.UserDTO;
import com.anhnhvcoder.devteria.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/users")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final IUserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserDTO> insertUser(@RequestBody @Valid UserDTO userDTO) {
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();

        UserDTO dto = userService.addUser(userDTO);
        apiResponse.setResult(dto);

        return apiResponse;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> dtos = userService.getAllUsers();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable String id) {

        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.getUserById(id));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id,@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
