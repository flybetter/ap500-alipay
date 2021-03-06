package org.alipay.entity;
// Generated 2016-7-20 9:48:04 by Hibernate Tools 3.6.0.Final

import java.math.BigDecimal;


/**
 * Feepackage generated by hbm2java
 */
public class Feepackage  {

	private Integer id;
	private String number;
	private String name;
	private String description;
	private String provider;
	private String phone;
	private double price;
	private double discount;
	private int leastPurchase;
	private char status;
	private String creator;
	private char type;
	private char payType;
	private char chargingWay;
	private int stockCount;
	private Integer soldCount;
	private Double flux;
	private Double total;
	public Feepackage() {
	}

	public Feepackage(String number, String name, String description, String provider, String phone, double price,
			double discount, int leastPurchase, char status, String creator,  char type, char payType,
			char chargingWay, int stockCount) {
		this.number = number;
		this.name = name;
		this.description = description;
		this.provider = provider;
		this.phone = phone;
		this.price = price;
		this.discount = discount;
		this.leastPurchase = leastPurchase;
		this.status = status;
		this.creator = creator;
		this.type = type;
		this.payType = payType;
		this.chargingWay = chargingWay;
		this.stockCount = stockCount;
	}

	public Feepackage(String number, String name, String description, String provider, String phone, double price,
			double discount, int leastPurchase, char status, String creator, 
			char type, char payType, char chargingWay, int stockCount, Integer soldCount, Double flux) {
		this.number = number;
		this.name = name;
		this.description = description;
		this.provider = provider;
		this.phone = phone;
		this.price = price;
		this.discount = discount;
		this.leastPurchase = leastPurchase;
		this.status = status;
		this.creator = creator;
		this.type = type;
		this.payType = payType;
		this.chargingWay = chargingWay;
		this.stockCount = stockCount;
		this.soldCount = soldCount;
		this.flux = flux;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getLeastPurchase() {
		return this.leastPurchase;
	}

	public void setLeastPurchase(int leastPurchase) {
		this.leastPurchase = leastPurchase;
	}

	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public char getType() {
		return this.type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public char getPayType() {
		return this.payType;
	}

	public void setPayType(char payType) {
		this.payType = payType;
	}

	public char getChargingWay() {
		return this.chargingWay;
	}

	public void setChargingWay(char chargingWay) {
		this.chargingWay = chargingWay;
	}

	public int getStockCount() {
		return this.stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public Integer getSoldCount() {
		return this.soldCount;
	}

	public void setSoldCount(Integer soldCount) {
		this.soldCount = soldCount;
	}

	public Double getFlux() {
		return this.flux;
	}

	public void setFlux(Double flux) {
		this.flux = flux;
	}
	
	
	public void setTotal(Double Total) {
		this.total=Total;
	}

	public Double getTotal() {
		BigDecimal b1 = new BigDecimal(this.getPrice());
	    BigDecimal b2 = new BigDecimal(this.getDiscount());
		total = Double.valueOf(b1.subtract(b2).doubleValue());
	    return total;
	}


	@Override
	public String toString() {
		return "Feepackage [id=" + id + ", number=" + number + ", name=" + name + ", description=" + description
				+ ", provider=" + provider + ", phone=" + phone + ", price=" + price + ", discount=" + discount
				+ ", leastPurchase=" + leastPurchase + ", status=" + status + ", creator=" + creator + ", type=" + type + ", payType=" + payType
				+ ", chargingWay=" + chargingWay + ", stockCount=" + stockCount + ", soldCount=" + soldCount + ", flux="
				+ flux + "]";
	}
	
	
}
