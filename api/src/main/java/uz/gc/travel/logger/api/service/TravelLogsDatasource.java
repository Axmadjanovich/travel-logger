package uz.gc.travel.logger.api.service;

import reactor.core.publisher.Mono;
import uz.gc.travel.logger.api.dto.TravelLogsDto;
import uz.gc.travel.logger.api.dto.TravelLogsResponse;

import java.util.Map;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 8:30 PM
 */
public interface TravelLogsDatasource {

    /**
     * Searching travel logs with user-defined params such as period filter(dateFrom, dateTo),
     * vehicle registration number, vehicle owner's name.
     * @param params map with filter name and value
     * @return All filtered travel logs as {@link TravelLogsResponse} with total amount of travelled distance.
     */
    Mono<TravelLogsResponse> getAllTravelLogsByParam(Map<String, String> params);
    Mono<TravelLogsDto> updateTravelLog(TravelLogsDto dto);
    Mono<TravelLogsDto> save(TravelLogsDto travelLogsDto);
}
