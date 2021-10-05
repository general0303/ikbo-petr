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

    @GetMapping(path="/authors")
    public @ResponseBody
    List<Author> getAllAuthors(){
        return ar.findAll();
    }
    @GetMapping(path="/books")
    public @ResponseBody
    List<Book> getAllBooks(){
        return br.findAll();
    }

    @PostMapping(path="/add_author")
    public @ResponseBody String addNewAuthor(@RequestParam String name){
        Author author = new Author();
        author.setName(name);
        ar.save(author);

        return "Saved author!";
    }
    @PostMapping(path="/add_book")
    public @ResponseBody String addNewBook(@RequestParam String name, @RequestParam List<Integer> authors){
        Book book=new Book();
        book.setName(name);
        for(Integer a: authors) {
            Author aaa = ar.findAuthorsById(a);
            book.getAuthors().add(aaa);
        }
        br.save(book);
        return "Saved book!";
    }
    @DeleteMapping(path="/author/delete/{author_id}")
    public @ResponseBody String deleteAuthor(@PathVariable Integer author_id){
        Author author = ar.findAuthorsById(author_id);
        ar.delete(author);
        return "Author deleted";
    }
    @DeleteMapping(path="/book/delete/{book_id}")
    public @ResponseBody String deleteBook(@PathVariable Integer book_id){
        Book book=br.findBooksById(book_id);
        br.delete(book);
        return "Book deleted";
    }
    @PutMapping(path="/author/edit/{author_id}")
    public @ResponseBody String editAuthor(@PathVariable Integer author_id, @RequestParam List<Integer> ids, @RequestParam String name){
        List<Book> books=new ArrayList<>();
        for (Integer i:ids)
            books.add(br.findBooksById(i));
        Author author=ar.findAuthorsById(author_id);
        author.setName(name);
        author.setBooks(books);
        for (Book book: author.getBooks()){
            book.getAuthors().add(author);
        }
        return "Author edited";
    }
    @PutMapping(path="/book/edit/{book_id}")
    public @ResponseBody String editBook(@PathVariable Integer book_id, @RequestParam List<Integer> ids, @RequestParam String name){
        Book book=br.findBooksById(book_id);
        List<Author> authors=new ArrayList<>();
        for (Integer i:ids)
            authors.add(ar.findAuthorsById(i));
        book.setName(name);
        book.setAuthors(authors);
        return "Book edited";
    }
    @GetMapping(path="/author/{author_id}")
    public @ResponseBody List<Book> getBooks(@PathVariable Integer author_id){
        return ar.findAuthorsById(author_id).getBooks();
    }
    @GetMapping(path="/booksmorethen3")
    public @ResponseBody List<Book> getMoreAuthors(){
        List<Book> books=new ArrayList<>();
        for (Book b: br.findAll()){
            if (b.getAuthors().size()>3) books.add(b);
        }
        return books;
    }
    @GetMapping(path="/maxauthor")
    public @ResponseBody Author maxAuthor(){
        Author author=new Author();
        int max=0;
        for (Author a: ar.findAll()){
            if (a.getBooks().size()>max){
                max=a.getBooks().size();
                author=a;
            }
        }
        return author;
    }
}