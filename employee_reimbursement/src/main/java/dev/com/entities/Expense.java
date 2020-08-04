package dev.com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import dev.com.exceptions.NegativeAmount;

@Entity
@Table(name = "expense")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "expID")
	private int expID;
	
	@Column(name = "expDate")
	private String expDate;
	
	@Column(name = "expType")
	private String expType;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private int amount; 
	
	@Column(name = "emplID")
	private int emplID;
	
	@Column(name = "status")
	private String status; 

	public Expense() {
		super();
	}

	public Expense(int expID, String expDate, String expType, String description, int amount, int emplID,
			String status) throws NegativeAmount {
		super();
		this.expID = expID;
		this.expDate = expDate;
		this.expType = expType;
		this.description = description;
		
		if(amount < 0) {
			throw new NegativeAmount();
			}
		else {
		this.amount = amount;}

		this.emplID = emplID;
		this.status = status;
	}

	public int getExpID() {
		return expID;
	}

	public void setExpID(int expID) {
		this.expID = expID;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) throws NegativeAmount {
		if(amount < 0) {
			throw new NegativeAmount();
		}else {
		this.amount = amount;}
	}

	public int getEmplID() {
		return emplID;
	}

	public void setEmplID(int emplID) {
		this.emplID = emplID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Expense [expID=" + expID + ", expDate=" + expDate + ", expType=" + expType + ", description="
				+ description + ", amount=" + amount + ", emplID=" + emplID + ", status=" + status + "]";
	}

	
	

}
