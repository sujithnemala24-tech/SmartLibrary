package com.smartlibrary.model;
import java.time.LocalDate;
public record BorrowRecord(String userId,String bookId,LocalDate borrowedOn,LocalDate returnedOn) { public BorrowRecord returned(){return new BorrowRecord(userId,bookId,borrowedOn,LocalDate.now());} public String toCsv(){return userId+","+bookId+","+borrowedOn+","+(returnedOn==null?"":returnedOn);} }
