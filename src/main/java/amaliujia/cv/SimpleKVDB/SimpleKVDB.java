package amaliujia.cv.SimpleKVDB;

import java.io.File;

public class SimpleKVDB {
    private DBInternal db;

    public static SimpleKVDB open(File directory, DBOptions options) {
        // TODO: check DB option.
        SimpleKVDB simpleKVDB = new SimpleKVDB();

        simpleKVDB.db = new DBInternal(directory, options);

        return simpleKVDB;
    }

    public static SimpleKVDB open(String directory, DBOptions options) {
        return open(new File(directory), options);
    }

    public byte[] get(byte[] key) {
        return null;
    }

    public void put(byte[] key, byte[] val) {
        db.put(key, val);
    }

    public void delete(byte[] key) throws DBException {
        return;
    }
}
