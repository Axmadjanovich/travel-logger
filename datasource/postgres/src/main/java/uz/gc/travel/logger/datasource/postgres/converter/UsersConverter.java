package uz.gc.travel.logger.datasource.postgres.converter;

import org.mapstruct.Mapper;
import uz.gc.travel.logger.api.dto.UsersDto;
import uz.gc.travel.logger.datasource.postgres.domain.Users;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 7:45 PM
 */
@Mapper(componentModel = "spring")
public interface UsersConverter {
    UsersDto toDto(Users users);
}
