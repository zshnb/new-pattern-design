package com.zshnb.patterndesign.decorator;

import java.util.ArrayList;
import java.util.List;

public class ArrayListUti<T> {
    private List<T> data;

    public ArrayListUti() {
        data = new ArrayList<>();
    }

    public void add(T t) {
        data.add(t);
    }

    public T get(int index) {
        return data.get(index);
    }

    public T remove(int index) {
        return data.remove(index);
    }

    public int size() {
        return data.size();
    }
}
