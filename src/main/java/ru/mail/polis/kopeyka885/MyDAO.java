package ru.mail.polis.kopeyka885;

import com.google.common.collect.Iterators;
import org.jetbrains.annotations.NotNull;
import ru.mail.polis.DAO;
import ru.mail.polis.Record;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyDAO implements DAO {
    private final SortedMap<ByteBuffer, ByteBuffer> map = new TreeMap<>();

    @NotNull
    @Override
    public Iterator<Record> iterator(@NotNull final ByteBuffer from) {
        return Iterators.transform(
                map.tailMap(from).entrySet().iterator(),
                tmp -> Record.of(Objects.requireNonNull(tmp).getKey(), tmp.getValue()));
    }

    @Override
    public void upsert(@NotNull final ByteBuffer key, @NotNull final ByteBuffer value) {
        map.put(key, value);
    }

    @Override
    public void remove(@NotNull final ByteBuffer key) {
        map.remove(key);
    }

    @Override
    public void close() {
        // empty yet or already
    }
}
