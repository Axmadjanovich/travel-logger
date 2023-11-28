package uz.gc.travel.logger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 8:06 PM
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TravelLogsResponse {
    private Map<String, Collection<TravelLogsDto>> travelLogs;
    private BigDecimal totalDistance;
}
