package controller;

import java.io.IOException;

public interface EmailSender {
    void sendEmail(String function, String field, String newValue, String toAddress) throws IOException;
}

