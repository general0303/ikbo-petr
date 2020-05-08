package ru.mirea.serverikbo;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    private Integer id;
    private String name;
    private List<AuthorDTO> authorList;

    public List<BookDTO> getBookDTOList(List<Book> bookList){
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList){
            authorList = new ArrayList<>();
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            for (Author author : book.getAuthors()){
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(author.getId());
                authorDTO.setName(author.getName());
                authorList.add(authorDTO);
            }
            bookDTO.setAuthorList(authorList);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    public List<AuthorDTO> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorDTO> authorList) {
        this.authorList = authorList;
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
