package org.example;
import com.google.gson.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            JsonArray jsonArray = getJsonArray("src/main/resources/CommonEvents.json");
            StringBuilder stringBuilder = new StringBuilder();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/out.json"));
            stringBuilder.append(jsonArray.toString());
            bufferedWriter.write(stringBuilder.toString());
            System.out.println(stringBuilder.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonArray getJsonArray(String filePath){
        JsonArray jsonArray = null;
        try (FileReader reader = new FileReader(filePath)){
            jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }
}
