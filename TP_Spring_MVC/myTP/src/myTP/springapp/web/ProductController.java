package myTP.springapp.web;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import myTP.springapp.business.IProductManager;
import myTP.springapp.model.Product;

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
    
    /*@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listProducts() {
        logger.info("List of products");
        Collection<Product> products = manager.findAll();
        return new ModelAndView("productsList", "products", products);
    }*/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProducts() {
        logger.info("List of products");
        return "productsList";
    }
    
    //accès au formulaire d'édition
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(@ModelAttribute Product p) {
        return "productForm";
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
    /*A cette étape, la seule erreur possible provient d'une erreur de conversion sur le prix. Essayez de donner un prix incorrect afin de tester ce fonctionnement.

Avertissement : A ce stade, la création d'un nouveau produit (après la soumission) se termine sur l'affichage de la liste des produits (dernière ligne du contrôleur ci-dessus). Ce comportement pose un problème : Si le client tente un rechargement de la page, cela va provoquer une nouvelle soumission et la création d'un nouveau produit !

Pour régler ce problème, nous allons renvoyer non pas sur la vue productsList, mais sur l'action permettant d'avoir la liste des produits*/
    
    
    /*On l'a encore REMPLACE PAR Form plus sophistiqué*/
    /*@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute Product p, BindingResult result) {
        if (result.hasErrors()) {
            return "productForm";
        }
        manager.save(p);
        return "redirect:list";
    }*/
    
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
    
    @Autowired
    ProductValidator validator;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute Product p, BindingResult result) {
        validator.validate(p, result);
        if (result.hasErrors()) {
            return "productForm";
        }
        manager.save(p);
        return "productsList";
    }

}