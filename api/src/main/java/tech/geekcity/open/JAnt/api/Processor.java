package tech.geekcity.open.JAnt.api;

import java.io.Closeable;
import java.io.IOException;

public interface Processor extends Closeable {
    // inject data with constructor
    // invoke setup to do some initialization
    void setup();

    // invoke close to release resources
    void process();

    @Override
    default void close() throws IOException {
    }
}
