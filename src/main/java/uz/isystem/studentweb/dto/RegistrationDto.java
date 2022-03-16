package uz.isystem.studentweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private String password;
    private String checkPassword;
    private String phone;
    private String email;
}
