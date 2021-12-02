package com.jx2lee.sfo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jx2lee.sfo.request.RequestPostList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class SendMonthCompletedWorkService {
//real    @Scheduled(cron = "0 0 * * * *")
//test    @Scheduled(cron = "0 * * * * *")
//     @Scheduled(cron = "0 0 * * * *")
    public void AlertDoneWork() {
        RequestPostList requestPostList = RequestPostList.getInstance();
        JsonObject jsonObject = null;
        try {
            jsonObject = requestPostList.getAllWorkByPage(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int totalCount = jsonObject.get("totalCount").getAsInt();
        int maxPage = totalCount / 100;

        Map<String, String> MonthlyWorkList = GetDoneWorkList(maxPage, requestPostList);
        for (Map.Entry<String, String>work:MonthlyWorkList.entrySet()) {
            log.info(work.getKey() + " :\t" + work.getValue());
        }
        log.info("MonthlyWorkList.size() = " + MonthlyWorkList.size());
    }

    public static LinkedHashMap<String, String> GetDoneWorkList(int maxPage, RequestPostList requestPostList) {

        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat currentParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateParser.setTimeZone(TimeZone.getTimeZone("KST"));

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateParser.parse(dateParser.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.MONTH, -1);
        Date currentDate = cal.getTime();
        log.info("due-date: " + currentParser.format(currentDate));

        LinkedHashMap MonthlyWorks = new LinkedHashMap();

        Loop1:
        for (int i = 0; i < maxPage + 1; i++) {
            JsonObject pageWork = new JsonObject();
            try {
                pageWork = requestPostList.getAllWorkByPage(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonArray pageArray = (JsonArray) pageWork.get("result");
            for (int j = 0; j < pageArray.size(); j++) {
                JsonObject task = (JsonObject) pageArray.get(j);
                Date parseDate;
                try {
                    parseDate = dateParser.parse(task.get("createdAt").toString().replaceAll("\"", ""));
                    if (!parseDate.after(currentDate)) {
                        break Loop1;
                    } else {
                        if (task.get("workflowClass").toString().replaceAll("\"", "").equals("closed")) {
                            String subject = task.get("subject").toString().replaceAll("\"", "");
                            MonthlyWorks.put(currentParser.format(parseDate), subject);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return MonthlyWorks;
    }
}
