package uz.gc.travel.logger.datasource.postgres.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.gc.travel.logger.datasource.postgres.util.DateTimeUtils;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author a.ergashev
 * Date: 5/25/2023
 * Time: 5:03 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(schema = "travel_logger", name = "travel_logs")
public class TravelLogs {
    @Id
    @Column("id")
    private Integer id;

    @CreatedDate
    @Column("vehicle_reg_number")
    private String vehicleRegNumber;

    @LastModifiedDate
    @Column("updated_date")
    private LocalDateTime updatedDate;

    @CreatedDate
    @Column("created_date")
    private LocalDateTime createdDate;

    @Column("owner_id")
    private Integer ownerId;

    @Column("odometer_start")
    private BigInteger odometerStart;

    @Column("odometer_end")
    private BigInteger odometerEnd;

    @Column("route")
    private String route;

    @Column("description")
    private String description;

    public static TravelLogs fromRow(Map<String, Object> rows){
        return new TravelLogs()
                .setId(Optional.ofNullable(rows.get("travel_log_id")).map(id -> Integer.parseInt(id.toString())).orElse(null))
                .setDescription(Optional.ofNullable(rows.get("description")).map(Object::toString).orElse(null))
                .setRoute(Optional.ofNullable(rows.get("route")).map(Object::toString).orElse(null))
                .setCreatedDate(Optional.ofNullable(rows.get("travel_created_date")).map(createdAt -> DateTimeUtils.fromString(createdAt.toString())).orElse(null))
                .setUpdatedDate(Optional.ofNullable(rows.get("travel_updated_date")).map(updatedAt -> DateTimeUtils.fromString(updatedAt.toString())).orElse(null))
                .setOdometerStart(Optional.ofNullable(rows.get("odometer_start")).map(odometerStart -> new BigInteger(odometerStart.toString())).orElse(null))
                .setOdometerEnd(Optional.ofNullable(rows.get("odometer_end")).map(odometerEnd -> new BigInteger(odometerEnd.toString())).orElse(null))
                .setOwnerId(Optional.ofNullable(rows.get("owner_id")).map(ownerId -> Integer.parseInt(ownerId.toString())).orElse(null))
                .setVehicleRegNumber(Optional.ofNullable(rows.get("vehicle_reg_number")).map(Object::toString).orElse(null));
    }
}
