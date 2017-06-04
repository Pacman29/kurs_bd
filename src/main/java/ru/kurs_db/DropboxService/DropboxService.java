package ru.kurs_db.DropboxService;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kurs_db.FileStorage.FileStorage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by pacman29 on 20.05.17.
 */

@Service
@PropertySource("classpath:dropbox.properties")
public class DropboxService implements FileStorage,ApplicationRunner{


    @Value("${dropbox.token}")
    private String AccessToken;

    @Value("${dropbox.files_folder}")
    private  String FilesFolder;

    private DbxClientV2 dbxClientV2;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("dropbox", "en_US");
        dbxClientV2 = new DbxClientV2(dbxRequestConfig,this.AccessToken);
    }

    @Override
    public FileMetadata savefile(@NotNull MultipartFile File) throws IOException, DbxException {
        final String date = new SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis());
        final StringBuilder fileName = new StringBuilder();
        final UUID id = UUID.randomUUID();
        fileName.append(date);
        fileName.append("__");
        fileName.append(id);
        fileName.append("__");
        fileName.append(File.getOriginalFilename());
        FileMetadata uploadedFile = dbxClientV2.files().uploadBuilder("/"+fileName).uploadAndFinish(File.getInputStream());
        return uploadedFile;
    }

    @Override
    public String getfilelink(@NotNull String filename) throws DbxException {
        return dbxClientV2.files().getTemporaryLink(filename).getLink();
    }

    @Override
    public FileMetadata getfile(@NotNull String filename) throws IOException, DbxException {
        final File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory() + FilesFolder +"_"+ filename);
        OutputStream outputStream = new FileOutputStream(file);
        FileMetadata data = dbxClientV2.files().downloadBuilder(filename).download(outputStream);
        return data;
    }

    @Override
    public Metadata deletefile(@NotNull String filename) throws DbxException {
        return dbxClientV2.files().delete(filename);
    }
}
