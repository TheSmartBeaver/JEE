package springapp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductCode {

	@NotNull
    @Size(min = 1, max = 1)
    @Pattern(regexp="[A-Z]", message="Le code doit débuter par une majuscule")
    String base;
    
    @Min(value=1000, message="Le numéro doit être >= à 1000")
    @Max(value=9999, message="Le numéro doit être <= à 9999")
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