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

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 8:15 AM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(schema = "travel_logger", name = "vehicles")
public class Vehicles {
    @Id
    @Column("id")
    private Integer id;

    @LastModifiedDate
    @Column("updated_date")
    private LocalDateTime updatedDate;

    @CreatedDate
    @Column("created_date")
    private LocalDateTime createdDate;

    @Column("model")
    private String model;

    @Column("manufacturer")
    private String manufacturer;

    @Column("reg_number")
    private String regNumber;

    @Column("owner_id")
    private Integer ownerId;

    public static Vehicles fromRow(Map<String, Object> rows){
        return new Vehicles()
                .setId(Optional.ofNullable(rows.get("vehicle_id")).map(id -> Integer.parseInt(id.toString())).orElse(null))
                .setCreatedDate(Optional.ofNullable(rows.get("v_created_date")).map(createdAt -> DateTimeUtils.fromString(createdAt.toString())).orElse(null))
                .setUpdatedDate(Optional.ofNullable(rows.get("v_updated_date")).map(updatedAt -> DateTimeUtils.fromString(updatedAt.toString())).orElse(null))
                .setManufacturer(Optional.ofNullable(rows.get("manufacturer")).map(Object::toString).orElse(null))
                .setModel(Optional.ofNullable(rows.get("model")).map(Object::toString).orElse(null))
                .setOwnerId(Optional.ofNullable(rows.get("owner_id")).map(ownerId -> Integer.parseInt(ownerId.toString())).orElse(null))
                .setRegNumber(Optional.ofNullable(rows.get("vehicle_reg_number")).map(Objects::toString).orElse(null));
    }
}
