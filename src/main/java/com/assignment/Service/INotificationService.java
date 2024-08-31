package com.assignment.Service;

public interface INotificationService {

    /**
     * Sends a notification email to a  recipient.
     *
     * @param message The content of the email to be sent.
     */
    void sendNotification(String message,String email);

}
