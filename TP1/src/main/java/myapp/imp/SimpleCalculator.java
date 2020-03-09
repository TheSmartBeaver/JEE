package myapp.imp;

import myapp.services.ICalculator;
import myapp.services.ILogger;

public class SimpleCalculator implements ICalculator {

    private ILogger logger;

    public void start() {
        if (logger == null) {
            throw new IllegalStateException("null logger");
        }
        System.err.println("Start " + this);
    }

    public void stop() {
        System.err.println("Stop " + this);
    }

    public int add(int a, int b) {
        logger.log(String.format("add(%d,%d)", a, b));
        return (a + b);
    }

    public ILogger getLogger() {
        return logger;
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }

}
