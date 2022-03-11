package cn.adcc.mongodb_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class MongodbTestApplicationTests {
    @Autowired
    private StudentDaoTypeOne studentDaoTypeOne;

    @Test
    void addOneStudent() throws InterruptedException {
//        插入10行
        for (Integer count = 0; count < 1000000; count++) {
            int randomNum= (int) Math.random ();
            Student student = new Student();
            //student.setStudentId("student_"+(count+randomNum)) ;//如果自己不去设置id则系统会分配给一个id
            student.setStudentName("Godfery"+count);
            student.setStudentAge(count);
            student.setStudentScore(98.5-count);
            student.setStudentBirthday(new Date());
            studentDaoTypeOne.save(student);
            Thread.sleep(1000);
            System.out.println("NO."+count+": "+student.getStudentInfo());

        }
    }

    @Test
    void deleteOneStudentByStudentId(){
//        删除id为student_0的学生
        studentDaoTypeOne.deleteById("student_0");
    }

    @Test
    void updateOneStudent(){
//        修改姓名为Godfery1的Student年龄为22
        Student student = studentDaoTypeOne.getAllByStudentName("Godfery1");
        student.setStudentAge(22);
        studentDaoTypeOne.save(student);

    }

    @Test
    void getOneStudentByStudentId(){
        System.out.println(studentDaoTypeOne.findById("student_1"));
    }

    @Test
    void getAllStudent(){
        List<Student> studentList = studentDaoTypeOne.findAll();
        studentList.forEach(System.out::println);
    }

    @Test
    void contextLoads() {
    }

}
