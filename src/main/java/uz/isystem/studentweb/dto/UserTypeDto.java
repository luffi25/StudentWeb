package uz.isystem.studentweb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTypeDto {

    private Integer id;
    @NotBlank(message = "Invalid name, send correct") // NotBlank
    private String name;
    @NotBlank(message = "Invalid display name, send correct")
    private String displayName;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
