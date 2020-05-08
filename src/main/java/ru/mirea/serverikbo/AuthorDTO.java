package ru.mirea.serverikbo;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private Integer id;
    private String name;
    private List<BookDTO> bookList;

    public List<AuthorDTO> getAuthorDTOList(List<Author> authorList){
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for (Author author : authorList){
            bookList = new ArrayList<>();
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setId(author.getId());
            authorDTO.setName(author.getName());
            for (Book book : author.getBooks()){
                BookDTO bookDTO = new BookDTO();
                bookDTO.setId(book.getId());
                bookDTO.setName(book.getName());
                bookList.add(bookDTO);
            }
            authorDTO.setBookList(bookList);
            authorDTOList.add(authorDTO);
        }
        return authorDTOList;
    }

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDTO> authorList) {
        this.bookList = authorList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
