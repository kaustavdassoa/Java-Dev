package com.example.demo.service;

import com.example.demo.dto.Input;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ReadCSVFile {

    public List<Input> readCSV() throws IOException
    {
        String fileName="./data/Inputdata.csv";
        Reader fileReader= Files.newBufferedReader(Paths.get(fileName));
        List<Input> inputList=new ArrayList<>();

        CsvToBean<Input> csvToBean= new CsvToBeanBuilder<Input>(fileReader)
                                    .withType(Input.class)
                                    .withIgnoreLeadingWhiteSpace(true)
                                    .build();


        csvToBean.stream().forEach(input -> inputList.add(input));
        log.info("Total number of records read : {}",inputList.size());
        return inputList;
    }
}
