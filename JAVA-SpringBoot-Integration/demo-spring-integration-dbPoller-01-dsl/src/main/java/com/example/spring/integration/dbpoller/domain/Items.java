package com.example.spring.integration.dbpoller.domain;

import java.util.Date;

public class Items {
    private String ITEM_ID;
    private String DESCRIPTION;
    private int INVENTORY_STATUS;
    private Date LAST_UPDATED_DATE;

    public String getITEM_ID() {
        return ITEM_ID;
    }

    public void setITEM_ID(String ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getINVENTORY_STATUS() {
        return INVENTORY_STATUS;
    }

    public void setINVENTORY_STATUS(int INVENTORY_STATUS) {
        this.INVENTORY_STATUS = INVENTORY_STATUS;
    }

    public Date getLAST_UPDATED_DATE() {
        return LAST_UPDATED_DATE;
    }

    public void setLAST_UPDATED_DATE(Date LAST_UPDATED_DATE) {
        this.LAST_UPDATED_DATE = LAST_UPDATED_DATE;
    }
}
