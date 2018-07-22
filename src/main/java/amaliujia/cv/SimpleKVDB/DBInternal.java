package amaliujia.cv.SimpleKVDB;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
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
    private AtomicLong fileID;

    // TODO: why it is volatile keyworded.
    private volatile DBDataFile currentWriteFile;

    /**
     * The process of initializing a DB internal:
     * 1. Check if DBOptions is illegal.
     * 2. Rebuild index mapping from index file.
     * 3. Create a current file writer (the tail of log).
     * 4. Kick off background compaction thread.
     * @param directory
     * @param options
     */
    public DBInternal(File directory, DBOptions options) {
        this.directory = directory;
        this.options = options;
        // TODO: check options.

        // TODO: rebuid index.

        // TODO: initialize fileID. Make sure the starting number is the largest number used so far.

        // TODO: open a current file writer.

        // TODO: start offline compaction.

    }

    // TODO: Does this function need to be thread safe?
    private long getNextSequenceNumber() {
        return System.nanoTime();
    }


    public void put(byte[] key, byte[] val) {
        Record record = new Record(key, val, getNextSequenceNumber());
        writeRecord(record);

        // TODO: update index.
    }

    private void writeRecord(Record record) {
        rolloverCurrentFileWriter(record.size());
        // TODO: write to file
    }

    private DBDataFile createDBDataFile() {
        return null;
    }

    private void rolloverCurrentFileWriter(long recordSize) {
        if (currentWriteFile == null) {
            currentWriteFile = createDBDataFile();
        } else if (currentWriteFile.getCurrentFileSize() + recordSize > options.getMaxDataFileSize()) {
            currentWriteFile.close();
            currentWriteFile = createDBDataFile();
        }
    }
}
