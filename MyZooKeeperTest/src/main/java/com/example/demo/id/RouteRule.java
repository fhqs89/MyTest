package com.example.demo.id;

public interface RouteRule<T> {

    int getDbIndex(T routeFactor);

    int getTableIndex(T routeFactor);
}
