package org.example;
import com.google.gson.*;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            JsonArray jsonArray = getJsonArray("S:\\data\\CommonEvents.json");
            List<JsonObject> jsonObject = getArrayJsonObject(jsonArray);
            List<String> stringList = getListParametersList(jsonObject);
            writeFile(stringList, "s:\\1.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(List<String> stringList, String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String value : stringList) {
                writer.write(value);
                writer.newLine();
            }
            System.out.println("Danh sách đã được ghi vào tệp.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào tệp: " + e.getMessage());
        }
    }
    public static List<String> getListParametersList(List<JsonObject> jsonObjects){
        List<JsonObject> jsonListObjects = new ArrayList<>();
        JsonArray jsonArray = new JsonArray();
        JsonArray jsonArray1 = new JsonArray();
        for (JsonObject jsonObject: jsonObjects){
            jsonArray.add(jsonObject.get("list"));
        }

        for (JsonElement element: jsonArray){
            for (JsonElement jsonElements: element.getAsJsonArray()){
                jsonArray1.add(jsonElements.getAsJsonObject().get("parameters"));
            }
        }
        List<JsonPrimitive> jsonPrimitiveList = new ArrayList<>();
        for (JsonElement element: jsonArray1){
            for (JsonElement jsonElement: element.getAsJsonArray()){
                if (jsonElement.isJsonPrimitive())
                    jsonPrimitiveList.add(jsonElement.getAsJsonPrimitive());
            }
        }
        List<String> stringList = new ArrayList<>();
        for (JsonPrimitive jsonPrimitive: jsonPrimitiveList){
            if (jsonPrimitive.isString()){
                stringList.add(jsonPrimitive.getAsString());
            }
        }
        return stringList;
    }
    public static List<JsonObject> getArrayJsonObject(JsonArray jsonArray){
        List<JsonObject> jsonObjects = new ArrayList<>();
        for (JsonElement element: jsonArray){
            if (element.isJsonObject()){
                jsonObjects.add(element.getAsJsonObject());
            }
        }
        return jsonObjects;
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