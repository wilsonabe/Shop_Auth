package com.qaguru.batch19shop.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaguru.batch19shop.models.Product;
import com.qaguru.batch19shop.services.ProductService;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class ShopTest {

    @Test
    public void saveANewProduct(){
        String file = "testdata/product.json";
        ProductService productService = new ProductService();
        Product product = productService.readProductDetails(file);
        String productId = productService.saveANewProduct(product);
        productService.findAProduct(productId,product);
    }




}
