package pl.lodz.p.it.naspontanaapp.domain;

public class SimpleType<T> {

    private T value;

    public SimpleType(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
