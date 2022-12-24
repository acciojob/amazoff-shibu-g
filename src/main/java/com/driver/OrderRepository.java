package com.driver;

import java.util.HashMap;
import java.util.List;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository{

	Map<String,Order>orders=new HashMap<>();
	Map<String,Integer>deliverypartner=new HashMap<>();
	Map<String,List<String>>orderidpair=new HashMap<>();
	Set<String>assigned=new HashSet<>();
	
	public void addorder(String id,Order o) {
		orders.put(id, o);
	}
	public void addpartner(String id) {
		deliverypartner.put(id,0);
	}
	public void addorderidpair(String orderid,String partnerid) {
		if(orderidpair.get(partnerid)==null) {
			List<String>orderess=new ArrayList<>();
			orderess.add(orderid);
			assigned.add(orderid);
			orderidpair.put(partnerid, orderess);
			deliverypartner.put(partnerid, orderess.size());
		}else {
			List<String>orderess=orderidpair.get(partnerid);
			orderess.add(orderid);
			assigned.add(orderid);
			orderidpair.put(partnerid, orderess);
			deliverypartner.put(partnerid, orderess.size());
		}
	}
	public Order getbyid(String id) {
		return orders.get(id);
	}
	public DeliveryPartner getpartnerbyid(String id) {
		DeliveryPartner d=new DeliveryPartner(id);
		d.setNumberOfOrders(deliverypartner.get(id));
		return d;
	}
	public int getcountbyid(String id) {
		return deliverypartner.get(id);
	}
	public List<String> getordersbyid(String id){
		List<String>orderess=orderidpair.get(id);
		return orderess;
	}
	public List<String>getallorders(){
		List<String>o=new ArrayList<>();
		for(String ob:orders.keySet()){
			o.add(ob);
		}
		return o;
	}
	public int getunasignorders() {
		return orderidpair.size()-assigned.size();
	}
	public int getundeliveredorder(String time,String id) {
		
		List<String>order=orderidpair.get(id);
		String []times=time.split(":");
    	int number=Integer.parseInt(times[0])*60;
    	number+=Integer.parseInt(times[1]);
		int count=0;
		for(String s:order) {
			Order o=orders.get(s);
		if(o.getDeliveryTime()>number) {
			count++;
		}
		}
		return count;
		}
	public String getlastdeliverordertime(String id) {
		List<String>order=orderidpair.get(id);
		int last=0;
		for(String s:order) {
			Order o=orders.get(s);
			if(o.getDeliveryTime()>last) {
				last=o.getDeliveryTime();
			}
		}
		int hour=last/60;
		last%=60;
		String time=hour+" : "+last;
		return time;
	}
	public void deletepartnerbyid(String id) {
		deliverypartner.remove(id);
		orderidpair.remove(id);
		deliverypartner.remove(id);
	}
	public void deleteorderbyid(String id) {
		orders.remove(id);
		if(assigned.contains(id)) {
			for(String a:orderidpair.keySet()) {
				List<String>ll=orderidpair.get(a);
				for(int i=0;i<ll.size();i++) {
					if(ll.get(i).equals(id)) {
						ll.remove(i);
						orderidpair.put(a,ll);
						return;
					}
				}
			}
		}
	}
	}
