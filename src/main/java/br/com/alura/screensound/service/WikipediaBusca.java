package br.com.alura.screensound.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikipediaBusca {

    private List<Page> pages;

    public List<Page> getPages() {
        return pages;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Page {
        private String title;
        public String getTitle() {
            return title;
        }
    }
}
