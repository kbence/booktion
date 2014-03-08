package com.booktion.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by bnc on 3/7/14.
 */
public class StringOutputStream extends OutputStream
{
    private ByteArrayOutputStream stream;

    public StringOutputStream()
    {
        stream = new ByteArrayOutputStream();
    }

    @Override
    public void write(int b) throws IOException
    {
        stream.write(b);
    }

    @Override
    public String toString()
    {
        return new String(stream.toByteArray());
    }
}
