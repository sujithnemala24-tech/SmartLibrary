package com.smartlibrary.repository;
import com.smartlibrary.model.*;import java.util.*;
public interface LibraryRepository { Map<String,Book> loadBooks(); void saveBooks(Collection<Book> books); List<BorrowRecord> loadRecords();void saveRecords(List<BorrowRecord> records);List<Reservation> loadReservations();void saveReservations(List<Reservation> reservations); }
