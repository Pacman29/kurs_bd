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
    private String file_name;
    private String description;

    public Word(Integer id,
                String word,
                String slang,
                String dialect,
                String file_name,
                String description,
                Integer file_id) {
        this.id = id;
        this.word = word;
        this.slang = slang;
        this.dialect = dialect;
        this.file_name = file_name;
        this.description = description;
        this.file_id = file_id;
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

    public String getFile_name() {
        return file_name;
    }

    public Integer getFile_id() {
        return file_id;
    }
}
