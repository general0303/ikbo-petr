package ru.mirea.serverikbo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Author")
public class Author{
    @Id
    @Column(name="id_author")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name_author", length = 50, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name="Author_Book",
            joinColumns = @JoinColumn(name="id_author", referencedColumnName = "id_author"),
            inverseJoinColumns = @JoinColumn(name="id_book", referencedColumnName = "id_book")
    )
    private List<Book> books= new ArrayList<>();

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


    public String toString() {
        return "Author{" + id + ", name='" + '\'' + '}';
    }
}
