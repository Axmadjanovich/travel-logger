package uz.gc.travel.logger.datasource.postgres.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.gc.travel.logger.datasource.postgres.domain.Users;

import java.util.List;

import static org.springframework.data.relational.core.query.Criteria.where;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 7:04 PM
 */
@Repository
@RequiredArgsConstructor
public class UsersRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Flux<Users> findAllUsers(List<Integer> ids){
        return r2dbcEntityTemplate.select(Users.class)
                .matching(Query.query(where("id").in(ids)))
                .all();
    }
}
