package model;


import java.text.ParseException;

public class RegularItem extends Item{
    public RegularItem(String name, String dueDate) throws ParseException {
        super(name, dueDate);
    }

    RegularItem(String name, String status, String dueDate) {
        super(name, status, dueDate);
    }

    @Override
    public String getItemType() {
        return "Regular";
    }

    @Override
    public String toString() {
        return name + "   " +
                "Status: " + status + "   " +
                "DueDate: " + dueDate + "  ";
    }
}
