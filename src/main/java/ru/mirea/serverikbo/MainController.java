package ru.mirea.serverikbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;




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
    Iterable<Author> getAllAuthors(){
        return ar.findAll();
    }
    @GetMapping(path="/book")
    public @ResponseBody
    Iterable<Book> getAllBooks(){
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
    public @ResponseBody String addNewBook(@RequestParam String name){
        Book book=new Book();
        book.setName(name);
        br.save(book);
        return "Saved book!";
    }
}
