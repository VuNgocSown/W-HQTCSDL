package com.thunga.web.model;

import com.thunga.web.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDetailInfo {
	private Book book;
	private Integer quantity;
	
	public CartDetailInfo() {
        this.quantity = 0;
    }
    
    public Book getBook() {
        return this.book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Integer getAmount() {
        return this.book.getPrice() * this.quantity;
    }
}
