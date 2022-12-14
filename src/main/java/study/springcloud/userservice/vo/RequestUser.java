package study.springcloud.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not less than two Characters")
    @Email
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not less than two Characters")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password not less than two Characters")
    private String pwd;
}
