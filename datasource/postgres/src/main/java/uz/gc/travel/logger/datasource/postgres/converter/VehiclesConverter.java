package uz.gc.travel.logger.datasource.postgres.converter;

import org.mapstruct.Mapper;
import uz.gc.travel.logger.api.dto.VehiclesDto;
import uz.gc.travel.logger.datasource.postgres.domain.Vehicles;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 7:48 PM
 */
@Mapper(componentModel = "spring")
public interface VehiclesConverter {
    VehiclesDto toDto(Vehicles vehicle);
}
