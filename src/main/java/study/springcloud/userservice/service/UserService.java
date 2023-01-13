package study.springcloud.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import study.springcloud.userservice.dto.UserDto;
import study.springcloud.userservice.repositroy.UserEntity;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUserByAll();
}
