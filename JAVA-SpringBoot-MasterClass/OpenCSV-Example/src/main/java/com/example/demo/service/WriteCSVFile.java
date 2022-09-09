package com.example.demo.service;

import com.example.demo.dto.Input;
import com.example.demo.dto.Output;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
//https://dev.to/franzwong/writing-csv-file-with-opencsv-without-capitalized-headers-and-follows-declaration-order-207e
public class WriteCSVFile {

        public void writeFile(List<Input> inputList) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
            List<Output> outputList=new ArrayList<>();
            FileWriter writer=new FileWriter("./data/Output.csv");

            for(Input input : inputList)
            {
                Output output=Output.builder()
                                .id(input.getId())
                                .name(input.getName())
                                .status("PROCESSED")
                                .build();
                log.info(output.toString());
                outputList.add(output);
            }

            log.info("Total number of outputList : {}",outputList.size());

            String [] columnWriteOrder= {"Name","Id","Status"};

            HeaderColumnNameMappingStrategy<Output> mappingStrategy=new HeaderColumnNameMappingStrategy<>();
            mappingStrategy.setType(Output.class);
            //mappingStrategy.setColumnOrderOnWrite( new CustomComparator());


            String headerLine = Arrays.stream(Output.class.getDeclaredFields())
                    .map(field -> field.getAnnotation(CsvBindByName.class))
                    .filter(Objects::nonNull)
                    .map(CsvBindByName::column)
                    .collect(Collectors.joining(","));

            try (StringReader reader = new StringReader(headerLine)) {
                CsvToBean<Output> csv = new CsvToBeanBuilder<Output>(reader)
                        .withType(Output.class)
                        .withMappingStrategy(mappingStrategy)
                        .build();
                for (Output csvRow : csv) {}
            }

            StatefulBeanToCsvBuilder<Output> builder = new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =builder.withMappingStrategy(mappingStrategy).build();
            beanWriter.write(outputList);
            writer.close();

        }
}
