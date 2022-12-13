package study.springcloud.userservice.service;

import study.springcloud.userservice.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
}
