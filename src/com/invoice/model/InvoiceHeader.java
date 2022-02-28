package com.invoice.model;

import java.util.ArrayList;
import java.util.Date;


public class InvoiceHeader{
    //some variable using in program
    private int Invoice_Number;
    private Date Invoice_Date;
    private String Customer_Name;
    private ArrayList<InvoiceLine> lines;
    private double InvoiceTotal;


    public InvoiceHeader(int invoiceNum, Date invoiceDate, String customerName) {
        this.Invoice_Number = invoiceNum;
        this.Invoice_Date = invoiceDate;
        this.Customer_Name = customerName;
    }

//set and get methods
    public int getInvoice_Number() {
        return Invoice_Number;
    }

    public void setInvoice_Number(int invoice_Number) {
        this.Invoice_Number = invoice_Number;
    }

    public Date getInvoice_Date() {
        return Invoice_Date;
    }

    public void setInvoice_Date(Date invoice_Date) {
        this.Invoice_Date = invoice_Date;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.Customer_Name = customer_Name;
    }

    public ArrayList<InvoiceLine> getLines() {

        if (lines==null){
            lines = new ArrayList<>();}
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public void addLines (InvoiceLine total){
        getLines().add(total);
        setInvoiceTotal(getInvoiceTotal() + total.getItem_Total());
    }

    public double getInvoiceTotal() {
        return InvoiceTotal;
    }

    public void setInvoiceTotal(double invoiceTotal) {
        this.InvoiceTotal = invoiceTotal;
    }
}





