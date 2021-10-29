package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.entities.TradeHistory;

@Repository
public interface HistoryRepository extends JpaRepository<TradeHistory,Integer>{


}
