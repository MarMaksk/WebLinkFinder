package com.logicbig.example.service;

import com.logicbig.example.entity.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class LinkService {

    public boolean isLink(String link) {
        // URL не содержит пробелов
        String[] parts = link.split("\\s+");

        // Проверка каждой части строки
        for (String item : parts)
            try {
                URL url = new URL(item);
            } catch (MalformedURLException e) {
                // Если это не URL
                System.out.print(item + " ");
                return false;
            }

        return true;
    }

    // Метод для нахождения ссылок и приведения их к годному к использованию виду
    public List<Link> findLinks(String url) throws IOException {
        List<Link> links = new ArrayList<>();
        Document doc = null;
        doc = Jsoup.connect(url).get();
        Elements elements = doc.select("a[href]");
        for (Element element : elements) {
            String name = element.text();
            String link = element.attr("href");
            // С целью оставить только ссылки
            if (!link.contains("http"))
                continue;
                // У некоторых ссылок нет названия
            else if (name.trim().isEmpty())
                name = "Отсутвует название или гиперссылка по изображению";
            links.add(new Link(name, link));
        }
        // Для добавления каждой ссылке своего id
        AtomicInteger counter = new AtomicInteger(0);
        return links.stream()
                // Избавление от повторов
                .distinct()
                .peek(x -> x.setId(counter.incrementAndGet()))
                .collect(Collectors.toList());
    }

}
