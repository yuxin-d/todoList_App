package model;

import java.io.IOException;

public interface Loadable {
    void load(String fileName) throws IOException;
}
