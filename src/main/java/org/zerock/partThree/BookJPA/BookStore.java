package org.zerock.partThree.BookJPA;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class BookStore {

    private Integer id;

    private String name;

    @OneToMany(mappedBy = "bookStore")
    private Set<Book>books = new HashSet<>();

    public void add(Book book){
        book.setBookStore(this);
//        this.books.add(book);
        book.getBookStore();
        getBooks().add(book);
    }
}
