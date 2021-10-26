package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ab.entities.OrderBook;

public interface OrderBookRepository extends JpaRepository<OrderBook,Integer>{

}
