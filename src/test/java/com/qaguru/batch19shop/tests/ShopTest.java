package com.qaguru.batch19shop.tests;

import com.qaguru.batch19shop.models.Product;
import com.qaguru.batch19shop.services.ProductService;
import org.testng.annotations.Test;

public class ShopTest {

    @Test
    public void saveANewProduct(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product);
        productService.findAProduct(productId,product);
    }
    @Test
    public void updateAProduct(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product);

        Product product2 = Product.builder()
                .name("Samsung 20")
                .description("A Featured phone")
                .price(1700.99)
                .build();
        productService.updateAProduct(productId,product2);
        productService.findAProduct(productId, product2);
    }

    @Test
    public void deleteAProduct(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product);
        productService.deleteService(productId);
    }




}
