package ru.kpfu.itis.hauss.helpers.processors;

import java.nio.file.Paths;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.PHOTO_DIRECTORY;

public class UploadFilesLinkProcessor {

    public static String getLinkByPhotoName(String photoName) {
        String photoLink = null;
        if (photoName != null) {
            photoLink = Paths.get(PHOTO_DIRECTORY, photoName).toString();
        }
        return photoLink;
    }
}
