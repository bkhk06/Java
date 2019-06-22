package ld.springboot_jdbc_template_demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/bookOps")
    public void bookOps(){
        Book book1 = new Book();
        book1.setAuthor("王实甫");
        book1.setName("西厢记");
        book1.setPrice(30f);
        book1.setPublicationDate(new Date());
        int id = bookService.addBook(book1);
        System.out.println("addBook>>>"+id);

        Book book2 = new Book();
        book2.setAuthor("鲁迅");
        book2.setName("朝花夕拾");
        book2.setPrice(30f);
        book2.setPublicationDate(new Date());
        int updateBook = bookService.addBook(book2);
        System.out.println("addBook>>>"+updateBook);



    }
}
