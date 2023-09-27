package com.epicode.Spring.security.repository;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Receipt;
import com.epicode.Spring.security.entity.User;

import java.util.List;


public interface ReceiptRepository extends CrudRepository<Receipt, Long>{
	public List<Receipt> findByUser(User user);
}
