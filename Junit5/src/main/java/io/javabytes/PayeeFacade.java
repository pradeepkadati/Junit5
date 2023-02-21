package io.javabytes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PayeeFacade {

    Map<String, Payee> payees;

    public PayeeFacade() {
        payees = new HashMap<>();

        buildPayees(payees);
    }

    private void buildPayees(Map<String, Payee> payees) {

        for (int i = 1; i <=10; i++) {
            Payee payee = new Payee();
            String id = String.valueOf(i);
            payee.setId(id);
            payee.setName("payee" + id);
            payee.setAccountNumber("123456" + id);

            if (i % 2 == 0) {
                payee.setType("Private");
            } else {
                payee.setType("Managed");
            }

            payees.put(id, payee);
        }
    }

    public Payee getPayeeById(String id) throws PayeeNotFoundException {

        Payee payee = payees.get(id);

        if (payee == null){
            throw new PayeeNotFoundException("Payee Not found for id "+id);
        }
        return payee;
    }

    public List<Payee> getPayees() {
        return payees.values().stream().collect(Collectors.toList());
    }

    public List<Payee> getPayeesByType(String type) {
        if (type == null || !Set.of("Private","Managed").contains(type)){
            throw new UnSupportedPayeeType(type + " is a unsupported payee type");
        }
        return payees.values().stream().filter(payee -> payee.getType().equals(type)).collect(Collectors.toList());
    }
}
