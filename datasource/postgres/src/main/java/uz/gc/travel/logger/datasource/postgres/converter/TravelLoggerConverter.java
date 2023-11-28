package uz.gc.travel.logger.datasource.postgres.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.gc.travel.logger.api.dto.TravelLogsDto;
import uz.gc.travel.logger.api.dto.UsersDto;
import uz.gc.travel.logger.datasource.postgres.domain.TravelLogs;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 4:18 PM
 */
@Mapper(componentModel = "spring", imports = UsersDto.class)
public interface TravelLoggerConverter {
    @Mapping(target = "owner", expression = "java(new UsersDto().setId(entity.getOwnerId()))")
    TravelLogsDto toDto(TravelLogs entity);
    @Mapping(target = "ownerId", expression = "java(travelLogsDto.getOwner().getId())")
    @Mapping(target = "vehicleRegNumber", expression = "java(travelLogsDto.getVehicle().getRegNumber())")
    TravelLogs toEntity(TravelLogsDto travelLogsDto);
}
