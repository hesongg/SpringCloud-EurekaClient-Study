package study.springcloud.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.springcloud.userservice.dto.UserDto;
import study.springcloud.userservice.service.UserService;
import study.springcloud.userservice.vo.Greeting;
import study.springcloud.userservice.vo.RequestUser;
import study.springcloud.userservice.vo.ResponseUser;

@RequiredArgsConstructor
@RequestMapping("/user-service")
@RestController
public class UserController {

    private final UserService userService;
    private final Greeting greeting;
    private final Environment environment;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service on Port %s",
                environment.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);

        UserDto returnUserDto = userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(returnUserDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
