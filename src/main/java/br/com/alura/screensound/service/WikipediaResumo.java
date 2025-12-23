package br.com.alura.screensound.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaResumo {

    private String title;
    private String extract;

    public String getTitle() {
        return title;
    }

    public String getExtract() {
        return extract;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }
}
