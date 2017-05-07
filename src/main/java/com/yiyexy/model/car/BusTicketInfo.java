package com.yiyexy.model.car;

import java.util.Date;

/**
 * <p>Create on 2017/05/07</p>
 *
 * @author stormma
 *
 * @description: <p>用户购买大巴模板类</p>
 */
public class BusTicketInfo {

	/**
	 * uid
	 */
	private int uid;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 地址
 	 */
	private String address;

	/**
	 * <p>购票数目</p>
 	 */
	private int amount;

	/**
	 * 出发日期
 	 */
	private Date startDate;

	/**
	 * 出发时间
 	 */
	private String startTime;

	/**
	 * 目的地
	 */
	private String arrive;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getArrive() {
		return arrive;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	@Override
	public String toString() {
		return "BusTicketInfo{" +
				"uid=" + uid +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", amount=" + amount +
				", startDate=" + startDate +
				", startTime='" + startTime + '\'' +
				", arrive='" + arrive + '\'' +
				'}';
	}
}