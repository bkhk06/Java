package ld.springboot_jpa_ex1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,Integer> {
    List<Book> getBooksByPriceGreaterThan(Float price);
    List<Book> getBooksByAuthorStartingWith(String author);

    @Query(value = "select * from t_book where id=(slect max(id) from t_book)",
    nativeQuery = true)
    Book getMaxIdBook();

    @Query("")
}
