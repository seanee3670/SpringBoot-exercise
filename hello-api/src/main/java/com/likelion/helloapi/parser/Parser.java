package com.likelion.helloapi.parser;

public interface Parser<T> {
    T parse(String str);
}
