package ld.springboot_rest_demo2;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    // 创建线程安全的Map
    static Map<Long, Book> books = Collections.synchronizedMap(new HashMap<Long, Book>());

    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<Book> getUserList() {
        // 处理"/books/"的GET请求，用来获取图书列表
        // 还可以通过@RequestParam传递参数来进行查询条件或者翻页信息的传递
        List<Book> r = new ArrayList<Book>(books.values());
        return r;
    }

    @RequestMapping(value="/", method=RequestMethod.POST,produces = "application/json")
    public String createBook(@RequestBody Book book) {
        // 处理"/books/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        books.put(book.getBookdId(), book);
        return "success";
    }

    @RequestMapping(value="/{bookId}", method=RequestMethod.GET)
    public Book getBook(@PathVariable Long bookId) {
        // 处理"/books/{id}"的GET请求，用来获取url中id值的Book信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return books.get(bookId);
    }

    @RequestMapping(value="/{bookId}", method=RequestMethod.PUT)
    public String putBook(@PathVariable Long bookId, @RequestBody Book book) {
        // 处理"/books/{bookId}"的PUT请求，用来更新Book信息
        Book b = books.get(bookId);
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        books.put(bookId, b);
        return "success";
    }

    @RequestMapping(value="/{bookId}", method=RequestMethod.DELETE)
    public String deleteBook(@PathVariable Long bookId) {
        // 处理"/books/{bookId}"的DELETE请求，用来删除Book
        books.remove(bookId);
        return "success";
    }
}
