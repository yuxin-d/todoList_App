package parsers;

import model.DueDate;
import model.Priority;
import model.Status;
import model.Task;
import model.exceptions.NullArgumentException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Represents Task parser
public class TaskParser {

    // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
    // string and parses it as a task; each parsed task is added to the list of tasks.
    // Any task that cannot be parsed due to malformed JSON data is not added to the
    // list of tasks.
    // Note: input is a string representation of a JSONArray


    public List<Task> parse(String input) {
        JSONArray tasksArray = new JSONArray(input);
        List<Task> taskList = new ArrayList<>();
        for (Object object: tasksArray) {
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                forEachLoop(taskList, jsonObject);
            }

        }
        return taskList;   // stub*/
    }

    private void forEachLoop(List<Task> taskList, JSONObject jsonObject) {
        String description;
        try {
            description = jsonObject.getString("description");
            DueDate dd;
            Task task = new Task(description);
            try {
                dd = parseDueDate(jsonObject.getJSONObject("due-date"));
            } catch (JSONException e) {
                dd = null;
            }
            set(jsonObject, task, dd);
            tags(taskList, jsonObject, task);
            taskList.add(task);
        } catch (JSONException e) {
            //
        }
    }


    private void tags(List<Task> taskList, JSONObject jsonObject, Task task) {
        JSONArray tagsArr = jsonObject.getJSONArray("tags");
        for (Object tagobj: tagsArr) {
            if (tagobj instanceof JSONObject) {
                JSONObject tagjobj = (JSONObject)tagobj;
                task.addTag(tagjobj.getString("name"));
            }
        }
    }

    private void set(JSONObject jsonObject, Task task, DueDate dd) {

        task.setDueDate(dd);
        Priority p = parsePriority(jsonObject.getJSONObject("priority"));
        task.setPriority(p);
        Status s = null;

        s = parseStatus(jsonObject.getString("status").toUpperCase());

        task.setStatus(s);
    }


    public Priority parsePriority(JSONObject o) {
        Priority p = new Priority();
        p.setImportant(o.getBoolean("important"));
        p.setUrgent(o.getBoolean("urgent"));
        return p;
    }

    public DueDate parseDueDate(JSONObject o) {
        if (o == JSONObject.NULL) {
            return null;
        }
        DueDate dd = new DueDate();
        if (o == null) {
            dd.setDueDate(null);
        } else {
            Calendar cal = Calendar.getInstance();
            int year = o.getInt("year");
            int hour = o.getInt("hour");
            int minute = o.getInt("minute");
            int day = o.getInt("day");
            int month = o.getInt("month");
            cal.set(year, month, day, hour, minute);
            dd.setDueDate(cal.getTime());
        }
        return dd;
    }

    public Status parseStatus(String s) {
        Status status = Status.valueOf(s);
        return status;
    }


}