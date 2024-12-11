package com.example.quan_ly_thu_vien.Service;

import com.example.quan_ly_thu_vien.Model.Book;
import com.example.quan_ly_thu_vien.Repository.BookRepository;

import java.util.Date;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public Book getBookByCode(String bookCode) {
        return bookRepository.getBookByCode(bookCode);
    }

    public void decreaseBookQuantity(String bookCode) {
        bookRepository.decreaseBookQuantity(bookCode);
    }

    public boolean borrowBook(String borrowCode, String bookCode, int studentId, Date issueDate, Date returnDate) {
        Book book = bookRepository.getBookByCode(bookCode);
        if (book != null && book.getQuantity() > 0 && returnDate.after(issueDate)) {
            bookRepository.decreaseBookQuantity(bookCode);
            bookRepository.borrowBook(borrowCode, bookCode, studentId, (java.sql.Date) issueDate, (java.sql.Date) returnDate);

            return true;
        }
        return false;
    }
}
