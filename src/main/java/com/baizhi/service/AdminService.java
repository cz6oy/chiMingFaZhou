package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    Admin findByName(String username);
}
