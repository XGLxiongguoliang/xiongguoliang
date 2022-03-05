package com.example.network.temp.designpattern.behavior.iterator;

public interface Aggregate {
    public void add(Object object);
    public void remove(Object object);
    public Iterator getIterator();
}
