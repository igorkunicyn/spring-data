package com.igorkunicyn.springdata.services;

import com.igorkunicyn.springdata.entities.Product;
import com.igorkunicyn.springdata.enums.AddProduct;
import com.igorkunicyn.springdata.enums.SortProduct;
import com.igorkunicyn.springdata.models.ProductModelMinAndMaxPrice;
import com.igorkunicyn.springdata.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public ProductRepository getProductRepo() {
        return productRepo;
    }

    public String addProduct(Product product) {
        for (Product products : productRepo.findAll()) {
            if (product.getTitle().equals(products.getTitle()) && product.getPrice() == products.getPrice()) {
                return AddProduct.PRODUCT_EXISTS.getName();
            }
        }
        productRepo.save(product);
        return AddProduct.ADD_PRODUCT.getName();
    }

    public Page<Product> findPaginated(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return productRepo.findAll(pageable);
    }

    public List<Product> sortedProduct(String sort) {
        List<Product> productList = productRepo.findAll();
        Set<String> titleSet = new HashSet<>();
        for (Product product : productList) {
            titleSet.add(product.getTitle());
        }
        List<Product> productListResult = new ArrayList<>();
        if (sort.equals(SortProduct.SORT_MIN.getName())) {
            for (String title : titleSet) {
                productListResult.add(productRepo.findAllByTitleOrderByPriceAsc(title).get(0));
            }
        }
        if (sort.equals(SortProduct.SORT_MAX.getName())) {
            for (String title : titleSet) {
                productListResult.add(productRepo.findAllByTitleOrderByPriceDesc(title).get(0));
            }
        }
        return productListResult;
    }

    public List<ProductModelMinAndMaxPrice> getModel() {
        List<Product> productList = productRepo.findAll();
        Set<String> titleSet = new HashSet<>();
        for (Product product : productList) {
            titleSet.add(product.getTitle());
        }
        List<ProductModelMinAndMaxPrice> minAndMaxPriceList = new ArrayList<>();
        for (String title : titleSet) {
            Product productMinPrice = productRepo.findAllByTitleOrderByPriceAsc(title).get(0);
            Product productMaxPrice = productRepo.findAllByTitleOrderByPriceDesc(title).get(0);
            ProductModelMinAndMaxPrice modelMinAndMaxPrice = new ProductModelMinAndMaxPrice();
            modelMinAndMaxPrice.setTitle(productMinPrice.getTitle());
            modelMinAndMaxPrice.setPriceMin(productMinPrice.getPrice());
            modelMinAndMaxPrice.setIdMin(productMinPrice.getId());
            modelMinAndMaxPrice.setPriceMax(productMaxPrice.getPrice());
            modelMinAndMaxPrice.setIdMax(productMaxPrice.getId());
            minAndMaxPriceList.add(modelMinAndMaxPrice);
        }
        return minAndMaxPriceList;
    }

}
