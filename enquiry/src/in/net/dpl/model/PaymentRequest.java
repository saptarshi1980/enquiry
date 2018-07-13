package in.net.dpl.model;

public class PaymentRequest {
	
	String conNo;
	String billMonth;
	double billAmount=0.0;
	double rebate=0.0;
	String dueDate;
	String billMonthWord;
	String consumer_name;
	double sdBill=0;
	double billValue=0;
	public double getBillValue() {
		return billValue;
	}
	public void setBillValue(double billValue) {
		this.billValue = billValue;
	}
	public double getSdBill() {
		return sdBill;
	}
	public void setSdBill(double sdBill) {
		this.sdBill = sdBill;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getBillMonthWord() {
		return billMonthWord;
	}
	public void setBillMonthWord(String billMonthWord) {
		this.billMonthWord = billMonthWord;
	}
	public String getConsumer_name() {
		return consumer_name;
	}
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	
	
	

}
