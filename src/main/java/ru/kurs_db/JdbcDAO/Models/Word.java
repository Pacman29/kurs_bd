package ru.kurs_db.JdbcDAO.Models;


/**
 * Created by pacman29 on 20.05.17.
 */
public class Word {
    private Integer id;
    private String word;
    private String slang;
    private String dialect;
    private String file_name;
    private String discription;

    public Word(Integer id,
                String word,
                String slang,
                String dialect,
                String file_name,
                String discription) {
        this.id = id;
        this.word = word;
        this.slang = slang;
        this.dialect = dialect;
        this.file_name = file_name;
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

    public String getFile_name() {
        return file_name;
    }
}
