package edu.selu.siddhanta.patientmanager;

/**
 * Created by Siddhanta on 4/1/2017.
 */
 class Order {
	private String order_type, medicine, dosage,last_refill,refill_rem,id;


	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getLast_refill() {
		return last_refill;
	}
	public void setLast_refill(String last_refill) {
		this.last_refill = last_refill;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getRefill_rem() {
		return refill_rem;
	}
	public void setRefill_rem(String refill_rem) {
		this.refill_rem = refill_rem;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
}

