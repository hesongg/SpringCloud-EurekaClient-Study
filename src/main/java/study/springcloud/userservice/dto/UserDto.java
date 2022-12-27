package study.springcloud.userservice.dto;

import lombok.Data;
import study.springcloud.userservice.vo.ResponseOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private LocalDateTime createdAt;

    private String encrypted;

    private List<ResponseOrder> orders;
}
