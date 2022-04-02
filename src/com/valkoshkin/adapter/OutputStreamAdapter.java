package com.valkoshkin.adapter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class OutputStreamAdapter implements OutputAdapter {

    private final OutputStream stream;

    public OutputStreamAdapter(OutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void write(String[] items) throws IOException {
        try (var writer = new OutputStreamWriter(stream)) {
            for (String item : items) {
                writer.write(item + "\n");
            }
        }
    }
}
