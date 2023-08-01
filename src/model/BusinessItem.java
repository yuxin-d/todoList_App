package model;

import java.text.ParseException;

public class BusinessItem extends Item{

    public BusinessItem(String name, String dueDate) throws ParseException {
        super(name, dueDate);
    }

    BusinessItem(String name, String status, String dueDate) {
        super(name, status, dueDate);
    }


    @Override
    public String getItemType() {
        return "Business";
    }

    @Override
    public String toString() {
        return
                name + "   " +
                "Status: " + status + "   " +
                "DueDate: " + dueDate + "  ";
    }
}
