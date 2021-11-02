package com.ab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.Order;
import com.ab.entities.OrderBook;
import com.ab.models.Action;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook,Integer>{

	@Query("FROM OrderBook ob WHERE ob.instrumentName =:instrumentName")
	public OrderBook getByInstrumentName(@Param("instrumentName") String instrumentName);
	
}
