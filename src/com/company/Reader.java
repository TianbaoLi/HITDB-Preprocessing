package com.company;

import javax.swing.*;
import java.io.File;

/**
 * Created by TuringMac on 2016/12/28.
 */
public abstract class Reader {
    protected File file;
    protected JTable displayTable;

    public Reader(File file, JTable displayTable) {
        this.file = file;
        this.displayTable = displayTable;
    }

    abstract void read() throws Exception;
}
