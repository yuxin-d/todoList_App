package utility;

import jdk.nashorn.internal.parser.JSONParser;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.Parser;
import parsers.TaskParser;
import persistence.Jsonifier;

import java.io.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

// File input/output operations
public class JsonFileIO {
    public static final File jsonDataFile = new File("./resources/json/tasks.json");

    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Task> read() throws IOException {
        try {
            Files.readAllBytes(jsonDataFile.toPath());
        } catch (FileNotFoundException e) {
            System.out.println("THE FILE NOT FOUND");
        }
        TaskParser taskParser = new TaskParser();
        String inputValue = new String(Files.readAllBytes(jsonDataFile.toPath()));
        List<Task> returnList = taskParser.parse(inputValue);
        return returnList;
    }



    // EFFECTS: saves the tasks to jsonDataFile
    public static void write(List<Task> tasks) throws IOException {
        FileWriter jsonFWriter = new FileWriter(jsonDataFile.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(jsonFWriter);
        JSONArray taskList = Jsonifier.taskListToJson(tasks);
        bufferedWriter.write(taskList.toString());
        bufferedWriter.close();
    }
}
