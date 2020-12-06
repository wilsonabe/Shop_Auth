package com.qaguru.batch19shop.tests;

import com.qaguru.batch19shop.models.Product;
import com.qaguru.batch19shop.services.ProductService;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

public class ShopTest {

    @Test
    public void saveANewProduct(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product,HttpStatus.SC_CREATED,"maria","maria123");
        productService.findAProduct(productId,product,HttpStatus.SC_OK);
    }
    @Test
    public void updateAProduct(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product,HttpStatus.SC_CREATED,"maria","maria123");

        Product product2 = Product.builder()
                .name("Samsung 20")
                .description("A Featured phone")
                .price(1700.99)
                .build();
        productService.updateAProduct(productId,product2);
        productService.findAProduct(productId, product2,HttpStatus.SC_OK);
    }

    @Test
    public void deleteAProduct(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product,HttpStatus.SC_CREATED,"maria","maria123");
        productService.deleteService(productId);
        productService.findAProduct(productId,null, HttpStatus.SC_NOT_FOUND);
    }
    @Test
    public void findAllProducts(){
        String file = "testdata/productarray.json";
        ProductService productService = new ProductService();
        List<Product> products = productService.readProductList(file);
        for(int i=0;i<products.size();i++){
            productService.saveANewProduct(products.get(i),HttpStatus.SC_CREATED,"maria","maria123");
        }
        productService.findAllProducts(products);

    }

    @Test
    public void saveANewProductWithUnauthorized(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product,HttpStatus.SC_FORBIDDEN,"john", "john123");
    }


}
