package com.example.Planner.services;


import com.example.Planner.dto.UserDTO;
import com.example.Planner.models.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;

public interface UserServicePersonal {

    User findUserById(int id);

    User findUserByUsername(String username);

    @Caching(
            evict = {
                    @CacheEvict(value = "allUsers", allEntries = true)
            }
    )

    @Cacheable(value = "allUsers")
    Page<User> findAll(int pageNo, int pageSize);

    void updateUser(UserDTO userDTO);

    void deleteUser(int userId);

    void updateUser(User user, String permissions);
}
