package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UserSystem implements Loadable, Saveable{
   private Map<User, Password> userInfo;

   public UserSystem (){
       userInfo = new HashMap<>();
       defaultUser();
   }

    // EFFECTS: check if user have access to the todolist, return false if access denied
    public boolean accessVerification(int passWordEntered, String nameEntered){
        Password pwEntered = new Password(passWordEntered);
        User userEntered = new User(nameEntered, pwEntered);
        Set<User> users = userInfo.keySet();
        for(User u: users){
            if (u.equals(userEntered)){
                return true;
            }
        }
        System.out.println("Access denied: incorrect password or invalid username");
        return false;
    }

    // MODIFIES: User
    // EFFECTS: set user password to passwordentered
    public void resetPasswords(User u, int passwordentered) {
        Password pw  = new Password(passwordentered);
        userInfo.remove(u);
        u.setPasswords(pw);
        userInfo.put(u,pw);
    }


    // MODIFIES: this
    // EFFECTS: add a new user to user system
    public void addUser(User u){
        userInfo.put(u, u.getPasswords());
    }

    public void addUser(String userName, int password){
        Password passwordEntered = new Password(password);
        User user = new User(userName, passwordEntered);
        userInfo.put(user, user.getPasswords());
    }


    public Map<User, Password> getUserInfo() {
        return userInfo;
    }


    // MODIFIES: this
    // EFFECTS: add a default user admin to the user system
    private void defaultUser(){
        User admin = new User("admin");
        addUser(admin);
    }

    @Override
    public void save(String fileName) throws IOException {
        List<String> newLines = new ArrayList<>();
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        Set<Map.Entry<User, Password>> entries = userInfo.entrySet();
        for (Map.Entry<User, Password> entry : entries) {
            newLines.add(entry.getKey().getUserName() + " : " + Integer.toString(entry.getValue().getPw()));
        }
        for (String line : newLines){
            writer.println(line);
        }
        writer.close();
    }

    @Override
    public void load(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String line: lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Password password = new Password(Integer.parseInt(partsOfLine.get(1)));
            User user = new User(partsOfLine.get(0), password);
            userInfo.put(user, password);
        }
    }

    // REQUIRES: input file name to exist
    // MODIFIES: this
    // EFFECTS: empty input file
    @Override
    public void emptyFile(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        writer.print("");
        writer.close();
    }

    // EFFECTS: split ArraryList<String>
    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" : ");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
