package com.webservice;

import com.webservice.base.APITestCase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class BookServiceTest extends APITestCase {



    @Test(priority=0 ,description = "Verify that the API starts with an empty store")
    public void emptyStore() {

            Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).get(API_ROOT+"/api/books/");

            List<HashMap<String, String>> booklist = response.jsonPath().getList("$.");

            assertEquals(0, booklist.size(), "Booklist is not empty");

    }

    @Test(priority = 1, description = "Verify that author are required fields")
    public void requiredFieldTitle() throws Exception {

            Response response=given().contentType("application/json")
                    .body("{\"title\" : \"DevOps is a lie\"}")
                    .when()
                    .put(API_ROOT+"/api/books/");

            String errorMsg = response.jsonPath().get("error");
            assertEquals(errorMsg, "title and author are required fields", "Error message is not correct");

    }

    @Test(priority = 1, description = "Verify that title are required fields")
    public void requiredFieldAuthor() {

            Response response = given().contentType("application/json")
                    .body("{\"author\" : \"Jane Archer\"}")
                    .when()
                    .put(API_ROOT+"/api/books/");

            String errorMsg = response.jsonPath().get("error");
            assertEquals(errorMsg, "title and author are required fields", "Error message is not correct");

    }

    @Test(priority = 1, description = "Verify that author cannot be empty")
    public void emptyAuthorFields() {

            Response response = given().contentType("application/json")
                    .body("{\"author\" : \"\" ,\"title\" : \"DevOps is a lie\"}")
                    .when()
                    .put(API_ROOT+"/api/books/");

            String errorMsg = response.jsonPath().get("error");
            assertEquals(errorMsg, "title and author fields cannot be empty", "Error message is not correct");
    }

    @Test(priority = 1, description = "Verify that title cannot be empty")
    public void emptyTitleFields() {

            Response response = given().contentType("application/json")
                    .body("{\"author\" : \"Jane Archer\" ,\"title\" : \"\"}")
                    .when()
                    .put(API_ROOT+"/api/books/");

            String errorMsg = response.jsonPath().get("error");
            assertEquals(errorMsg, "title and author fields cannot be empty", "Error message is not correct");

    }

    @Test(priority = 1, description = "Verify that the id field is read−only")
    public void readOnlyId() {

            Response response = given().contentType("application/json")
                    .body("{\"id\" : \"2\",\"author\" : \"Jane Archer\" ,\"title\" : \"DevOps is a lie\"}")
                    .when()
                    .put(API_ROOT+"/api/books/");

            String errorMsg = response.jsonPath().get("error");
            assertEquals(errorMsg, "id field is readonly", "Error message is not correct");

    }

    @Test(priority = 2, description = "Verify that you can create a new book via PUT")
    public void putbooksSuccessfully() {

        Response addBookSuccessfullResponse = given().contentType("application/json")
                .body("{\"author\" : \"Jane Archer2\" ,\"title\" : \"DevOps is a lie\"}")
                .when()
                .put(API_ROOT+"/api/books/");

        String id = addBookSuccessfullResponse.jsonPath().get("id");
        String title = addBookSuccessfullResponse.jsonPath().get("title");

        Response addedBookResponse = given().contentType("application/json")
                .when()
                .get(API_ROOT + "/api/books/"+id+"/");

        String titleReturned = addedBookResponse.jsonPath().get("title");

        assertEquals(title, titleReturned, "Book couldnot add!!");

    }

    @Test(priority = 3, description = "Verify that you cannot create a duplicate book")
    public void putbooksDuplicate() {

            Response addBookSuccessfullResponse = given().contentType("application/json")
                    .body("{\"author\" : \"Jane Archer2\" ,\"title\" : \"DevOps is a lie\"}")
                    .when()
                    .put(API_ROOT+"/api/books/");

            String author = addBookSuccessfullResponse.jsonPath().get("author");
            String title = addBookSuccessfullResponse.jsonPath().get("title");

            Response addBookDuplicateResponse = given().contentType("application/json")
                    .body("{\"author\" : \""+author+"\" ,\"title\" : \""+title+"\"}")
                    .when()
                    .put(API_ROOT+"/api/books/");

            String errorMsg = addBookDuplicateResponse.jsonPath().get("error");
            assertEquals(errorMsg, "Another book with similar title and author already exists", "Error message is not correct");

    }


}
