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
 * Time: 7:47 PM
 */
@Getter
@Setter
@Accessors(chain = true)
public class VehiclesDto {
    private Integer id;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
    private String model;
    private String manufacturer;
    @NotNull(message = "Vehicle's registration number must be not null", groups = BasicInfo.class)
    private String regNumber;
    private Integer ownerId;
}
