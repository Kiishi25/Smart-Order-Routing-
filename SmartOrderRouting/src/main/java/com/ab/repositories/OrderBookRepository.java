package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.OrderBook;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook,Integer>{

	@Query("FROM OrderBook ob WHERE ob.instrument.code =:instrumentCode")
	public OrderBook getByInstrumentCode(@Param("instrumentCode") String instrumentCode);

}
