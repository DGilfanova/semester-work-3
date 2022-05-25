package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.hauss.dto.OfferDto;
import ru.kpfu.itis.hauss.models.Offer;
import ru.kpfu.itis.hauss.models.OfferCategory;
import ru.kpfu.itis.hauss.models.Status;

import java.util.List;

public interface OffersRepository extends CrudRepository<Offer, Long> {
    Page<Offer> findAllByStatus(Status status, Pageable pageable);
    Page<Offer> findAllByStatusNotAndAccount_Id(Status status, Long accountId, Pageable pageable);

    @Query(value = "SELECT o FROM Offer o WHERE o.category.id = :category_id AND o.account.id = :account_id " +
            "AND o.price < (SELECT AVG(overall_offer.price) FROM Offer overall_offer " +
            "WHERE overall_offer.category.id = :category_id)")
    List<Offer> findMostAdvantageousByCategoryAndUser(@Param("account_id") Long accountId,
                                               @Param("category_id") Long categoryId);

    @Query(value = "WITH offer_order as (" +
                "SELECT offer.id as id, title, description, price, execution_time, photo_name, status, " +
                    "offer.account_id as account_id, category_id, count(offer.id) as count " +
                "FROM offer inner join orders o on offer.id = o.offer_id " +
                    "where offer.account_id = :account_id group by offer.id) " +
            "SELECT id, title, description, price, execution_time, photo_name, status, account_id, category_id " +
                    "FROM offer_order order by count limit 5",
            nativeQuery = true)
    List<Offer> findMostPopularByOrderAndUser(@Param("account_id") Long accountId);

    @Query(value = "SELECT AVG(overall_offer.price) FROM Offer overall_offer " +
            "WHERE overall_offer.category.id = :category_id")
    Double findAveragePriceByCategory(@Param("category_id") Long categoryId);

    List<Offer> findAllByStatusAndCategory(Status status, OfferCategory category);

    @Query(value = "SELECT o FROM Offer o where o.status = :status AND o.title like :title")
    List<Offer> findAllByStatusAndTitleIsContains(@Param("status") Status status,
                                                  @Param("title") String title);
}
