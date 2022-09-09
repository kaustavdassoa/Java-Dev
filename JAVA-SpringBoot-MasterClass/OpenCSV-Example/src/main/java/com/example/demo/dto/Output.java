package com.example.demo.dto;



import com.opencsv.bean.CsvBindByName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Output {

    @CsvBindByName(column = "Status",required = true)
    public String  status;

    @CsvBindByName(column = "Name",required = true)
    public String  name;

    @CsvBindByName(column = "ID",required = true)
    public Integer id;
}
