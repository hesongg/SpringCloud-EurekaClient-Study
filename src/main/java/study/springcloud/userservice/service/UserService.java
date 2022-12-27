package study.springcloud.userservice.service;

import study.springcloud.userservice.dto.UserDto;
import study.springcloud.userservice.repositroy.UserEntity;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
