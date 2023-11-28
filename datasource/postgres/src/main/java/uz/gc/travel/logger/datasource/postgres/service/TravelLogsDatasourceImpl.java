package uz.gc.travel.logger.datasource.postgres.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import uz.gc.travel.logger.api.dto.TravelLogsDto;
import uz.gc.travel.logger.api.dto.TravelLogsResponse;
import uz.gc.travel.logger.api.service.TravelLogsDatasource;
import uz.gc.travel.logger.datasource.postgres.converter.TravelLoggerConverter;
import uz.gc.travel.logger.datasource.postgres.converter.UsersConverter;
import uz.gc.travel.logger.datasource.postgres.converter.VehiclesConverter;
import uz.gc.travel.logger.datasource.postgres.domain.TravelLogs;
import uz.gc.travel.logger.datasource.postgres.domain.Users;
import uz.gc.travel.logger.datasource.postgres.domain.Vehicles;
import uz.gc.travel.logger.datasource.postgres.repository.TravelLogsRepository;
import uz.gc.travel.logger.datasource.postgres.util.DateTimeUtils;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author a.ergashev
 * Date: 5/30/2023
 * Time: 11:12 AM
 */
@RequiredArgsConstructor
@Service
@Log4j2
public class TravelLogsDatasourceImpl implements TravelLogsDatasource {

    private final TravelLogsRepository travelLogsRepository;
    private final TravelLoggerConverter travelLoggerConverter;
    private final UsersConverter usersConverter;
    private final VehiclesConverter vehiclesConverter;

    public static final BiConsumer<String, Throwable> logFailure = (method, throwable) -> log.error("Error occurred in {} [throwable={}, errorMessage={}]",
            method, throwable.getClass().getName(), throwable.getMessage());

    @Override
    public Mono<TravelLogsResponse> getAllTravelLogsByParam(Map<String, String> params){
        AtomicReference<BigDecimal> distance = new AtomicReference<>(BigDecimal.ZERO);
        return travelLogsRepository.getAllTravelLogs(params)
                .map(row -> {
                    var travelLog = travelLoggerConverter.toDto(TravelLogs.fromRow(row));
                    var user = usersConverter.toDto(Users.fromRow(row));
                    var vehicle = vehiclesConverter.toDto(Vehicles.fromRow(row));

                    travelLog.setOwner(user);
                    travelLog.setVehicle(vehicle);

                    distance.set(distance.get()
                            .add(BigDecimal.valueOf(
                                    travelLog.getOdometerEnd()
                                            .subtract(travelLog.getOdometerStart())
                                            .doubleValue()
                            ))
                    );

                    return travelLog;
                })
                .collectMultimap(tl -> DateTimeUtils.formatInstantToDate(tl.getCreatedDate().truncatedTo(ChronoUnit.DAYS)), Function.identity(), TreeMap::new)
                .map(tlm -> new TravelLogsResponse(tlm, distance.get()))
                .doOnError(e -> logFailure.accept("TravelLogsDatasourceImpl.getAllTravelLogsByParam", e));
    }

    @Override
    public Mono<TravelLogsDto> updateTravelLog(TravelLogsDto dto){
        return travelLogsRepository.updateTravelLogs(travelLoggerConverter.toEntity(dto))
                .map(travelLoggerConverter::toDto)
                .doOnError(e ->
                        logFailure.accept("TravelLogsDatasourceImpl.updateTravelLog", e));
    }

    @Override
    public Mono<TravelLogsDto> save(TravelLogsDto travelLogsDto) {
        return travelLogsRepository.saveTravelLogs(travelLoggerConverter.toEntity(travelLogsDto))
                .map(travelLoggerConverter::toDto)
                .doOnError(e -> logFailure.accept("ServiceDatasourceImpl.save", e));
    }

}
