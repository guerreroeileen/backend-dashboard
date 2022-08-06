package com.dashboard.backenddashboard.service;

import com.dashboard.backenddashboard.common.service.IBaseCRUDService;
import com.dashboard.backenddashboard.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends IBaseCRUDService <UserDto, Integer> {

    UserDto findUserByUsername(String username);
}
