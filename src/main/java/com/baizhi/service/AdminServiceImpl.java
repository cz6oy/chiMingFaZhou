package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.RoleDto;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByName(String username) {
        Admin admin = adminMapper.findByName(username);
        return admin;
    }

    @Override
    public List<RoleDto> findByTokenName(String username) {
        List<RoleDto> roleDtos = adminMapper.findByTokenName(username);
        return roleDtos;
    }

    @Override
    public List<String> findByPermission(String role_id) {
        List<String> byPermission = adminMapper.findByPermission(role_id);
        return byPermission;
    }
}
