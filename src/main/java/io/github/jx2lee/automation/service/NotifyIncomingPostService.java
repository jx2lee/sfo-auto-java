package io.github.jx2lee.automation.service;

import com.google.gson.JsonObject;
import io.github.jx2lee.automation.config.DoorayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class NotifyIncomingPostService {
    @Autowired
    DoorayConfig doorayConfig;

    public boolean isEqualSubject(String subejct) {
        return subejct.contains("[서비스광장] 업무 요청이 등록되었습니다. 검토하여 주십시오.");

    }
    public String searchSubject(String contentBody){
        Pattern startPattern = Pattern.compile("\\[일반\\]");
        Pattern endPattern = Pattern.compile("\\<\\/span\\>");

        contentBody = contentBody.replaceAll("&"+"nbsp;", " ");
        Matcher startMatcher = startPattern.matcher(contentBody);
        String subject = "";
        if (startMatcher.find()) {
            String startSubject = contentBody.substring(startMatcher.start());
            Matcher endMatcher = endPattern.matcher(startSubject);
            if (endMatcher.find()) {
                subject = startSubject.substring(0, endMatcher.start()).trim();
            }
        }

        return subject.replaceAll("\\s{8}", " ");
    }

    public void putSubject(String subject, String postId) {

        String endpoint = doorayConfig.getBaseUrl() + "/" + doorayConfig.getProjectId() + "/posts/" + postId;
        String authorization = "dooray-api " + doorayConfig.getToken();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("subject", subject);

        WebClient webClient = WebClient.create();
        webClient.put()
                .uri(endpoint)
                .header("Authorization", authorization)
                .exchange()
                .block();
    }
}
