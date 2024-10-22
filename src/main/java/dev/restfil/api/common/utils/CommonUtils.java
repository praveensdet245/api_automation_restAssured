package dev.restfil.api.common.utils;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonUtils {
    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);
    private CommonUtils() {

    }

    public static boolean isFileDownloaded(String fileName) {
        boolean isFileExist = false;
        try{
            Path filePath = Paths.get(Constants.DOWNLOAD_FILE_PATH,fileName);
            File file = filePath.toFile();
            isFileExist = file.exists();
        }catch (Exception e) {
            log.error("Unable to validate whether file exist or not in directory :"+Constants.DOWNLOAD_FILE_PATH,e);
        }
        return isFileExist;
    }
}
