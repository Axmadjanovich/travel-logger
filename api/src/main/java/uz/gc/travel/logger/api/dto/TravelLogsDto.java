package uz.gc.travel.logger.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import uz.gc.travel.logger.api.validation.BasicInfo;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 4:15 PM
 */
@Getter
@Setter
@Accessors(chain = true)
public class TravelLogsDto {
    private Integer id;
    @NotNull(message = "Vehicle must be not null", groups = BasicInfo.class)
    private VehiclesDto vehicle;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
    @NotNull(message = "Owner must be not null", groups = BasicInfo.class)
    private UsersDto owner;
    @NotNull(message = "Odometer start point must be not null", groups = BasicInfo.class)
    private BigInteger odometerStart;
    @NotNull(message = "Odometer end point must be not null", groups = BasicInfo.class)
    private BigInteger odometerEnd;
    @NotBlank(message = "Route must be not null", groups = BasicInfo.class)
    private String route;
    private String description;
}
