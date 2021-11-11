/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Models;

/**
 *
 * @author Tran Van Thanh
 */
public class billCTT {
    private String IDOrderString;
    private String TimeOrder;
    private String IDProduct;
    private String ProductName;
    private String Size;
    private int Quantity;
    private double Price;
    private String Note;
    private String IDCust;

    public billCTT() {
    }

    public billCTT(String IDOrderString, String TimeOrder, String IDProduct, String ProductName, String Size, int Quantity, double Price, String Note, String IDCust) {
        this.IDOrderString = IDOrderString;
        this.TimeOrder = TimeOrder;
        this.IDProduct = IDProduct;
        this.ProductName = ProductName;
        this.Size = Size;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Note = Note;
        this.IDCust = IDCust;
    }

    public String getIDOrderString() {
        return IDOrderString;
    }

    public void setIDOrderString(String IDOrderString) {
        this.IDOrderString = IDOrderString;
    }

    public String getTimeOrder() {
        return TimeOrder;
    }

    public void setTimeOrder(String TimeOrder) {
        this.TimeOrder = TimeOrder;
    }

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public String getIDCust() {
        return IDCust;
    }

    public void setIDCust(String IDCust) {
        this.IDCust = IDCust;
    }
    
    
}
