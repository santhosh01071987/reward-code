package com.rewards.model;


public class RewardsOfCustomer {
	
	private Long customerId;
	private String customerMailId;
	private String customerPhoneNumber;
	private Double firstMonthRewards;
	private Double secondMonthRewards;
	private Double thirdMonthRewards;
	private Double threeMonthsRewards;
	
	public RewardsOfCustomer() { }
	
	public RewardsOfCustomer(Long customerId, String customerMailId, String customerPhoneNumber, Double firstMonthRewards,
			Double secondMonthRewards, Double thirdMonthRewards, Double threeMonthsRewards) {
		super();
		this.customerId = customerId;
		this.customerMailId = customerMailId;
		this.customerPhoneNumber = customerPhoneNumber;
		this.firstMonthRewards = firstMonthRewards;
		this.secondMonthRewards = secondMonthRewards;
		this.thirdMonthRewards = thirdMonthRewards;
		this.threeMonthsRewards = threeMonthsRewards;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerMailId() {
		return customerMailId;
	}

	public void setCustomerMailId(String customerMailId) {
		this.customerMailId = customerMailId;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public Double getFirstMonthRewards() {
		return firstMonthRewards;
	}

	public void setFirstMonthRewards(Double firstMonthRewards) {
		this.firstMonthRewards = firstMonthRewards;
	}

	public Double getSecondMonthRewards() {
		return secondMonthRewards;
	}

	public void setSecondMonthRewards(Double secondMonthRewards) {
		this.secondMonthRewards = secondMonthRewards;
	}

	public Double getThirdMonthRewards() {
		return thirdMonthRewards;
	}

	public void setThirdMonthRewards(Double thirdMonthRewards) {
		this.thirdMonthRewards = thirdMonthRewards;
	}

	public Double getThreeMonthsRewards() {
		return threeMonthsRewards;
	}

	public void setThreeMonthsRewards(Double threeMonthsRewards) {
		this.threeMonthsRewards = threeMonthsRewards;
	}


	

}
