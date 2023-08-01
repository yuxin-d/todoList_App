package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Item {
    protected String name;
    protected String status;
    protected String dueDate;
    protected String type;
    protected Date due;

    Item(String name, String dueDate) throws ParseException {
        this.name = name;
        this.dueDate = dueDate;
        status = "In-progress";
        this.type = getItemType();
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        due = format.parse(getDueDate());
    }

    Item(String name, String status, String dueDate){
        this.name = name;
        this.status = status;
        this.dueDate = dueDate;
        this.type = getItemType();
    }

    // EFFECTS: return the name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: return dueDate of the item
    public String getDueDate() {
        return dueDate;
    }

    // EFFECTS: return status of the item
    public String getStatus() {
        return status;
    }


    // MODIFIES: this
    // EFFECTS: change the name of the item
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: change the status of the item
    public void setStatus(String status) {
        this.status = status;
    }

    // MODIFIES: this
    // EFFECTS: change the status of the item
    public void setDueDate(String dueDate) throws ParseException {
        new RegularItem(getName(),dueDate);
        this.dueDate = dueDate;
    }

    public Date getDue(){
        return due;
    }

    // EFFECTS: return item type as string
    public abstract String getItemType();

}
