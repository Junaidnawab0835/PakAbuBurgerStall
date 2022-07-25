package com.example.pakabuburgerstall;

public class Orders {
    String order_id;
    String total_payment;
    String payment_method;
    String date;

    public Orders(String order_id, String total_payment, String payment_method, String date) {
        this.order_id = order_id;
        this.total_payment = total_payment;
        this.payment_method = payment_method;
        this.date = date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
