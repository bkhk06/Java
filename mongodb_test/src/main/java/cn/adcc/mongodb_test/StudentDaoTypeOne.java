package cn.adcc.mongodb_test;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author LD
 * @version 1.0
 * @date 2022/3/10 13:37
 */
/*
 * dao层写法一
 * 这里的用法其实和SpringDataJPA相似, 可根据需要来自定义方法
 *
 * 这种写法不需要写实现类
 *
 * MongoRepository<行对应的对象类型, 主键列类型>
 * */
public interface StudentDaoTypeOne extends MongoRepository<Student, String> {
    //    可根据需求自己定义方法, 无需对方法进行实现
    Student getAllByStudentName(String studentName);
}
