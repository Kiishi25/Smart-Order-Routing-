package com.ab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.TradeHistory;

@Repository
public interface HistoryRepository extends JpaRepository<TradeHistory,Integer>{
	
	@Query("From TradeHistory t WHERE t.order.orderID =:orderID")
	List<TradeHistory> findAllByOrderId(@Param("orderID") int orderID);


}
