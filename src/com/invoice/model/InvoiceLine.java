package com.invoice.model;

public class InvoiceLine {
    //Public Variable Using In Program
    private String Item_Name;
    private double Item_Price;
    private int Count;
    private double Item_Total;
    private InvoiceHeader InvoiceHeader;

    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceHeader invoiceHeader) {
        this.Item_Name = itemName;
        this.Item_Price = itemPrice;
        this.Count = count;
        this.setItem_Total(this.Count * this.Item_Price);
        this.InvoiceHeader = invoiceHeader;
    }
    // Set and get methods

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.Item_Name = item_Name;
    }

    public double getItem_Price() {
        return Item_Price;
    }

    public void setItem_Price(double item_Price) {
        this.Item_Price = item_Price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        this.Count = count;
    }

    public double getItem_Total() {
        return Item_Total;
    }

    public void setItem_Total(double item_Total) {
        this.Item_Total = item_Total;
    }

    public InvoiceHeader getInvoiceHeader() {
        return InvoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.InvoiceHeader = invoiceHeader;
    }
}

