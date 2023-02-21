package io.javabytes;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PayeeFacadeTest {

    PayeeFacade payeeFacade;

    @BeforeAll
    static void initAll() {
        System.out.println("Test Case Execution Started For Payee Facade");
    }

    @AfterAll
    static void tearDownAll() {

        System.out.println("Test Case Execution Completed For Payee Facade");
    }
    @BeforeEach
    void setUp() {
        payeeFacade = new PayeeFacade();
    }

    @AfterEach
    void tearDown() {
        payeeFacade = null;
    }

    @Test
    void get_PayeeBy_Id_success() throws PayeeNotFoundException {
       Payee payee = payeeFacade.getPayeeById("1");
        // Junit Assertions
       assertNotNull(payee);
       assertEquals("1",payee.getId());
       assertEquals("payee1",payee.getName());
       assertEquals("Managed",payee.getType());
       assertEquals("1234561",payee.getAccountNumber());
    }

    @Test
    void get_PayeeBy_Id_failure() throws PayeeNotFoundException {
        Payee payee = payeeFacade.getPayeeById("1");

        assertNotNull(payee);
        assertEquals("1",payee.getId());
        assertEquals("payee1",payee.getName());
        assertEquals("Manage",payee.getType());
        assertEquals("123456",payee.getAccountNumber());

    }
    @Test
    void get_PayeeBy_Id_success_assertall() throws PayeeNotFoundException {
        Payee payee = payeeFacade.getPayeeById("1");
        Payee expectedPayee = expectedPayee();
        // Junit Assertions
        assertAll("Payee",
                ()->assertNotNull(payee),
                () -> assertEquals(expectedPayee.getId(),payee.getId()),
                () -> assertEquals(expectedPayee.getName(),payee.getName()),
                () -> assertEquals(expectedPayee.getType(),payee.getType()),
                () -> assertEquals(expectedPayee.getAccountNumber(),payee.getAccountNumber()));

        //AssertJ Assertions
        org.assertj.core.api.Assertions.assertThat(payee);

        org.assertj.core.api.Assertions.assertThat(payee).usingRecursiveComparison().isEqualTo(expectedPayee);

    }

    @Test
    void get_PayeeBy_Id_failure_assertall() throws PayeeNotFoundException {
        Payee payee = payeeFacade.getPayeeById("1");
        Payee unExpectedPayee = unExpectedPayee();
        // Junit Assertions
        assertAll("Payee",
                ()->assertNotNull(payee),
                () -> assertEquals(unExpectedPayee.getId(),payee.getId()),
                () -> assertEquals(unExpectedPayee.getName(),payee.getName()),
                () -> assertEquals(unExpectedPayee.getType(),payee.getType()),
                () -> assertEquals(unExpectedPayee.getAccountNumber(),payee.getAccountNumber()));
        //AssertJ Assertions
        org.assertj.core.api.Assertions.assertThat(payee);

        org.assertj.core.api.Assertions.assertThat(payee).usingRecursiveComparison().isEqualTo(unExpectedPayee);

    }

    @Test
    void get_PayeeBy_Id_failure_assertall_assertJ() throws PayeeNotFoundException {
        Payee payee = payeeFacade.getPayeeById("1");
        Payee unExpectedPayee = unExpectedPayee();

        //AssertJ Assertions
        org.assertj.core.api.Assertions.assertThat(payee);
        org.assertj.core.api.Assertions.assertThat(payee).usingRecursiveComparison().isEqualTo(unExpectedPayee);

    }


    @Test
    void get_PayeeBy_Id_exception() throws PayeeNotFoundException {

        PayeeNotFoundException exception = assertThrows(PayeeNotFoundException.class,  () ->payeeFacade.getPayeeById("12"));
        assertEquals("Payee Not found for id 12", exception.getMessage());

         //assertj exception
        org.assertj.core.api.Assertions.assertThatExceptionOfType(PayeeNotFoundException.class)
                .isThrownBy(()->payeeFacade.getPayeeById("12"))
                .withMessage("Payee Not found for id 12");

        org.assertj.core.api.Assertions.assertThatThrownBy(()->payeeFacade.getPayeeById("12"))
                .isInstanceOf(PayeeNotFoundException.class)
                .hasMessageContaining("Payee Not found for id 12")
                .hasCauseInstanceOf(PayeeNotFoundException.class);


    }

    @Test
    void get_Payees_success() {
    }

    @Test
    void get_Payees_no_payees() {
    }

    @Test
    void get_Payees_By_Type_success() {


       List<Payee> privatePayee = payeeFacade.getPayeesByType("Private");

        for (Payee payee:privatePayee) {
            assertEquals("Private",payee.getType());
        }

        org.assertj.core.api.Assertions.assertThatIterable(privatePayee).allMatch(payee ->  "Private".equals(payee.getType()));

    }

    @Test
    void get_Payees_By_Type_exception() {

        org.assertj.core.api.Assertions.assertThatExceptionOfType(UnSupportedPayeeType.class)
                .isThrownBy(()->payeeFacade.getPayeesByType("Popular"))
                .withMessage("Popular is a unsupported payee type");


        org.assertj.core.api.Assertions.assertThatThrownBy(()->payeeFacade.getPayeesByType("Popular"))
                .isInstanceOf(UnSupportedPayeeType.class)
                .hasMessageContaining("Popular is a unsupported payee type");
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