package com.example.quan_ly_thu_vien.Repository;

import com.example.quan_ly_thu_vien.Model.Book;
import com.example.quan_ly_thu_vien.Repository.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.quan_ly_thu_vien.Repository.BaseRepository.getConnection;

public class BookRepository {
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Sach";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setCode(resultSet.getString("ma_sach"));
                book.setName(resultSet.getString("ten_sach"));
                book.setAuthor(resultSet.getString("tac_gia"));
                book.setDescription(resultSet.getString("mo_ta"));
                book.setQuantity(resultSet.getInt("so_luong"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookByCode(String bookCode) {
        Book book = null;
        String sql = "SELECT * FROM Sach WHERE ma_sach = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bookCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                book = new Book();
                book.setCode(resultSet.getString("ma_sach"));
                book.setName(resultSet.getString("ten_sach"));
                book.setAuthor(resultSet.getString("tac_gia"));
                book.setDescription(resultSet.getString("mo_ta"));
                book.setQuantity(resultSet.getInt("so_luong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void decreaseBookQuantity(String bookCode) {
        String sql = "UPDATE Sach SET so_luong = so_luong - 1 WHERE ma_sach = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, bookCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrowBook(String borrowCode, String bookCode, int studentId, Date issueDate, Date returnDate) {
        String sql = "INSERT INTO TheMuonSach (ma_muon_sach, ma_sach, ma_hoc_sinh, trang_thai, ngay_muon, ngay_tra) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, borrowCode);
            preparedStatement.setString(2, bookCode);
            preparedStatement.setInt(3, studentId);
            preparedStatement.setBoolean(4, true);
            preparedStatement.setDate(5, issueDate);
            preparedStatement.setDate(6, returnDate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
