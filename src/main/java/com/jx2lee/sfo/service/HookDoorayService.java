package com.jx2lee.sfo.service;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class HookDoorayService {
    // @Scheduled(cron = "0 0 * * * *")
    public void testRun() throws IOException {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        log.info(sdf.format(new Date()));
        log.info(RequestPostList().toString());
        SendMessageTest();
    }

    public StringBuffer RequestPostList() throws IOException {
        URL url = new URL("https://api.dooray.com/project/v1/projects/2888709710680057005/posts?size=100&order=-createdAt");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Authorization", "dooray-api ajjt1imxmtj4:tQkn78_4TcCt9QvxsoRzLw");
        urlConnection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
        String line;
        StringBuffer response = new StringBuffer();

        while((line = in.readLine()) != null) {
             response.append(line);
        }
        in.close();
        return response;
    }

    public void SendMessageTest() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        JsonObject requestData = new JsonObject();
        requestData.addProperty("botName", "TechOps 알리미");
        requestData.addProperty("botIconImage", "https://static.dooray.com/static_images/dooray-bot.png");
        requestData.addProperty("text", "안녕하세요\n현재 시각은 " + sdf.format(new Date()) + "입니다.");

        URL url = new URL("https://hook.dooray.com/services/2773930546917574305/2998097594941029786/TGA5LIc5StSkbEPx-JodYw");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(10000);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        urlConnection.setDoOutput(true);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
        outputStreamWriter.write(requestData.toString());
        outputStreamWriter.flush();

        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) {
            log.info("Succeeded Dooray Incoming Hook.");
        } else {
            log.info("failed Dooray Incoming Hook. %d", responseCode);
        }
    }
}
