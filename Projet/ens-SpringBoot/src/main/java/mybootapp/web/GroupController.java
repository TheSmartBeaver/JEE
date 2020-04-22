package mybootapp.web;

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
import mybootapp.business.IProductManager;
import mybootapp.dao.DAOPerson;
import mybootapp.model.Party;
import mybootapp.model.Product;
import mybootapp.model.ProductCode;

@Controller()
@RequestMapping("/")
public class GroupController {

    @Autowired
    DAOPerson dao;

    protected final Log logger = LogFactory.getLog(getClass());
    
    

    @ModelAttribute("availableGroups")
    Iterable<Party> availableGroups() {
        logger.info("Making list of products");
        return dao.findAllParties();
    }
    
    @ModelAttribute
    public Product newProduct(
            @RequestParam(value = "id", required = false) Integer productNumber) {
        if (productNumber != null) {
            logger.info("find product " + productNumber);
            return null;
            //return manager.find(productNumber);
        }
        Product p = new Product();
        p.setNumber(null);
        p.setName("");
        p.setPrice(0.0);
        p.setDescription("");
        logger.info("new product = " + p);
        return p;
    }
    
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public String listProducts() {
        logger.info("List of products");
        return "groupList";
    }
    
    @Autowired
    ValidatorProduct validator;

    @RequestMapping(value = "/group/list", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute @Valid Product p, BindingResult result) {
    	System.err.println("SAVE PRODUCT");
    	System.err.println(p.getType());
        //validator.validate(p, result);
        if (result.hasErrors()) {
            return "groupList";
        }
        //manager.save(p);
        return "groupList";
    }
    
    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(ProductCode.class, new EditorProductCode());
    }
}