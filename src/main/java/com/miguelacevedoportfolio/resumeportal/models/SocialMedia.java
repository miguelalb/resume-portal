package com.miguelacevedoportfolio.resumeportal.models;

import javax.persistence.*;

@Entity
@Table
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String site;
    private String link;
    private String icon;

    @Override
    public String toString() {
        return "SocialMedia{" +
                "id=" + id +
                ", site='" + site + '\'' +
                ", link='" + link + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
