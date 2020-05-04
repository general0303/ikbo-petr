package ru.mirea.serverikbo;

import org.springframework.data.repository.CrudRepository;

public interface BookRespository extends CrudRepository<Book, Integer> {
}
