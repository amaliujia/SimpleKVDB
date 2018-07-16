package amaliujia.cv.SimpleKVDB;

import lombok.Getter;

/**
 * checksum         - 4 bytes.
 * version          - 1 byte.
 * Key size         - 1 bytes.
 * record size      - 4 bytes.
 * record offset    - 4 bytes.
 * sequence number  - 8 bytes
 * Total size: 22 bytes
 */
public class IndexEntry {
    public final static int INDEX_ENTRY_HEADER_SIZE = 22;

    public final static int CHECKSUM_SIZE = 4;
    public static final int CHECKSUM_OFFSET = 0;
    public static final int VERSION_OFFSET = 4;
    public static final int KEY_SIZE_OFFSET = 5;
    public static final int RECORD_SIZE_OFFSET = 6;
    public static final int RECORD_OFFSET = 10;
    public static final int SEQUENCE_NUMBER_OFFSET = 14;

    private int checkSum;
    private int version;
    private int keySize;
    private int recordSize;
    private int recordOff;
    private long sequenceNum;

    // Index entry have to save the key to help locate where the data is.
    @Getter
    private byte[] key;

}
