package study.springcloud.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.springcloud.userservice.vo.Greeting;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class UserController {

    private final Greeting greeting;

    @GetMapping("/health-check")
    public String status() {
        return "It's working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }
}
