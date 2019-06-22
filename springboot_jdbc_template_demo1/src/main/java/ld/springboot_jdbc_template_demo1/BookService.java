package ld.springboot_jdbc_template_demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;
    public int addBook(Book book){
        return bookDAO.addBook(book);
    }
    public int updateBook(Book book){
        return bookDAO.updateBook(book);
    }

    public int deleteBookById(Integer id){
        return bookDAO.deleteBookById(id);
    }

    public Book getBookById(Integer id){
        return bookDAO.getBookById(id);
    }

    public List<Book> getALlBooks(){
        return (List<Book>) bookDAO.getAllBooks();
    }


}
