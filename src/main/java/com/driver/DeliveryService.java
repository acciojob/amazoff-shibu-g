package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
	@Autowired
	OrderRepository repo;

	public void addpartner(String id) {
		repo.addpartner(id);
	}
	public DeliveryPartner getbyid(String id) {
		return repo.getpartnerbyid(id);
	}
}
