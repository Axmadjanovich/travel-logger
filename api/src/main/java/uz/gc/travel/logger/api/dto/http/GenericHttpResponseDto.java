package uz.gc.travel.logger.api.dto.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import uz.gc.travel.logger.api.dto.TravelLogsResponse;
import uz.gc.travel.logger.api.enums.ERequestStatus;

import java.util.List;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 8:44 PM
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GenericHttpResponseDto<T> {
    private ERequestStatus status;
    private T data;
    private String message;
    private List<FieldErrorDto> fieldErrors;

    public GenericHttpResponseDto(ERequestStatus eRequestStatus, T data, String message) {
        this.status = eRequestStatus;
        this.data = data;
        this.message = message;
    }
}
