package uz.isystem.studentweb.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private UserTypeDto userType;
    @NotNull(message = ("User type invalid"))
    private Integer userTypeId;
    @NotNull(message = ("User id invalid"))
    private Long userId;
    private Long chatId;
    @NotEmpty(message = "Username invalid")
    @Length(min = 5, max = 25, message = ("User name so short or long"))
    private String username;
    @NotBlank(message = ("Phone invalid"))
    private String phone;
    @NotBlank(message = ("First name invalid"))
    private String firstname;
    private String lastname;
    private String password;
    private String token;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
