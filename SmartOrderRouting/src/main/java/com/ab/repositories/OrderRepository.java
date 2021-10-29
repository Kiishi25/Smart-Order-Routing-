package com.ab.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.Order;
import com.ab.entities.TradeHistory;
import com.ab.models.OrderType;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	
	@Transactional
	@Modifying
	@Query("UPDATE Order o SET o.priceLimit =:priceLimit WHERE o.orderID =:orderID")
	public void changeOrderLimit(@Param("priceLimit")double priceLimit, @Param("orderID") int orderID);
	
	@Transactional
	@Modifying
	@Query("UPDATE Order o SET o.shareQuantity =:shareQuantity WHERE o.orderID =:orderID")
	public void changeOrderShareQuantity(@Param("shareQuantity")int shareQuantity, @Param("orderID") int orderID);
	
	
	@Query("SELECT status FROM Order o")
	public List<String> getAllStatus();

	@Query("From Order o WHERE o.buyOrSell =:buyOrSell")
	public List<Order> findAllByBuyOrSell(@Param("buyOrSell")String buyOrSell);
	
	@Query("From Order o WHERE o.type =:type")
	public List<Order> findAllByType(@Param("type")OrderType type);

	@Transactional
	@Modifying
	@Query("UPDATE Order o SET o.history =:history WHERE o.orderID =:orderID")
	public void addHistoryByOrderID(@Param("orderID")int orderID1,@Param("history") List<TradeHistory> history);

	@Query("From Order o WHERE o.orderID =:orderID")
	public Order getByOrderID(@Param("orderID")int orderID);

	public List<Order> findAllByUserID(int userID);

	
}
