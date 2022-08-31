package com.logicbig.example.controller;

import com.logicbig.example.entity.Link;
import com.logicbig.example.service.LinkService;
import com.logicbig.example.service.NotificationService;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component("linkController")
@SessionScoped
@Getter
@Setter
public class LinkController {

    private String input;
    private List<Link> links;
    private boolean analiseButton = true;
    private NotificationService notification;
    private LinkService linkService;

    @Autowired
    public LinkController(NotificationService notification, LinkService linkService) {
        this.notification = notification;
        this.linkService = linkService;
    }

    public void onBlurEvent() {
        if (linkService.isLink(input)) {
            analiseButton = false;
        } else {
            analiseButton = true;
            notification.warn("Неверная ссылка");
        }
    }

    public void analise() {
        try {
            links = linkService.findLinks(input);
        } catch (IOException e) {
            notification.warn("Ошибка при подключении");
            e.printStackTrace();
        }
        notification.info("Найдено " + links.size() + " ссылок");
    }


    public void clearTable() {
        links = new ArrayList<>();
    }

    public void onLinkClick(String url) {
        input = url;
    }


}