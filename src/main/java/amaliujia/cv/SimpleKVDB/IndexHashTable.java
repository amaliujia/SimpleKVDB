package amaliujia.cv.SimpleKVDB;

public interface IndexHashTable<T> {
    /**
     * put value given key
     * @param key key in byte array representation.
     * @param value value.
     * @return true if value is put successfully, false otherwise.
     */
    boolean put(byte[] key, T value);

    /**
     * @param key      key of the entry to be added. Must not be {@code null}.
     * @param value    value of the entry to be added. Must not be {@code null}.
     * @return {@code true} on success or {@code false} if the key is already present.
     */
    boolean putIfAbsent(byte[] key, T value);

    /**
     *
     * @param key
     * @return Return value if key is present or null if key is not present.
     */
    T get(byte[] key);

}
