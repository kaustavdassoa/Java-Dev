package com.example.spring6.practice.dataService;

import java.util.Arrays;

public class MongoDBDatasource implements Datasource {
    @Override
    public int[] retrieveData() {
        int[] array={2,2};
        return array;
    }
}
