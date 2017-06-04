package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Word;

import java.util.ArrayList;

/**
 * Created by pacman29 on 20.05.17.
 */
public interface WordsDAO {
    Word create(final String word,
                final String slang,
                final String dialect,
                final Integer file_id,
                final String discription);

    Word change(final Integer word_id,
                final String new_slang,
                final String new_dialect,
                final String new_discription);

    Word delete(final Integer id);
    ArrayList<Word> getAllWords(final Integer limit_s, final Integer limit_f);
    Word get(@NotNull final Integer id);
    ArrayList<Word> getword(@NotNull final String word);
    ArrayList<Word> getword(@NotNull final String word,
                            final String slang,
                            final String dialect);

    ArrayList<Word> search (String word, String dialect, String slang);
}
