package ru.kurs_db.JdbcDAO.Models;

/**
 * Created by Pacman29 on 11.06.2017.
 */
public class WordWithURL {
    private Integer id;
    private String word;
    private String slang;
    private String dialect;
    private String url;
    private String description;

    public WordWithURL(Integer id,
                String word,
                String slang,
                String dialect,
                String url,
                String description) {
        this.id = id;
        this.word = word;
        this.slang = slang;
        this.dialect = dialect;
        this.url = url;
        this.description = description;
    }

    public WordWithURL(Word word, String url){
        this.id = word.getId();
        this.word = word.getWord();
        this.slang = word.getSlang();
        this.dialect = word.getDialect();
        this.url = url;
        this.description = word.getdescription();
    }

    public Integer getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getSlang() {
        return slang;
    }

    public String getDialect() {
        return dialect;
    }

    public String getdescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
