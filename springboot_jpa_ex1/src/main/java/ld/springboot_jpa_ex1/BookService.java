package ld.springboot_jpa_ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.Page;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;
    public void addBook(Book book){
        bookDAO.save(book);
    }

    Page getBookByPage(PageRequest pageable){
        return (Page) bookDAO.findAll(pageable);
    }

    public List<Book> getBooksByAuthorStartingWith(String author){
        return bookDAO.getBooksByAuthorStartingWith(author);
    }

    public List<Book> getBooksByPriceGreatedThan(Float price){
        return bookDAO.getBooksByPriceGreaterThan(price);
    }

    public Book getMaxIdBook(){
        return bookDAO.getMaxIdBook();
    }

    public List<Book> getBookByIdAndAuthor(String author,Integer id){
        return bookDAO.getBookByIdAndAutor(author,id);
    }

    public List<Book> getBooksByIdAndName(String name,Integer id){
        return bookDAO.getBookByIdAndAndName(name, id);
    }
}
