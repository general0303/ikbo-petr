package ru.mirea.serverikbo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRespository extends JpaRepository<Book, Integer> {
    List<Book> findAllBy();
    Book findBooksById(Integer id);
}
