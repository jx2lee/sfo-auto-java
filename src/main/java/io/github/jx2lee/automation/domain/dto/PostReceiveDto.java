package io.github.jx2lee.automation.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PostReceiveDto {

    public String webhookType;
    public Tenant tenant;
    public String hookEventType;
    public Integer version;
    public Project project;
    public Source source;
    public Post post;

    public static class Tenant {
        public String id;
    }

    public static class Project {
        public String id;
        public String code;
    }

    public static class Source {
        public String type;
        public Member member;
    }

    public static class Post {
        public Integer number;
        public String subject;
        public String id;
        public Users users;
        public Body body;
        public String updatedAt;
        public boolean duedateFlag;
        public String createdAt;
        public String priority;
        public Parent parent;
    }

    public static class Users {
        public From from;
        public List<To> to;
        public List<String> cc;
        public Me me;
    }

    public static class Me {
        public Member member;
        public String type;
    }

    public static class Member {
        public String organizationMemberId;
        public String lastActionAt;
        public String name;
        public boolean read;
        public String workflowId;
        public Integer idProviderId;
        public String idProviderUserId;
    }

    public static class To {
        public String type;
        public Member member;
    }

    public static class From {
        public String type;
        public Member member;
    }
    public static class Body {
        public String mimeType;
        public String content;
        public boolean emptyContent;
    }

    public static class Parent {
        public String id;
        public Integer number;
        public String subject;
        public String dueDate;
        public boolean dueDateFlag;
        public String workflowClass;
    }
}




