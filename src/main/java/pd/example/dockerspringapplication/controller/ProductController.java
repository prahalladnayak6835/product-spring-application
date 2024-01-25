package pd.example.dockerspringapplication.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pd.example.dockerspringapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    List<Product> products = new ArrayList<>();



    @GetMapping("/")
    public String getList(Model model) {

        products.add(new Product(1L,"Shampoo",2,119.00));
        products.add(new Product(2L,"Hair Oil",1,250.50));
        products.add(new Product(3L,"Soap",5,100.00));
        products.add(new Product(4L,"Talc",1,40.00));
        products.add(new Product(5L,"Toothpaste",2,50.00));
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProduct(Model model) {
        model.addAttribute("products", products);
        return "add-products";
    }
    @PostMapping("/add")
    public String addUser(@ModelAttribute Product product,Model model) {
        product.setId((long) (products.size() + 1));
        products.add(product);
        model.addAttribute("products", products);
        return "products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        model.addAttribute("products", products);
        products.removeIf(x->x.getId()==(id));
        return "products";
    }
}