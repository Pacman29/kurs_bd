package ru.kurs_db.FileStorage;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by pacman29 on 20.05.17.
 */
public interface FileStorage {
    FileMetadata savefile(@NotNull MultipartFile File) throws IOException, DbxException;
    String  getfilelink(@NotNull String filename) throws DbxException;
    FileMetadata getfile(@NotNull String filename) throws IOException, DbxException;
    Metadata deletefile(@NotNull String filename) throws DbxException;
}
