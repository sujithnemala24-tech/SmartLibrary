package com.smartlibrary.repository;
import com.smartlibrary.model.*;import java.nio.file.*;import java.io.*;import java.util.*;
public class FileLibraryRepository implements LibraryRepository { private final Path dir; public FileLibraryRepository(Path dir){this.dir=dir;try{Files.createDirectories(dir);}catch(IOException e){throw new UncheckedIOException(e);}}
 private List<String> read(String name){try{Path p=dir.resolve(name);return Files.exists(p)?Files.readAllLines(p):new ArrayList<>();}catch(IOException e){throw new UncheckedIOException(e);}}
 private void write(String name,List<String> lines){try{Files.write(dir.resolve(name),lines);}catch(IOException e){throw new UncheckedIOException(e);}}
 public Map<String,Book> loadBooks(){Map<String,Book> m=new LinkedHashMap<>();for(String s:read("books.csv"))if(!s.isBlank())try{Book b=Book.fromCsv(s);m.put(b.getId(),b);}catch(RuntimeException ex){System.err.println("Skipping invalid books.csv row: "+ex.getMessage());}return m;}
 public void saveBooks(Collection<Book> bs){write("books.csv",bs.stream().map(Book::toCsv).toList());}
 public List<BorrowRecord> loadRecords(){List<BorrowRecord> out=new ArrayList<>();for(String s:read("borrow_records.csv")){String[] p=s.split(",",-1);if(p.length==4)try{out.add(new BorrowRecord(p[0],p[1],java.time.LocalDate.parse(p[2]),p[3].isBlank()?null:java.time.LocalDate.parse(p[3])));}catch(Exception ignored){}}return out;}
 public void saveRecords(List<BorrowRecord> r){write("borrow_records.csv",r.stream().map(BorrowRecord::toCsv).toList());}
 public List<Reservation> loadReservations(){List<Reservation> out=new ArrayList<>();for(String s:read("reservations.csv")){String[] p=s.split(",",-1);if(p.length==4)try{out.add(new Reservation(p[0],p[1],java.time.LocalDateTime.parse(p[2]),Boolean.parseBoolean(p[3])));}catch(Exception ignored){}}return out;}
 public void saveReservations(List<Reservation> r){write("reservations.csv",r.stream().map(Reservation::toCsv).toList());}
}
