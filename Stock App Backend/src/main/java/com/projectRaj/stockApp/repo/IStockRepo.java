package com.projectRaj.stockApp.repo;

import com.projectRaj.stockApp.model.Stock;
import com.projectRaj.stockApp.model.Type;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IStockRepo extends CrudRepository<Stock,Long> {

    List<Stock> findByStockTypeAndStockPriceLessThanEqualOrderByStockPriceDesc(Type type, double price);

    List<Stock> findByStockPriceLessThanAndStockCreationTimeStampLessThanOrderByStockOwnerCountDesc(double price, LocalDateTime time);

    @Modifying
    @Query(value = "UPDATE STOCK SET STOCK_PRICE = (STOCK_PRICE + STOCK_PRICE*(:hike)) WHERE STOCK_TYPE = :stockType", nativeQuery = true)
    void updateStockByType(float hike, String stockType);
}
