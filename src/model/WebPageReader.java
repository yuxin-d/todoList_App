package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class WebPageReader {

    private static WebPageReader instance;

    public static WebPageReader getInstance(){
        if (instance == null){
            instance = new WebPageReader();
        }
        return instance;
    }

    private WebPageReader(){
    }

    public void greetingMessage() throws IOException{

        BufferedReader br = null;

        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}