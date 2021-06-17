package com.igorkunicyn.springdata.controllers;

import com.igorkunicyn.springdata.entities.Product;
import com.igorkunicyn.springdata.enums.AddProduct;
import com.igorkunicyn.springdata.enums.SortProduct;
import com.igorkunicyn.springdata.utils.ProductModelMinAndMaxPrice;
import com.igorkunicyn.springdata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/showProductById", method = RequestMethod.GET)
    @ResponseBody
    public String showProductById(@RequestParam int id) {
        Product product = productService.getProductRepo().findProductById(id);
        if (product != null) {
            return String.format("id: %s, title: %s, cost: %s", product.getId(), product.getTitle(), product.getPrice());
        }
        return "Product with id = " + id + " not exists";
    }
//
//    @GetMapping("/showForm")
//    public String showSimpleForm(Model uiModel) {
//        Product product = new Product();
//        uiModel.addAttribute("product", product);
//        return "oldforms/product-add-form";
//    }
//
//    @GetMapping("/resultAddProduct")
//    public String resultAddProduct(@ModelAttribute("product") Product product) {
//        String result = productService.addProduct(product);
//        if (result.equals(AddProduct.ADD_PRODUCT.getName())) {
//            return "product-add-form-success";
//        }
//        if (result.equals(AddProduct.ID_NOT_UNIQUE.getName())) {
//            return "product-not-unique-id";
//        }
//        return "oldforms/product-exists";
//    }

    @GetMapping("/listProducts")
    public String listProducts(Model uiModel) {
//        List<Product> products = productService.getProductRepo().findAll();
//        uiModel.addAttribute("listProducts", products);
        return pageProducts(1,uiModel);
    }
//
//    @RequestMapping("/sorted/{sort}")
//    public String sortedProducts(@PathVariable(name = "sort") String sort, Model uiModel) {
//        if (sort.equals(SortProduct.SORT_MIN.getName())) {
//            List<Product> listProductsMinPrice = productService.sortedProduct(SortProduct.SORT_MIN.getName());
//            uiModel.addAttribute("listProductsMinPrice", listProductsMinPrice);
//            return "oldforms/product-min-price";
//        }
//        if (sort.equals(SortProduct.SORT_MAX.getName())) {
//            List<Product> listProductsMaxPrice = productService.sortedProduct(SortProduct.SORT_MAX.getName());
//            uiModel.addAttribute("listProductsMaxPrice", listProductsMaxPrice);
//            return "oldforms/product-max-price";
//        }
//        List<ProductModelMinAndMaxPrice> listProductsMinPrice = productService.getModel();
//        uiModel.addAttribute("listProductsMinAndMaxPrice", listProductsMinPrice);
//        return "oldforms/product-max-and-min-price";
//    }

    @GetMapping(value = "/page/{pageNum}")
    public String pageProducts(@PathVariable(name = "pageNum") int pageNum, Model uiModel) {
        Page<Product> productPage = productService.findPaginated(pageNum);
        List<Product> productList = productPage.getContent();
        uiModel.addAttribute("currentPage", pageNum);
        uiModel.addAttribute("totalPages", productPage.getTotalPages());
        uiModel.addAttribute("totalItems", productPage.getTotalElements());
        uiModel.addAttribute("listProducts", productList);
        return "list-product-user";

    }

}

