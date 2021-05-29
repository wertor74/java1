package ru.progwards.java1.lessons.datetime;

import java.time.LocalTime;
import java.util.Random;

public class UserSession {
    private int sessionHandle;
    private String userName;
    private LocalTime lastAccess;
    public UserSession(String userName) {
        this.sessionHandle = new Random().nextInt();
        this.userName = userName;
        this.lastAccess = LocalTime.now();
    }
    public int getSessionHandle() {
        return sessionHandle;
    }
    public String getUserName() {
        return userName;
    }
    public LocalTime getLastAccess() {
        return lastAccess;
    }
    public LocalTime updateLastAccess() {
        lastAccess = LocalTime.now();
        return lastAccess;
    }

    @Override
    public String toString() {
        return "{" +
                "sessionHandle=" + sessionHandle +
                ", userName='" + userName + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }

    public static void main(String[] args) {
        UserSession userSession = new UserSession("wertor");
        System.out.println(userSession.getUserName());
        System.out.println(userSession.getSessionHandle());
        System.out.println(userSession.getLastAccess());
    }
}