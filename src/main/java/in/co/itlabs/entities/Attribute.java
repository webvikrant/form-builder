package in.co.itlabs.entities;

import java.util.List;

public class Attribute {
    public enum Name{
        LABEL, PLACEHOLDER, VALUE
    }

    private Name name;
    private Class clazz;
    private boolean singleValued;
    private Object value;
    private List<Object> values;

    public Name getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }

    public boolean isSingleValued() {
        return singleValued;
    }

    public Object getValue() {
        return value;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setSingleValued(boolean singleValued) {
        this.singleValued = singleValued;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
