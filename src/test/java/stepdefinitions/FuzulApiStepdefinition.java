package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pojos.product.Product;
import pojos.product.ProductData;
import pojos.product.ProductExpected;
import pojos.productid.ProductId;
import pojos.productid.ProductIdData;

import java.util.ArrayList;

import static baseUrl.BaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class FuzulApiStepdefinition {

    Response response;


    @Given("User sends a GET request for Entity")
    public void userSendsAGETRequestForEntity() {

        spec.pathParam("pr1", "Entity");

        response = given(spec).
                when().
                get("{pr1}");

    }

    @Then("User verifies that status code is equal to {int}")
    public void userVerifiesThatStatusCodeIsEqualTo(int statusCode) {
        response.
                then().
                assertThat().
                statusCode(statusCode);
    }

    @Then("User verifies that apiResultType is equal to {int}")
    public void userVerifiesThatApiResultTypeIsEqualTo(int apiResultType) {
        int actualapiResultType = response.jsonPath().get("apiResultType");
        assertEquals(apiResultType, actualapiResultType);


    }

    @Then("User verifies that the data contains {int} values")
    public void userVerifiesThatTheDataContainsValues(int dataCount) {
        response.
                then().
                assertThat().
                body("data", hasSize(dataCount));
    }

    @Then("User verifies that data with id number {int} exists")
    public void userVerifiesThatDataWithIdNumberExists(int id) {
        response.
                then().
                assertThat().
                body("data.id", hasItem(id));
    }

    @Then("User verifies that the data.value contains {string} and {string} and {string}")
    public void userVerifiesThatTheDataValueContainsAndAnd(String str1, String str2, String str3) {
        response.
                then().
                assertThat().
                body("data.value", hasItems(str1, str2, str3));
    }

    @Then("User verifies that the entityGroupId of id number {int} is {int}.")
    public void userVerifiesThatTheEntityGroupIdOfIdNumberIs(int id, int entityGroupId) {
        int dataSize = response.jsonPath().getList("data").size();

        for (int i = 0; i < dataSize; i++) {
            if (response.jsonPath().getInt("data.id[" + i + "]") == id) {
                assertEquals(entityGroupId, response.jsonPath().getInt("data.entityGroupId[" + i + "]"));
            }

        }


    }


    @Given("User sends a GET request for EntityGroup")
    public void userSendsAGETRequestForEntityGroup() {

        spec.pathParam("pr1", "EntityGroup");

        response = given(spec).
                when().
                get("{pr1}");

    }


    @Then("User verifies that data with id number {int} {int} {int} {int} {int} exists")
    public void userVerifiesThatDataWithIdNumberExists(int id1, int id2, int id3, int id4, int id5) {
        response.
                then().
                assertThat().
                body("data.id", hasItems(id1, id2, id3, id4, id5));
    }

    @Then("User verifies that the Emlak tipi contains {string} {string} {string} values")
    public void userVerifiesThatTheEmlakTipiContainsValues(String str1, String str2, String str3) {
        int dataSize = response.jsonPath().getList("data").size();
        for (int i = 0; i < dataSize; i++) {
            if (response.jsonPath().getString("data.value[" + i + "]").equals("Emlak Tipi")) {
                response.
                        then().
                        assertThat().
                        body("data.entity[" + i + "].value", hasItems(str1, str2, str3));

            }

        }


    }


    @Then("User verifies that the Oda Sayisi contains {string}")
    public void userVerifiesThatTheOdaSayisiContains(String odaSayisi) {
        int dataSize = response.jsonPath().getList("data").size();
        for (int i = 0; i < dataSize; i++) {
            if (response.jsonPath().getString("data.value[" + i + "]").equals("Oda Sayısı")) {
                response.
                        then().
                        assertThat().
                        body("data.entity[" + i + "].value", hasItem(odaSayisi));

            }

        }

    }

    @Then("User verifies that the Bulundugu kat contains {int} values")
    public void userVerifiesThatTheBulunduguKatContainsValues(int count) {
        int dataSize = response.jsonPath().getList("data").size();
        for (int i = 0; i < dataSize; i++) {
            if (response.jsonPath().getString("data.value[" + i + "]").equals("Bulunduğu Kat")) {
                int actualCount = response.jsonPath().getList("data.entity[" + i + "]").size();
                assertEquals(count, actualCount);
            }

        }
    }


    @Given("User sends a GET request for Product")
    public void userSendsAGETRequestForProduct() {
        spec.pathParam("pr1", "Product");

        response = given(spec).
                when().
                get("{pr1}");


    }

    @Then("User verifies that pageNumber is equal to {int}")
    public void userVerifiesThatPageNumberIsEqualTo(int pageNumber) {
        int actualPageNumber = response.jsonPath().get("pageNumber");
        assertEquals(pageNumber, actualPageNumber);


    }

    @Then("User verifies that pageSize is equal to {int}")
    public void userVerifiesThatPageSizeIsEqualTo(int pageSize) {
        int actualPageNumber = response.jsonPath().get("pageSize");
        assertEquals(pageSize, actualPageNumber);

    }

    @Then("User verifies that productPhotoUrl is not empty")
    public void userVerifiesThatProductPhotoUrlIsNotEmpty() {
        int dataSize = response.jsonPath().getList("data").size();
        for (int i = 0; i < dataSize; i++) {
            assertFalse(response.jsonPath().getString("data.productPhotoUrl[" + i + "]").isEmpty());

        }

    }


    @Then("User verifies that all values for id {int}")
    public void userVerifiesThatAllValuesForId(int id) {
        ProductData expectedProductData = new ProductData("https://i0.shbdn.com/photos/00/94/85/x5_11140094857va.jpg",
                5,
                2,
                "Kiralık",
                4,
                "Eşyalı",
                7, "1 + 1",
                14, "1. Kat",
                28,
                " > 20",
                "HUZURLU ŞEHİR EVDEN EVE TAŞINMAYA HAZIR",
                "Tarihi dokusuyla büyüleyen antika ev. Geçmişin izlerini taşıyan bu ev, nostaljik bir atmosfer sunuyor.",
                37230.0,
                173);

        ProductExpected expectedData = new ProductExpected(1,
                30,
                1,
                null,
                expectedProductData);
        Product actualData = response.as(Product.class);
        int index = id - 1;

        assertEquals(expectedData.getPageSize(), actualData.getPageSize());
        assertEquals(expectedData.getPageSize(), actualData.getPageSize());
        assertEquals(expectedData.getApiResultType(), actualData.getApiResultType());
        assertEquals(expectedData.getMessage(), actualData.getMessage());
        assertEquals(expectedProductData.getProductPhotoUrl(), actualData.getData().get(index).getProductPhotoUrl());
        assertEquals(expectedProductData.getId(), actualData.getData().get(index).getId());
        assertEquals(expectedProductData.getPropertyTypeEntityId(), actualData.getData().get(index).getPropertyTypeEntityId());
        assertEquals(expectedProductData.getPropertyTypeEntityValue(), actualData.getData().get(index).getPropertyTypeEntityValue());
        assertEquals(expectedProductData.getFurnitureConditionEntityId(), actualData.getData().get(index).getFurnitureConditionEntityId());
        assertEquals(expectedProductData.getFurnitureConditionEntityValue(), actualData.getData().get(index).getFurnitureConditionEntityValue());
        assertEquals(expectedProductData.getNumberOfRoomsEntityId(), actualData.getData().get(index).getNumberOfRoomsEntityId());
        assertEquals(expectedProductData.getNumberOfRoomsEntityValue(), actualData.getData().get(index).getNumberOfRoomsEntityValue());
        assertEquals(expectedProductData.getFloorLevelEntityId(), actualData.getData().get(index).getFloorLevelEntityId());
        assertEquals(expectedProductData.getFloorLevelEntityValue(), actualData.getData().get(index).getFloorLevelEntityValue());
        assertEquals(expectedProductData.getBuildingAgeEntityId(), actualData.getData().get(index).getBuildingAgeEntityId());
        assertEquals(expectedProductData.getBuildingAgeEntityValue(), actualData.getData().get(index).getBuildingAgeEntityValue());
        assertEquals(expectedProductData.getTitle(), actualData.getData().get(index).getTitle());
        assertEquals(expectedProductData.getDescription(), actualData.getData().get(index).getDescription());
        assertEquals(expectedProductData.getPrice(), actualData.getData().get(index).getPrice(), 0.0);
        assertEquals(expectedProductData.getTotalSquareFootage(), actualData.getData().get(index).getTotalSquareFootage());


    }

    @Given("User sends a GET request for Product {int}")
    public void userSendsAGETRequestForProduct(int id) {
        spec.pathParams("pr1", "Product",
                "pr2", id);

        response = given(spec).
                when().
                get("/{pr1}/{pr2}");

    }


    @Then("User verifies for all values")
    public void userVerifiesForAllValues() {
        ArrayList<String> productPhotoUrls = new ArrayList<>();
        productPhotoUrls.add("https://i0.shbdn.com/photos/96/92/51/x5_10999692513u4.jpg");
        productPhotoUrls.add("https://i0.shbdn.com/photos/96/92/51/x5_1099969251buf.jpg");

        ProductIdData productIdData = new ProductIdData(productPhotoUrls, 7,
                2,
                "Kiralık",
                5,
                "Eşyasız",
                6,
                "1 + 0",
                17,
                "4. Kat",
                27,
                "15 - 20",
                "BAĞ BAHÇE İÇİNDE LÜKS VİLLA",
                "Dağ manzaralı ahşap bir ev. Doğanın kucağında yer alan bu ev, sıcak ve doğal bir yaşam tarzı sunuyor.",
                61549.0,
                298);
        ProductId expectedData = new ProductId(1,
                null,
                productIdData);
        ProductId actualData = response.as(ProductId.class);


        assertEquals(expectedData.getApiResultType(),actualData.getApiResultType());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(productPhotoUrls.get(0),actualData.getData().getProductPhotoUrls().get(0));
        assertEquals(productPhotoUrls.get(1),actualData.getData().getProductPhotoUrls().get(1));
        assertEquals(productIdData.getId(),actualData.getData().getId());
        assertEquals(productIdData.getPropertyTypeEntityId() ,actualData.getData().getPropertyTypeEntityId());
        assertEquals(productIdData.getPropertyTypeEntityValue(),actualData.getData().getPropertyTypeEntityValue());
        assertEquals(productIdData.getFurnitureConditionEntityId(),actualData.getData().getFurnitureConditionEntityId());
        assertEquals(productIdData.getFurnitureConditionEntityValue(),actualData.getData().getFurnitureConditionEntityValue());
        assertEquals(productIdData.getNumberOfRoomsEntityId(),actualData.getData().getNumberOfRoomsEntityId());
        assertEquals(productIdData.getNumberOfRoomsEntityValue(),actualData.getData().getNumberOfRoomsEntityValue());
        assertEquals(productIdData.getFloorLevelEntityId(),actualData.getData().getFloorLevelEntityId());
        assertEquals(productIdData.getFloorLevelEntityValue(),actualData.getData().getFloorLevelEntityValue());
        assertEquals(productIdData.getBuildingAgeEntityId(),actualData.getData().getBuildingAgeEntityId());
        assertEquals(productIdData.getBuildingAgeEntityValue(),actualData.getData().getBuildingAgeEntityValue());
        assertEquals(productIdData.getTitle(),actualData.getData().getTitle());
        assertEquals(productIdData.getDescription(),actualData.getData().getDescription());
        assertEquals(productIdData.getPrice(),actualData.getData().getPrice(),0.0);
        assertEquals(productIdData.getTotalSquareFootage(),actualData.getData().getTotalSquareFootage());


    }

    @Given("User sends a GET request for WeatherForecast")
    public void userSendsAGETRequestForWeatherForecast() {
        String url="http://localhost:44334/WeatherForecast";
        response = given().
                when().
                get(url);
    }
}
