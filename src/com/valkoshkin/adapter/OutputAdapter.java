package com.valkoshkin.adapter;

import java.io.IOException;

public interface OutputAdapter {
    void write(String[] items) throws IOException;
}
