package io.javabytes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PayeeFacadeTest {
    PayeeFacade payeeFacade;
    @Before
    public void setUp() throws Exception {
        payeeFacade = new PayeeFacade();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get_PayeeBy_Id_success() throws PayeeNotFoundException {
        Payee payee = payeeFacade.getPayeeById("1");
        // Junit Assertions
        assertNotNull(payee);
        assertEquals("1",payee.getId());
        assertEquals("payee1",payee.getName());
        assertEquals("Managed",payee.getType());
        assertEquals("1234561",payee.getAccountNumber());
    }

    @Test
    public void get_PayeeBy_Id_failure() throws PayeeNotFoundException {
        Payee payee = payeeFacade.getPayeeById("1");

        assertNotNull(payee);
        assertEquals("1",payee.getId());
        assertEquals("payee1",payee.getName());
        assertEquals("Manage",payee.getType());
        assertEquals("123456",payee.getAccountNumber());

    }

    @Test(expected = PayeeNotFoundException.class)
    public void get_PayeeBy_Id_exception() throws PayeeNotFoundException {
        payeeFacade.getPayeeById("12");
    }

    @Test(expected = UnSupportedPayeeType.class)
    public void get_Payees_By_Type_exception() {
        payeeFacade.getPayeesByType("Popular");
    }


    @Test
    public void getPayeesByType() {
    }

    private Payee expectedPayee(){
        Payee payee = new Payee();
        String id = String.valueOf(1);
        payee.setId(id);
        payee.setName("payee" + id);
        payee.setAccountNumber("123456" + id);
        payee.setType("Managed");
        return payee;
    }

    private Payee unExpectedPayee(){
        Payee payee = new Payee();
        String id = String.valueOf(1);
        payee.setId(id);
        payee.setName("payee");
        payee.setAccountNumber("123456");
        payee.setType("Managed");
        return payee;
    }
}