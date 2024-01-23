package com.epicode.Spring.cinema.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.cinema.entities.Receipt;
import com.epicode.Spring.security.entity.User;

import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Long>{
	public List<Receipt> findByUser(User user);
	
	@Query("SELECT SUM(r.totPrice), DATE(r.purchaseTime) FROM Receipt r GROUP BY DATE(r.purchaseTime)")
	public List<Object[]> getLastMonthReceipts();
}
