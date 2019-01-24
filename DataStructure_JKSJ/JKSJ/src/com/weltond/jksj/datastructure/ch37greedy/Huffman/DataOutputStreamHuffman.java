package com.weltond.jksj.datastructure.ch37greedy.Huffman;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author weltond
 * @project JKSJ
 * @date 1/24/2019
 */
public class DataOutputStreamHuffman {

    public static final DataOutputStreamHuffman OUTPUT = new DataOutputStreamHuffman();

    public static final String path = "C:\\test";

    public void outToFile(byte[] value) {
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(path + "src.file");
            output.write(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void outHuffmanToFile(byte[] value) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path + "out.huff");
            outputStream.write(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
