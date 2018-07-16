package amaliujia.cv.SimpleKVDB;

import java.io.File;

public class DBFileUtils {

    public static File[] listDataFiles(File directory) {
        return directory.listFiles(
                file -> DBConstant.DATA_FILE_PATTERN.matcher(file.getName()).matches());
    }
}
