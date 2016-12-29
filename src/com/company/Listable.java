package com.company;

import java.util.ArrayList;

/**
 * Created by TuringMac on 2016/12/28.
 */
public interface Listable {
    public static final int itemAmount = 200;

    public void schemaList(int rowRange, int columnRange, String[] list);

    public void tableList(int row, ArrayList<Object> list);
}
