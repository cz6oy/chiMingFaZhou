package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.RoleDto;

import java.util.List;

public interface AdminService {
    Admin findByName(String username);
    List<RoleDto> findByTokenName(String username);
    List<String> findByPermission(String role_id);
}
