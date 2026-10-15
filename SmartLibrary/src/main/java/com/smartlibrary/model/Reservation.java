package com.smartlibrary.model;
import java.time.LocalDateTime;
public record Reservation(String userId,String bookId,LocalDateTime createdAt,boolean fulfilled) { public String toCsv(){return userId+","+bookId+","+createdAt+","+fulfilled;} }
