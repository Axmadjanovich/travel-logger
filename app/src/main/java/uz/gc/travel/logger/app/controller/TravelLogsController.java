package uz.gc.travel.logger.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import uz.gc.travel.logger.api.dto.TravelLogsDto;
import uz.gc.travel.logger.api.dto.TravelLogsResponse;
import uz.gc.travel.logger.api.dto.http.GenericHttpResponseDto;
import uz.gc.travel.logger.api.enums.ERequestStatus;
import uz.gc.travel.logger.api.service.TravelLogsDatasource;
import uz.gc.travel.logger.api.validation.BasicInfo;

import java.util.Map;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 8:21 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/travel-logs")
public class TravelLogsController {
    private final TravelLogsDatasource travelLogsDatasource;

    @GetMapping()
    public Mono<GenericHttpResponseDto<TravelLogsResponse>> getTravelLogs(@RequestParam Map<String, String> params){
        return travelLogsDatasource.getAllTravelLogsByParam(params)
                .map(t -> new GenericHttpResponseDto<>(ERequestStatus.SUCCESS, t, "OK"))
                .switchIfEmpty(Mono.just(new GenericHttpResponseDto<>(ERequestStatus.ERROR, null, "Not found")))
                .onErrorResume(e -> Mono.just(new GenericHttpResponseDto<>(ERequestStatus.ERROR, null, e.getMessage())));
    }

    @PostMapping
    public Mono<GenericHttpResponseDto<TravelLogsDto>> addNewTravelLog(@RequestBody @Validated(BasicInfo.class) TravelLogsDto requestDto){
        return travelLogsDatasource.save(requestDto)
                .map(t -> new GenericHttpResponseDto<>(ERequestStatus.SUCCESS, t, "OK"))
                .switchIfEmpty(Mono.just(new GenericHttpResponseDto<>(ERequestStatus.ERROR, null, "Request was not saved")))
                .onErrorResume(e -> Mono.just(new GenericHttpResponseDto<>(ERequestStatus.ERROR, null, e.getMessage())));
    }

    @PutMapping
    public Mono<GenericHttpResponseDto<TravelLogsDto>> updateTravelLog(@RequestBody @Validated(BasicInfo.class) TravelLogsDto requestDto){
        return travelLogsDatasource.updateTravelLog(requestDto)
                .map(t -> new GenericHttpResponseDto<>(ERequestStatus.SUCCESS, t, "OK"))
                .switchIfEmpty(Mono.just(new GenericHttpResponseDto<>(ERequestStatus.ERROR, null, "Request was not saved")))
                .onErrorResume(e -> Mono.just(new GenericHttpResponseDto<>(ERequestStatus.ERROR, null, e.getMessage())));
    }
}
