package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.OrderBook;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook,Integer>{

	@Query("SELECT OrderBook ob FROM OrderBooks WHERE ob.instrumentName =:instrumentName")
	public OrderBook getByInstrumentName(@Param("instrumentName") String instrumentName);

}
