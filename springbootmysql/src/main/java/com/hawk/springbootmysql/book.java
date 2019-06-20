package com.hawk.springbootmysql;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.chart.PieChart;

import java.util.Date;

public class book {
    private String name;
    private String age;
    @JsonIgnore
    private Float price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;
}
