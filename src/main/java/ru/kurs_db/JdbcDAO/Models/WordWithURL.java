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
    private String discription;

    public WordWithURL(Integer id,
                String word,
                String slang,
                String dialect,
                String url,
                String discription) {
        this.id = id;
        this.word = word;
        this.slang = slang;
        this.dialect = dialect;
        this.url = url;
        this.discription = discription;
    }

    public WordWithURL(Word word, String url){
        this.id = word.getId();
        this.word = word.getWord();
        this.slang = word.getSlang();
        this.dialect = word.getDialect();
        this.url = url;
        this.discription = word.getDiscription();
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

    public String getDiscription() {
        return discription;
    }

    public String getUrl() {
        return url;
    }
}
