package org.example;

public class NotificationService {
    public boolean sendNotification(String notification) {
        boolean success = false;
        try {
            System.out.println(notification);
            success = true;
        } catch (Exception e) {
            System.out.println("Exception while sending notification");
        }
        return success;
    }
}
