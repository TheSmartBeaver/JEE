package myTP.springapp.model;

public class ProductCode {

    String base;
    int number;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ProductCode() {
        super();
    }

    public ProductCode(String base, int number) {
        super();
        this.base = base;
        this.number = number;
    }

}