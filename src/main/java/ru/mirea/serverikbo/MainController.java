package ru.mirea.serverikbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private AuthorRespository ar;
    @Autowired
    private BookRespository br;
    @RequestMapping(path="/")
    public String index() {
        return "index";
    }

    @GetMapping(path="/author")
    public @ResponseBody
    List<AuthorDTO> getAllAuthors(){
        AuthorDTO booksDTO = new AuthorDTO();
        return booksDTO.getAuthorDTOList(ar.findAll());
    }
    @GetMapping(path="/book")
    public @ResponseBody
    List<BookDTO> getAllBooks(){
        BookDTO peopleDTO = new BookDTO();
        return peopleDTO.getBookDTOList(br.findAll());
    }

    @GetMapping(path="/add_author")
    public @ResponseBody String addNewAuthor(@RequestParam String name){
        Author author = new Author();
        author.setName(name);
        ar.save(author);

        return "Saved author!";
    }
    @GetMapping(path="/add_book/{authors}")
    public @ResponseBody String addNewBook(@RequestParam String name, @RequestParam List<Integer> authors){
        Book book=new Book();
        book.setName(name);
        for(Integer a: authors) {
            Author aaa = ar.getOne(a);
            book.getAuthors().add(aaa);
        }
        br.save(book);
        return "Saved book!";
    }
    @GetMapping(path="/author/delete/{author_id}")
    public @ResponseBody String deleteAuthor(@RequestParam Integer author_id){
        Author author = ar.getOne(author_id);
        for (Book book: author.getBooks()){
            for (Author a: book.getAuthors()){
                if (author.getId()==a.getId())
                    book.getAuthors().remove(a);
            }
        }
        ar.delete(author);
        return "Author deleted";
    }
    @GetMapping(path="/book/delete/{book_id}")
    public @ResponseBody String deleteBook(@RequestParam Integer book_id){
        Book book=br.getOne(book_id);
        for (Author author: book.getAuthors()){
            for (Book b: author.getBooks()){
                if (book.getId()==b.getId())
                    author.getBooks().remove(b);
            }
        }
        br.delete(book);
        return "Book deleted";
    }
    @GetMapping(path="/author/edit/{author_id}")
    public @ResponseBody String editAuthor(@RequestParam Author author, @RequestParam List<Book> books, @RequestParam String name){
        for (Book book: author.getBooks()){
            for (Author a: book.getAuthors()){
                if (author.getId()==a.getId())
                    book.getAuthors().remove(a);
            }
        }
        author.setName(name);
        author.setBooks(books);
        for (Book book: author.getBooks()){
            book.getAuthors().add(author);
        }
        return "Author edited";
    }
    @GetMapping(path="/book/edit/{book_id}")
    public @ResponseBody String editBook(@RequestParam Book book, @RequestParam List<Author> authors, @RequestParam String name){
        for (Author author: book.getAuthors()){
            for (Book b: author.getBooks()){
                if (book.getId()==b.getId())
                    author.getBooks().remove(b);
            }
        }
        book.setName(name);
        book.setAuthors(authors);
        for(Author a: book.getAuthors()) {
            a.getBooks().add(book);
        }
        return "Book edited";
    }
    @GetMapping(path="/author/{author_id}")
    public @ResponseBody List<BookDTO> getBooks(@RequestParam Integer author_id){
        Author author=ar.getOne(author_id);
        BookDTO authorDTO = new BookDTO();
        return authorDTO.getBookDTOList(author.getBooks());
    }
    @GetMapping(path="/books")
    public @ResponseBody List<BookDTO> getmoreAuthors(){
        BookDTO people=new BookDTO();
        List<Book> books=new ArrayList<>();
        for (Book b: br.findAll()){
            if (b.getAuthors().size()>3) books.add(b);
        }
        return people.getBookDTOList(books);
    }
    @GetMapping(path="/maxauthor")
    public @ResponseBody String maxAuthor(){
        Author author=new Author();
        int max=0;
        for (Author a: ar.findAll()){
            if (a.getBooks().size()>max){
                max=a.getBooks().size();
                author=a;
            }
        }
        return author.getName();
    }
}