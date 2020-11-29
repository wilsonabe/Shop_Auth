package com.qaguru.batch19shop.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaguru.batch19shop.models.Product;
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
    private String baseUri = "http://localhost:8090";
    private String basePath = "/api/v1/products";
    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void sampleTest(){
        System.out.println("My test");
        String sNo = "1800.26";
        double dNo = Double.valueOf(sNo);

    }

    @Test
    public void saveANewProduct(){
        String file = "testdata/product.json";
        URL url = getClass().getClassLoader().getResource(file);
        Product product = null;
        try {
            product = objectMapper.readValue(url,Product.class);

        } catch (IOException e) {
            System.out.println("File read error");
            e.printStackTrace();
        }


        ValidatableResponse response = given().baseUri(baseUri)
                .basePath(basePath)
                .contentType(ContentType.JSON)
                .body(product)
                .log().all()
        .when()
                .post("/")
        .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_CREATED)
                .assertThat().header("Location",containsString("/api/v1/products/"));
        String location = response.extract().header("Location");
        String id = location.substring(basePath.length()+1);
        System.out.println("Product id - " +id);

        ValidatableResponse getResponse = given().baseUri(baseUri)
                .basePath(basePath)
                .log().all()
                .when()
                .get("/"+ id)
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK);

        Product resProduct = getResponse.extract().body().as(Product.class);
        product.setId(resProduct.getId());
        Assert.assertEquals(resProduct,product,"Incorrect product details");
    }
}
