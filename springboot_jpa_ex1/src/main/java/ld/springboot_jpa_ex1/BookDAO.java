package ld.springboot_jpa_ex1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sun.jvm.hotspot.debugger.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface BookDAO extends JpaRepository<Book,Integer> {
    List<Book> getBooksByPriceGreaterThan(Float price);
    List<Book> getBooksByAuthorStartingWith(String author);

    @Query(value = "select * from t_book where id=(slect max(id) from t_book)",
    nativeQuery = true)
    Book getMaxIdBook();

    @Query("select b from t_book where b.id>:id and b.author=:author")
    List<Book> getBookByIdAndAutor(@Param("author") String author,
                                   @Param("id") Integer id);

    @Query("select b from b_book b where b.id<?2 and b.name like  %?1%")
    List<Book> getBookByIdAndAndName(String name,Integer id);

    Page findAll(Pageable pageable);
}
