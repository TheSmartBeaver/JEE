package springapp.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import springapp.business.IProductManager;
import springapp.model.Product;
import springapp.model.ProductCode;

@Controller()
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductManager manager;

    protected final Log logger = LogFactory.getLog(getClass());

    @ModelAttribute("products")
    Collection<Product> products() {
        logger.info("Making list of products");
        return manager.findAll();
    }
    
    @ModelAttribute
    public Product newProduct(
            @RequestParam(value = "id", required = false) Integer productNumber) {
        if (productNumber != null) {
            logger.info("find product " + productNumber);
            return manager.find(productNumber);
        }
        Product p = new Product();
        p.setNumber(null);
        p.setName("");
        p.setPrice(0.0);
        p.setDescription("");
        logger.info("new product = " + p);
        return p;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProducts() {
        logger.info("List of products");
        return "productsList";
    }
    
    @Autowired
    ProductValidator validator;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute @Valid Product p, BindingResult result) {
        validator.validate(p, result);
        if (result.hasErrors()) {
            return "productForm";
        }
        manager.save(p);
        return "productsList";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(ProductCode.class, new ProductCodeEditor());
    }
}