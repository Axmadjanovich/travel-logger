package uz.gc.travel.logger.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import uz.gc.travel.logger.api.validation.BasicInfo;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 5:04 PM
 */
@Getter
@Setter
@Accessors(chain = true)
public class UsersDto {
    @NotNull(message = "Vehicle owner's user ID must be not null", groups = BasicInfo.class)
    private Integer id;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
    private String fullName;
    private String passportSerialNumber;
    private LocalDateTime birthDate;
}
