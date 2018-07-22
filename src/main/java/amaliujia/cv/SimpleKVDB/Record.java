package amaliujia.cv.SimpleKVDB;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import lombok.Setter;

public class Record {
    // Key and value are final because this is a append only log structured store.
    // Modification on the single record should not happen.
    private final byte[] key;
    private final byte[] value;
    private RecordHeader header;

    public Record(byte[] key, byte[] value, long sequenceNum) {
        this.key = key;
        this.value = value;

        header = new RecordHeader(0, DBConstant.version, key.length, value.length, sequenceNum);
        long crc = computeChecksum(header.serialize());
        header.setCrc(crc);
    }

    public ByteBuffer[] serializeToByteBuffers() {
        return new ByteBuffer[] {ByteBuffer.wrap(header.serialize()), ByteBuffer.wrap(key), ByteBuffer.wrap(value)};
    }

    public byte[] serialize() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(RecordHeader.CHECKSUM_SIZE + key.length + value.length);
        byteBuffer.put(header.serialize(), 0, RecordHeader.CHECKSUM_SIZE);
        byteBuffer.put(key, RecordHeader.CHECKSUM_SIZE, key.length);
        byteBuffer.put(value, RecordHeader.CHECKSUM_SIZE + key.length, value.length);
        return byteBuffer.array();
    }

    public long size() {
        return RecordHeader.HEADER_SIZE + key.length + value.length;
    }

    private long computeChecksum(byte[] headerBytes) {
        CRC32 crc32 = new CRC32();

        // compute checksum with all but the first header element, key and value.
        crc32.update(headerBytes, RecordHeader.CHECKSUM_OFFSET + RecordHeader.CHECKSUM_SIZE,
            RecordHeader.HEADER_SIZE - RecordHeader.CHECKSUM_SIZE);
        crc32.update(key);
        crc32.update(value);
        return crc32.getValue();
    }

    private class RecordHeader {
        /**
         * crc              - 8 bytes.
         * version          - 1 byte.
         * key size         - 1 bytes.
         * value size       - 4 bytes.
         * sequence number  - 8 bytes.
         */
        public static final int CHECKSUM_OFFSET = 0;
        public static final int CHECKSUM_SIZE = 8;

        public static final int HEADER_SIZE = 22;

        public static final int VERSION_OFFSET = 8;
        public static final int KEY_SIZE_OFFSET = 9;
        public static final int VALUE_SIZE_OFFSET = 10;
        public static final int SEQUENCE_NUMBER_OFFSET = 14;

        // checksum of key and value to detect disk level data corruption.
        @Setter
        private long crc;
        private int version;
        private int keySize;
        private int valueSize;
        private long sequenceNum;

        public RecordHeader(long crc, int version, int keySize, int valueSize, long sequenceNum) {
            this.crc = crc;
            this.version = version;
            this.keySize = keySize;
            this.valueSize = valueSize;
            this.sequenceNum = sequenceNum;
        }

        public byte[] serialize() {
            byte[] bytes = new byte[RecordHeader.HEADER_SIZE];
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

            byteBuffer.putLong(RecordHeader.CHECKSUM_OFFSET, this.crc);
            byteBuffer.put(RecordHeader.VERSION_OFFSET, (byte) this.version);
            byteBuffer.put(RecordHeader.KEY_SIZE_OFFSET, (byte) this.keySize);
            byteBuffer.putInt(RecordHeader.VALUE_SIZE_OFFSET, this.valueSize);
            byteBuffer.putLong(RecordHeader.SEQUENCE_NUMBER_OFFSET, this.sequenceNum);

            return byteBuffer.array();
        }
    }
}
