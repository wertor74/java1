package ru.progwards.java1.lessons.datetime;

import java.time.LocalTime;
import java.util.ArrayList;

public class SessionManager {
    private ArrayList<UserSession> sessions;
    private int sessionValid;
    public SessionManager(int sessionValid) {
        this.sessions = new ArrayList<>();
        this.sessionValid = sessionValid;
    }
    public void add(UserSession userSession) {
        sessions.add(userSession);
    }
    public UserSession find(String userName) {
        for (UserSession us : sessions) {
            if (us.getUserName().equals(userName)) {
                if (us.getLastAccess().plusSeconds((long) sessionValid).isAfter(LocalTime.now())) {
                    us.updateLastAccess();
                    return us;
                }
            }
        }
        return null;
    }
    public UserSession get(int sessionHandle) {
        for (UserSession us : sessions) {
            if (us.getSessionHandle() == sessionHandle) {
                if (us.getLastAccess().plusSeconds((long) sessionValid).isAfter(LocalTime.now())) {
                    us.updateLastAccess();
                    return us;
                }
            }
        }
        return null;
    }
    public void delete(int sessionHandle) {
        for (UserSession us : sessions) {
            if (us.getSessionHandle() == sessionHandle) {
                sessions.remove(us);
                break;
            }
        }
    }
    public void deleteExpired() {
        for (int i = sessions.size() - 1; i >= 0; i--) {
            if (sessions.get(i).getLastAccess().plusSeconds((long) sessionValid).isBefore(LocalTime.now())) sessions.remove(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SessionManager sm = new SessionManager(30);
        System.out.println(sm.find("wertor"));
        UserSession us = new UserSession("wertor");
        sm.add(us);
        System.out.println(sm.get(sm.sessions.get(0).getSessionHandle()));
        System.out.println(sm.get(sm.sessions.get(0).getSessionHandle()));
        System.out.println(sm.get(sm.sessions.get(0).getSessionHandle()));
        Thread.sleep(30001);
        System.out.println(sm.get(sm.sessions.get(0).getSessionHandle()));
        sm.add(new UserSession("wertor_1"));
        Thread.sleep(15000);
        sm.add(new UserSession("wertor_2"));
        Thread.sleep(15000);
        sm.deleteExpired();
        System.out.println(sm.sessions);
        sm.delete(sm.sessions.get(0).getSessionHandle());
        System.out.println(sm.sessions);
    }
}
