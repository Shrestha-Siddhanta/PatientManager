package edu.selu.siddhanta.patientmanager;


/**
 * Created by Siddhanta on 4/1/2017.
 */

import java.util.ArrayList;
import java.util.List;

class Patient {
	private String id, name, address1, address2, dob, provider;
	private List<Order> orders;

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getAddress1(){
		return this.address1;
	}
	public void setAddress1(String addr1){
		this.address1 = addr1;
	}

	public String getAddress2(){
		return this.address2;
	}
	public void setAddress2(String addr2){
		this.address2 = addr2;
	}
	public String getDOB(){
		return this.dob;
	}
	public void setDOB(String dob){
		this.dob = dob;
	}
	public String getProvider(){
		return this.provider;
	}
	public void setProvider(String provider){
		this.provider = provider;
	}

	public List<Order> getOrders() {
		return this.orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}

