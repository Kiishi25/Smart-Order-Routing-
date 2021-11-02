package com.ab.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.Order;
import com.ab.entities.TradeHistory;
import com.ab.entities.enums.BuyOrSell;
import com.ab.entities.enums.OrderType;

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
	public List<Order> findAllByBuyOrSell(@Param("buyOrSell")BuyOrSell buyOrSell);
	
	@Query("From Order o WHERE o.type =:type")
	public List<Order> findAllByType(@Param("type")OrderType type);

	@Transactional
	@Modifying
	@Query("UPDATE Order o SET o.history =:history WHERE o.orderID =:orderID")
	public void addHistoryByOrderID(@Param("orderID")int orderID1,@Param("history") List<TradeHistory> history);

	@Query("From Order o WHERE o.orderID =:orderID")
	public Order getByOrderID(@Param("orderID")int orderID);

	@Query( value ="SELECT TOP 1 * FROM Order o WHERE o.buyOrSell =:buyOrSell o.timeStamp < :currentDate ORDER BY o.timeStamp DESC", nativeQuery = true)
	public Order getByTimeStamp(@Param("buyOrSell") BuyOrSell buyOrSell, @Param("currentDate")LocalDateTime now);

	@Query("From Order o WHERE o.user.username =:username")
	public List<Order> findAllByUsername(@Param("username") String userName);

	@Query("DELETE From Order o WHERE o.user.username =:username")
	public void deleteAllByUsername(@Param("username") String username);

	@Query("From Order o WHERE o.orderBook.orderBookID =:orderBookID AND o.buyOrSell =:buyOrSell AND o.priceLimit < :priceLimit")
	public List<Order> findPossibleBuyOrdersForOrderBookID(int orderBookID, BuyOrSell buyOrSell, double priceLimit);
	
	@Query("From Order o WHERE o.orderBook.orderBookID =:orderBookID AND o.buyOrSell =:buyOrSell")
	public List<Order> findPossibleBuyOrdersForOrderBookID(int orderBookID, BuyOrSell buyOrSell);

	@Query("From Order o WHERE o.orderBook.orderBookID =:orderBookID AND o.buyOrSell =:buyOrSell AND o.priceLimit > :priceLimit")
	public List<Order> findPossibleSellOrdersForOrderBookID(int orderBookID, BuyOrSell buyOrSell, double priceLimit);

	@Query("From Order o WHERE o.orderBook.orderBookID =:orderBookID AND o.buyOrSell =:buyOrSell")
	public List<Order> findPossibleSellOrdersForOrderBookID(int orderBookID, BuyOrSell buyOrSell);
}
