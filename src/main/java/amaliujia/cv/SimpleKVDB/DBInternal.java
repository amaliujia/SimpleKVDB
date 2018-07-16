package amaliujia.cv.SimpleKVDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBInternal {
    private static final Logger logger = LoggerFactory.getLogger(DBInternal.class);

    private File directory;
    private DBOptions options;
    private Map<Integer, DBDataFile> dataFileMap = new ConcurrentHashMap<Integer, DBDataFile>();

    /**
     * The process of initializing a DB internal:
     * 1. Check if DBOptions is illegal.
     * 2. Rebuild index mapping from index file.
     * 3. Create a current file write (the tail of log).
     * 4. Kick off background compaction thread.
     * @param directory
     * @param options
     */
    public DBInternal(File directory, DBOptions options) {
        this.directory = directory;
        this.options = options;
        // TODO: check options

        // TODO:

    }

    // TODO: Do this function need to be thread safe?
    private long getNextSequenceNumber() {
        return System.nanoTime();
    }


}
