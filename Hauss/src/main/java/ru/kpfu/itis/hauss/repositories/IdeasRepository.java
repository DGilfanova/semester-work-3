package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.hauss.models.Idea;
import ru.kpfu.itis.hauss.models.Status;

import java.util.List;

public interface IdeasRepository extends CrudRepository<Idea, Long> {
    Page<Idea> findAllByStatus(Status status, Pageable pageable);
    List<Idea> findAllByStatusNotAndAccount_Id(Status status, Long authUserId);

    @Query(value = "SELECT * FROM idea i WHERE account_id = 3 AND created_at >= current_date - interval '1 week'",
            nativeQuery = true)
    List<Idea> findLastPublishedByUser(@Param("account_id") Long accountId);
}
