package io.github.jx2lee.automation.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestPostList {
    private static final RequestPostList instance = new RequestPostList();
    public static RequestPostList getInstance() {
        return instance;
    }

    private RequestPostList() {
    }
    public JsonObject getAllWorkByPage(int page) throws IOException {

        String parameter = String.format("page=%s&size=%s&order=%s", page, "100", "-createdAt");
        URL url = new URL("https://api.dooray.com/project/v1/projects/2895800239631697029/posts" + "?" + parameter);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Authorization", "dooray-api ajjt1imxmtj4:tQkn78_4TcCt9QvxsoRzLw");
        urlConnection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder response = new StringBuilder();

        while((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        JsonParser jsonParser = new JsonParser();

        return (JsonObject) jsonParser.parse(String.valueOf(response));
    }

    public JsonObject getWorkDetail(String postId) throws IOException {

        URL url = new URL("https://api.dooray.com/project/v1/projects/2895800239631697029/posts/" + postId);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Authorization", "dooray-api ajjt1imxmtj4:tQkn78_4TcCt9QvxsoRzLw");
        urlConnection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder response = new StringBuilder();

        while((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        JsonParser jsonParser = new JsonParser();

        return (JsonObject) jsonParser.parse(String.valueOf(response));
    }
}
