package com.logicbig.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Link {

    private Integer id;
    private String name;
    private String url;

    public Link(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
