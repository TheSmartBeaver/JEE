package myapp.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myapp.services.ICalculator;
import myapp.services.ILogger;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/config.xml")
public class TestSpringJUnitIntegration {

    @Autowired
    ILogger logger;

    @Autowired
    ICalculator calc;

    @BeforeEach
    public void beforeEachTest() {
        System.err.println("====================");
    }

    void use(ILogger logger) {
        logger.log("Voila le résultat");
    }

    void use(ICalculator calc) {
        assertEquals(300, calc.add(100, 200));
    }

    @Test
    public void testStderrLogger() {
        System.err.println("+++ StderrLogger");
        use(logger);
    }

    @Test
    public void testCalculatorWithLogger() {
        use(calc);
    }

}