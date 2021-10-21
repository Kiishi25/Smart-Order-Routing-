package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.entities.Order;
import com.ab.entities.OrderHistory;

@Repository
public interface HistoryRepository extends JpaRepository<OrderHistory,Integer>{


}