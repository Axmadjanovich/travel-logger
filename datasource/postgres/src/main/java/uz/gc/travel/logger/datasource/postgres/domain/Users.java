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
import java.util.Optional;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 7:18 AM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(schema = "travel_logger", name = "users")
public class Users {
    @Id
    @Column("id")
    private Integer id;

    @LastModifiedDate
    @Column("updated_date")
    private LocalDateTime updatedDate;

    @CreatedDate
    @Column("created_date")
    private LocalDateTime createdDate;

    @Column("full_name")
    private String fullName;

    @Column("passport_serial_number")
    private String passportSerialNumber;

    @Column("birth_date")
    private LocalDateTime birthDate;

    public static Users fromRow(Map<String, Object> rows) {
        return new Users()
                .setId(Optional.ofNullable(rows.get("owner_id")).map(id -> Integer.parseInt(id.toString())).orElse(null))
                .setFullName(Optional.ofNullable(rows.get("full_name")).map(Object::toString).orElse(null))
                .setPassportSerialNumber(Optional.ofNullable(rows.get("passport_serial_number")).map(Object::toString).orElse(null))
                .setCreatedDate(Optional.ofNullable(rows.get("user_created_date")).map(createdAt -> DateTimeUtils.fromString(createdAt.toString())).orElse(null))
                .setUpdatedDate(Optional.ofNullable(rows.get("user_updated_date")).map(updatedAt -> DateTimeUtils.fromString(updatedAt.toString())).orElse(null))
                .setBirthDate(Optional.ofNullable(rows.get("birth_date")).map(updatedAt -> DateTimeUtils.fromString(updatedAt.toString())).orElse(null));
    }
}
