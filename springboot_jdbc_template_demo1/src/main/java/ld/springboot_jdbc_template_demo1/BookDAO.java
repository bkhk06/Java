package ld.springboot_jdbc_template_demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int addBook(Book book) {
        return jdbcTemplate.update("INERT INTO book(name,author) VALUES (?,?)",
        book.getName(),book.getAuthor());
    }

    public  int updateBook(Book book){
        return jdbcTemplate.update("UPDATE book SET name=?,author=? WHERE id=?",
                book.getName(),book.getAuthor(),book.getId());
    }

    public  int deleteBookById(Integer id){
        return jdbcTemplate.update("DELETE FROM book WHERE id=?");
    }

    public Book getBookById(Integer id){
        return jdbcTemplate.queryForObject("select * from book where id=?",
                new BeanPropertyRowMapper<>(Book.class),id);
    }

    public Book getAllBooks(){
        return jdbcTemplate.queryForObject("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }
}
