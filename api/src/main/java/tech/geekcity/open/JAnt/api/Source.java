package tech.geekcity.open.JAnt.api;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface Source<DataType> extends Iterable<List<DataType>>, Closeable {
    void setup(Context context) throws Exception;

    List<String> fieldNameList();

    @Override
    default void close() throws IOException {

    }
}
