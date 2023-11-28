package uz.gc.travel.logger.api.dto.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author a.ergashev
 * Date: 11/28/2023
 * Time: 10:49 AM
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorDto {
    private String field;
    private String message;
}
