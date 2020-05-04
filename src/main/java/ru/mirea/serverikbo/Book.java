package ru.mirea.serverikbo;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Book")
public class Book {
    @Id
    @Column(name="id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name_book", length = 50, nullable = false)
    private String name;

    @ManyToMany (mappedBy = "books")
    private List<Author> authors =new ArrayList<>();

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


    public String toString(){
        return "Book{" + id + ", name='" + '\''+'}';
    }
}
