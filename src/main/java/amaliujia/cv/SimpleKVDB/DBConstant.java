package amaliujia.cv.SimpleKVDB;

import java.util.regex.Pattern;

public class DBConstant {
    public static final String DATAFILE_NAME_SUFFIX = ".data";
    public static final String INDEXFILE_NAME_SUFFIX = ".index";

    public static final Pattern DATA_FILE_PATTERN = Pattern.compile("([0-9]+)" + DBConstant.DATAFILE_NAME_SUFFIX + "c?");

    public static final int version = 1;

}
