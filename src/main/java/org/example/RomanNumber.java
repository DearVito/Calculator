package org.example;

public enum RomanNumber {
    X(10),IX(9),VIII(8),VII(7),VI(6),V(5),IV(4),III(3),II(2),I(1);

    int arabicNumber;
    RomanNumber(int arabicNumber) {
        this.arabicNumber = arabicNumber;
    }
}
