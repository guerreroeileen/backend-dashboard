package com.dashboard.backenddashboard.controller;

import com.dashboard.backenddashboard.common.controller.BaseCRUDController;
import com.dashboard.backenddashboard.common.service.IBaseCRUDService;
import com.dashboard.backenddashboard.dto.UserDto;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController extends BaseCRUDController<UserDto, Integer> {
    public UserController(IBaseCRUDService<UserDto, Integer> iBaseCRUDService) {
        super(iBaseCRUDService);
    }
}
