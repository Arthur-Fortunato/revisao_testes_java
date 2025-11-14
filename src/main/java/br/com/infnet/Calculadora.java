package br.com.infnet;

// TDD -> Test-Driven Development
public class Calculadora {
    public int soma(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if(b == 0) throw new IllegalArgumentException("Divided By Zero");
        return a / b;
    }
}
