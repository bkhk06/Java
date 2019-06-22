package ld.springboot_rest_demo2;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class TestBookController {
    private MockMvc mvc;

    private RequestBuilder request = null;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new BookController()).build();
        request = null;
    }

    public void testGet() throws Exception{
        request = get("/books/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    public void testPost() throws Exception{
        Book book = new Book();
        book.setBookId(Long.parseLong("1"));
        book.setTitile("Spring Boot Tutorial");
        book.setAuthor("bluecoffee");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBook = objectMapper.writeValueAsString(book);

        request = post("/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBook.getBytes());

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        String respStr = mvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[" + jsonBook + "]")))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testPost.resp:"+respStr);
    }

    public void testPut() throws Exception{
        Book book = new Book();
        book.setBookId(Long.parseLong("1"));
        book.setTitile("Spring Boot学习教程");
        book.setAuthor("Alex Qian");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBook = objectMapper.writeValueAsString(book);

        request = put("/books/" + book.getBookId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBook.getBytes());

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        String respStr = mvc.perform(get("/books/"+book.getBookId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(jsonBook)))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testPut.resp:"+respStr);
    }

    public void testDelete() throws Exception{
        request = delete("/books/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        String respStr = mvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testDelete.resp:"+respStr);

    }

    @Test
    public void testSuite() throws Exception{
        this.testGet();//获取一本书籍
        this.testPost();//创建一本书籍
        this.testPut();//更新一本书籍
        this.testDelete();//删除一本书籍
    }
}
