package com.example.springConfiguration.component;

import com.example.springConfiguration.service.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionHolder
{

    private List<SimpleBean> ListOfSimpleBean;

    @Autowired
    public void setListOfSimpleBean(List<SimpleBean> listOfSimpleBean) {
        this.ListOfSimpleBean = listOfSimpleBean;
    }
}
