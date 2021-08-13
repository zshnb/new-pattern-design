package com.zshnb.patterndesign.decorator;

public class PositiveNumberArrayListUtil<T extends Number> {
    private ArrayListUti<T> arrayListUti;
    public PositiveNumberArrayListUtil(ArrayListUti<T> arrayListUti) {
        this.arrayListUti = arrayListUti;
    }

    public void add(Number number) {
        assert !(number instanceof Integer) || number.intValue() > 0;
        assert !(number instanceof Long) || number.longValue() > 0L;
        assert !(number instanceof Double) || number.doubleValue() > 0.0;

        arrayListUti.add((T) number);
    }

    public Number get(int index) {
        return arrayListUti.get(index);
    }

    public Number remove(int index) {
        return arrayListUti.remove(index);
    }

    public int size() {
        return arrayListUti.size();
    }
}
