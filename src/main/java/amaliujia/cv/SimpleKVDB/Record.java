package amaliujia.cv.SimpleKVDB;

public class Record {
    // Key and value are final because this is a append only log structured store.
    // Modification on the single record should not happen.
    private final byte[] key;
    private final byte[] value;

    public Record(byte[] key, byte[] value) {
        this.key = key;
        this.value = value;
    }


    private class RecordHeader {
        /**
         * crc              - 4 bytes.
         * version          - 1 byte.
         * key size         - 1 bytes.
         * value size       - 4 bytes.
         * sequence number  - 8 bytes.
         */
        public static final int CHECKSUM_OFFSET = 0;
        public static final int VERSION_OFFSET = 4;
        public static final int KEY_SIZE_OFFSET = 5;
        public static final int VALUE_SIZE_OFFSET = 6;
        public static final int SEQUENCE_NUMBER_OFFSET = 10;
        public static final int HEADER_SIZE = 18;
        public static final int CHECKSUM_SIZE = 4;


        private int crc;
        private int version;
        private int keySize;
        private int valueSize;
        private long sequenceNum;
    }
}
