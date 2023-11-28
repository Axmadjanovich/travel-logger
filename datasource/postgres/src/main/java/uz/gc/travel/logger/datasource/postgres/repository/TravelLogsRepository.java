package uz.gc.travel.logger.datasource.postgres.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.gc.travel.logger.datasource.postgres.domain.TravelLogs;
import uz.gc.travel.logger.datasource.postgres.exception.NotFoundException;

import java.util.Map;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

/**
 * @author a.ergashev
 * Date: 5/30/2023
 * Time: 11:10 AM
 */
@Repository
@RequiredArgsConstructor
public class TravelLogsRepository {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final DatabaseClient databaseClient;

    public Mono<TravelLogs> saveTravelLogs(TravelLogs travelLogs){
        return r2dbcEntityTemplate.insert(travelLogs);
    }

    public Mono<TravelLogs> updateTravelLogs(TravelLogs travelLogs){
        return r2dbcEntityTemplate.select(TravelLogs.class)
                .matching(query(where("id").is(travelLogs.getId())))
                .one()
                .switchIfEmpty(Mono.error(new NotFoundException(String.format("Travel log with ID[%s] was not found!", travelLogs.getId()))))
                .flatMap(tl -> r2dbcEntityTemplate.update(travelLogs));
    }

    public Flux<Map<String, Object>> getAllTravelLogs(Map<String, String> params){
        StringBuilder condition = new StringBuilder(" where 1 = 1 ");
        createQueryCondition(params, condition);

        StringBuilder queryStr = new StringBuilder("select " +
                "t.id as travel_log_id, " +
                "t.vehicle_reg_number, " +
                "t.updated_date as travel_updated_date, " +
                "t.created_date as travel_created_date, " +
                "t.owner_id, " +
                "t.odometer_start, " +
                "t.odometer_end, " +
                "t.route, " +
                "t.description, " +
                "u.updated_date as user_updated_date, " +
                "u.created_date as user_created_date, " +
                "u.full_name, " +
                "u.passport_serial_number, " +
                "u.birth_date, " +
                "v.id as vehicle_id, " +
                "v.updated_date as v_updated_date, " +
                "v.created_date as v_created_date, " +
                "v.model, " +
                "v.manufacturer " +
                "from travel_logger.travel_logs t " +
                "left join travel_logger.users u on t.owner_id = u.id " +
                "left join travel_logger.vehicles v on t.vehicle_reg_number = v.reg_number ");

        queryStr.append(condition)
                .append("order by t.odometer_start asc ");

        DatabaseClient.GenericExecuteSpec sql = databaseClient.sql(queryStr.toString());
        sql = setParameters(params, sql);

        return sql.fetch()
                .all();
    }

    private void createQueryCondition(Map<String, String> params, StringBuilder condition){
        if (params.containsKey("vehicleRegNumber")){
            condition.append("and t.vehicle_reg_number = :vehicleRegNumber ");
        }
        if (params.containsKey("ownerName")){
            condition.append("and u.full_name like :ownerName ");
        }
        if(params.containsKey("dateFrom")){
            condition.append("and t.created_date >= :dateFrom ");
        }
        if(params.containsKey("dateTo")){
            condition.append("and t.created_date <= :dateTo ");
        }
    }

    private DatabaseClient.GenericExecuteSpec setParameters(Map<String, String> params, DatabaseClient.GenericExecuteSpec sql){
        if (params.containsKey("vehicleRegNumber")){
            sql = sql.bind("vehicleRegNumber", params.get("vehicleRegNumber"));
        }
        if (params.containsKey("ownerName")){
            sql = sql.bind("ownerName", "%" + params.get("ownerName") + "%");
        }
        if(params.containsKey("dateFrom")){
            sql = sql.bind("dateFrom", params.get("dateFrom"));
        }
        if(params.containsKey("dateTo")){
            sql = sql.bind("dateTo", params.get("dateTo"));
        }

        return sql;
    }
}