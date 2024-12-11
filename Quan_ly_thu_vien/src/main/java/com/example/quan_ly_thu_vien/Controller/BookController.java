package com.example.quan_ly_thu_vien.Controller;

import com.example.quan_ly_thu_vien.Model.Book;
import com.example.quan_ly_thu_vien.Model.Student;
import com.example.quan_ly_thu_vien.Service.BookService;
import com.example.quan_ly_thu_vien.Service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookController", urlPatterns = "/books")
public class BookController extends HttpServlet {
    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();

        request.setAttribute("books", books);

        RequestDispatcher dispatcher = request.getRequestDispatcher("bookList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookCode = request.getParameter("bookCode");
        String studentId = request.getParameter("studentId");
        String issueDateStr = request.getParameter("issueDate");
        String returnDateStr = request.getParameter("returnDate");
        String borrowCode = "MS-" + System.currentTimeMillis(); // Generate unique borrow code

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date issueDate = null;
        Date returnDate = null;

        try {
            issueDate = new java.sql.Date(dateFormat.parse(issueDateStr).getTime());
            returnDate = new java.sql.Date(dateFormat.parse(returnDateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Book book = bookService.getBookByCode(bookCode);
        if (book != null && book.getQuantity() > 0 && returnDate.after(issueDate)) {
            bookService.decreaseBookQuantity(bookCode);
            bookService.borrowBook(borrowCode, bookCode, Integer.parseInt(studentId), issueDate, returnDate);
            response.sendRedirect("books");
        } else {
            request.setAttribute("errorMessage", "Không thể mượn sách, vui lòng kiểm tra lại thông tin.");
            doGet(request, response);
        }
    }
}
