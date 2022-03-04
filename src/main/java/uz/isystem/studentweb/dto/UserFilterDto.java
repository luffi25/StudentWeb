package uz.isystem.studentweb.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserFilterDto extends FilterDto{
    private String firstname;
    private String lastname;
    private Integer userTypeId;
    private String username;
    private String phone;
    private Boolean status;
    private LocalDateTime minCreateDate;
    private LocalDateTime maxCreateDate;
}
