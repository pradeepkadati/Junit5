package io.javabytes;

public class UnSupportedPayeeType extends RuntimeException {

    public UnSupportedPayeeType() {
        super("UnSupported Payee Type");
    }

    public UnSupportedPayeeType(String message) {
        super(message);
    }
}
