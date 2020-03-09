package myapp.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import myapp.services.ICalculator;
import myapp.services.ILogger;

public class TestSpring {

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
        var conf = "config.xml";

        // create a context and find beans
        try (var ctx = new ClassPathXmlApplicationContext(conf);) {
            var logger = ctx.getBean("stderrLogger", ILogger.class);
            use(logger);
        }
    }

    @Test
    public void testCalculatorWithLogger() {
        System.err.println("+++ CalculatorWithLogger");
        var conf = "config.xml";

        // create a context and find beans
        try (var ctx = new ClassPathXmlApplicationContext(conf);) {
            var calc = ctx.getBean("calculator", ICalculator.class);
            use(calc);
        }
    }

}