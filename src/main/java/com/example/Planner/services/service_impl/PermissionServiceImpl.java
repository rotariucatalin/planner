package com.example.Planner.services.service_impl;

import com.example.Planner.models.Permission;
import com.example.Planner.repositories.PermissionRepository;
import com.example.Planner.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
}
