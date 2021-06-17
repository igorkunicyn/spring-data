package com.igorkunicyn.springdata.controllers;

import com.igorkunicyn.springdata.entities.Product;
import com.igorkunicyn.springdata.services.ProductService;
import com.igorkunicyn.springdata.services.ShopCartService;
import com.igorkunicyn.springdata.utils.CartElement;
import com.igorkunicyn.springdata.utils.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ProductService productService;
    private ShopCartService shopCartService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShopCartService(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @GetMapping
    public String cartPage(Model model, HttpSession httpSession) {
        ShopCart cart = shopCartService.getCurrentCart(httpSession);
        List<CartElement> cartElementList = cart.getCartElementList();
        double price = shopCartService.totalPriceCart(cart);
        model.addAttribute("price",price);
        model.addAttribute("cartList", cartElementList);
        return "cart-page";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id")Long id, HttpSession httpSession){
        Product product = productService.getProductRepo().findProductById(id);
        ShopCart shopCart = shopCartService.getCurrentCart(httpSession);
        shopCartService.addCart(product, shopCart);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{title}")
    public String addToCart(@PathVariable("title")String title, HttpSession httpSession){
        ShopCart shopCart = shopCartService.getCurrentCart(httpSession);
        shopCartService.deleteFromCart(shopCart, title);
        return "redirect:/cart";
    }

}
