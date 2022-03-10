package cn.adcc.mongodb_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LD
 * @version 1.0
 * @date 2022/3/10 13:35
 */
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Student implements Serializable {
    @Id// 必须指定id列
    private String studentId;

    private String studentName;

    private Integer studentAge;

    private Double studentScore;

    private Date studentBirthday;

    public void setStudentId(String s) {
        studentId=s;
    }

    public void setStudentName(String s) {
        studentName=s;
    }


    public void setStudentAge(int i) {
        studentAge=i;
    }

    public void setStudentScore(double v) {
        studentScore=v;
    }

    public void setStudentBirthday(Date date) {
        studentBirthday=date;
    }
}
