package mybootapp.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.validation.Valid;
import mybootapp.model.Product;

@Controller()
@RequestMapping("/")
public class EditProductController {
	
	@ModelAttribute("productTypes")
    public Map<String, String> productTypes() {
        Map<String, String> types = new LinkedHashMap<>();
        types.put("type1", "Type 1");
        types.put("type2", "Type 2");
        types.put("type3", "Type 3");
        types.put("type4", "Type 4");
        types.put("type5", "Type 5");
        return types;
    }
	
	@RequestMapping(value = "/product/edit", method = RequestMethod.GET)
	public String editProduct(@ModelAttribute @Valid Product p) {
	    return "productForm";
	}
}
