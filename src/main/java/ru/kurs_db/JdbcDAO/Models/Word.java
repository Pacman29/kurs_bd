package ru.kurs_db.JdbcDAO.Models;


/**
 * Created by pacman29 on 20.05.17.
 */
public class Word {
    private Integer id;
    private String word;
    private String slang;
    private String dialect;
    private Integer file_id;
    private String discription;

    public Word(Integer id,
                String word,
                String slang,
                String dialect,
                Integer file_id,
                String discription) {
        this.id = id;
        this.word = word;
        this.slang = slang;
        this.dialect = dialect;
        this.file_id = file_id;
        this.discription = discription;
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

    public Integer getFile_id() {
        return file_id;
    }
}
