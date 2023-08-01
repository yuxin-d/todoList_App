package model;

import java.text.ParseException;

public class UrgentItem extends Item {
    public UrgentItem(String name, String dueDate) throws ParseException {
        super(name, dueDate);
    }

    UrgentItem(String name, String status, String dueDate) {
        super(name, status, dueDate);
    }

    @Override
    public String getItemType() {
        return "Urgent";
    }

    @Override
    public String toString() {
        return
                name + "   " +
                "Status: " + status + "   " +
                "DueDate: " + dueDate + "  " +
                        "   !!URGENT!!";
    }
}
