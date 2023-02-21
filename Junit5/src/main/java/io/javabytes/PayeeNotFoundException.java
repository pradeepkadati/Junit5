package io.javabytes;

public class PayeeNotFoundException extends Exception{

    public  PayeeNotFoundException(){
        super("Payee Not Found");
    }

    public PayeeNotFoundException(String message){
        super(message);

    }

}
