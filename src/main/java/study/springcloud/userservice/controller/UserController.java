package study.springcloud.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.web.bind.annotation.*;
import study.springcloud.userservice.dto.UserDto;
import study.springcloud.userservice.service.UserService;
import study.springcloud.userservice.vo.Greeting;
import study.springcloud.userservice.vo.RequestUser;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class UserController {

    private final UserService userService;
    private final Greeting greeting;

    @GetMapping("/health-check")
    public String status() {
        return "It's working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public String createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);

        userService.createUser(userDto);

        return "Create User method is called";
    }
}
