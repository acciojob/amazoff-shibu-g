package com.driver;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
	OrderRepository repo;

	public void addorder(String id,Order o) {
		repo.addorder(id, o);
	}
	public void addpair(String orderid,String partnerid) {
		repo.addorderidpair(orderid, partnerid);
	}
	public Order getbyid(String id) {
		return repo.getbyid(id);
	}
public int getorderscount(String id) {
	return repo.getcountbyid(id);
}
public List<String> grtordersbyid(String id){
	return repo.getordersbyid(id);
}
public List<Order>getallorders(){
	return repo.getallorders();
}
public int getunasigned() {
	return repo.getunasignorders();
}
public int getundeliveredorder(String time,String id) {
	return repo.getundeliveredorder(time, id);
}
public String getlastorder(String id) {
	return repo.getlastdeliverordertime(id);
}
public void deletepartnerbyid(String id) {
	repo.deletepartnerbyid(id);
}
public void deleteorder(String id) {
	repo.deleteorderbyid(id);
}
}
