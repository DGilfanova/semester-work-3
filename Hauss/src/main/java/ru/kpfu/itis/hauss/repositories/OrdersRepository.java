package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.hauss.models.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByAccountId(Long authUserId);

//    Native query
//    @Query(value = "SELECT ord.id as id, ord.account_id as account_id, ord.comment as comment, ord.date as date, ord.total_price as total_price, ord.offer_id as offer_id FROM orders ord LEFT JOIN offer o ON ord.offer_id = o.id WHERE o.id = :offer_id AND " +
//            "ord.date <= :date AND (ord.date + o.execution_time) >= :date", nativeQuery = true)
//    List<Order> findConflictOrder(@Param("offer_id") Long offerId, @Param("date") LocalDate date);

    @Query(value = "SELECT ord FROM Order ord JOIN FETCH ord.offer o WHERE o.id = :offer_id AND " +
            "ord.date <= :date AND (ord.date + o.executionTime) >= :date")
    List<Order> findConflictOrder(@Param("offer_id") Long offerId,
                                  @Param("date") LocalDate date);

    List<Order> findAllByAccountIdAndDateAfterOrderByDate(Long clientId, LocalDate date);
    List<Order> findAllByOfferAccountIdAndDateAfterOrderByDate(Long professionalId, LocalDate date);
}
