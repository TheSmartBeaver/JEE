package springapp.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import springapp.web.Bye;

public class Product {

	private Integer number;

    @NotNull
    @Size(min = 1, message = "Le nom est obligatoire")
    private String name;

    @NotNull
    @Min(value = 1, message = "Le prix est trop bas")
    private Double price;

    @NotNull(message = "La description est obligatoire")
    @Size(min = 1, max = 100, message = "Entre 1 et 200 caractères")
    @Bye
    private String description;

    @NotNull()
    @Size(min=1,message="Le type doit être renseigné")
    private String type;

    @Valid
    private ProductCode code;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public ProductCode getCode() {
        return code;
    }

    public void setCode(ProductCode code) {
        this.code = code;
    }

}