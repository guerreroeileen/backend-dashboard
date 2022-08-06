package com.dashboard.backenddashboard.repository;

import com.dashboard.backenddashboard.dao.model.User;
import com.dashboard.backenddashboard.dao.repository.UserJPARepository;
import com.dashboard.backenddashboard.dto.UserDto;
import com.dashboard.backenddashboard.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserJPARepository userJPARepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<UserDto> findAll(Integer page, Integer size, Boolean enablePagination) {
        return this.userJPARepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged())
                .map(p -> this.modelMapper.map(p, UserDto.class));
    }

    @Override
    public UserDto findById(Integer id) {
        return modelMapper.map(this.userJPARepository.findById(id), UserDto.class);
    }

    @Override
    public UserDto save(UserDto object) {
        return modelMapper.map(
                this.userJPARepository.save(
                        modelMapper.map(object, User.class)),
                UserDto.class
        );
    }

    @Override
    public UserDto update(UserDto object, Integer id) {
        Optional<User> user = this.userJPARepository.findById(id);
        if (user.isPresent()) {
            return modelMapper.map(
                    this.userJPARepository.save(modelMapper.map(object, User.class)),
                    UserDto.class
            );
        }
        return null;
    }

    @Override
    public void delete(Integer object) {
        this.userJPARepository.deleteById(object);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return modelMapper.map(this.userJPARepository.findUserByUsername(username), UserDto.class);
    }
}
