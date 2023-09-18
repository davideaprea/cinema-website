package com.epicode.Spring.security.repository;
import org.springframework.data.repository.CrudRepository;

import com.epicode.Spring.security.entity.Receipt;

public interface ReceiptRepository extends CrudRepository<Receipt, Long>{
}
