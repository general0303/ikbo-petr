package ru.mirea.serverikbo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRespository extends JpaRepository<Author, Integer> {
    List<Author> findAllBy();
}
