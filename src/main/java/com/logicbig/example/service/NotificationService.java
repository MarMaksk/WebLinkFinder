package com.logicbig.example.service;

import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

@Service
public class NotificationService {

    public void info(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, "Инфо", message);
    }

    public void warn(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, "Внимание", message);
    }

    private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
