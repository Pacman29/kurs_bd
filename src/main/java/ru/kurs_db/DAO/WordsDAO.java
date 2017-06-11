package ru.kurs_db.DAO;

import org.jetbrains.annotations.NotNull;
import ru.kurs_db.JdbcDAO.Models.Word;

import java.util.List;

/**
 * Created by pacman29 on 20.05.17.
 */
public interface WordsDAO {
    Word create(final String word, final String slang, final String dialect, final Integer file_id, final String description);
    Word change(final Integer word_id, final String new_slang, final String new_dialect, final String new_description);
    Word delete(final Integer id);
    List<Word> getAllWords();
    Word get(@NotNull final Integer id);
    List<Word> getword(@NotNull final String word);
    List<Word> getword(@NotNull final String word, final String slang, final String dialect);
    List<Word> search(String word, String dialect, String slang);
}
