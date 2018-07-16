package amaliujia.cv.SimpleKVDB;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryHashMap<T> implements IndexHashTable<T> {
    private Map<Long, T> metadata;

    public InMemoryHashMap() {
        metadata = new ConcurrentHashMap<>();
    }

    @Override
    public boolean put(byte[] key, T value) {
        return putIfAbsentInternal(key, value, false);
    }

    @Override
    public boolean putIfAbsent(byte[] key, T value) {
        return putIfAbsentInternal(key, value, true);
    }

    private boolean putIfAbsentInternal(byte[] key, T value, boolean ifAbsent) {
        long hash = java.util.Arrays.hashCode(key);
        if (!ifAbsent || (ifAbsent && !metadata.containsKey(hash))) {
            metadata.put(hash, value);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T get(byte[] key) {
        
        return null;
    }
}
