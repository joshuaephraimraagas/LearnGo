package java.com.csit321g6.gilaspilipinas.learngo.service;

import java.com.csit321g6.gilaspilipinas.learngo.dto.UserRequest;
import java.com.csit321g6.gilaspilipinas.learngo.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest request);
    List<UserResponse> getAll();
    UserResponse getById(Long id);
    UserResponse update(Long id, UserRequest request);
    void delete(Long id);
}