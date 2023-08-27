//package airbnb.clone.api;
//
//        import org.assertj.core.util.Arrays;
//        import org.junit.jupiter.api.BeforeEach;
//        import org.junit.jupiter.api.Test;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.test.autoconfigure.json.JsonTest;
//        import org.springframework.boot.test.json.JacksonTester;
//
//        import java.io.IOException;
//
//        import static org.assertj.core.api.Assertions.assertThat;
//
//@JsonTest
//public class sampleTest {
//
//    @Autowired
//    private JacksonTester<CashCard> json;
//
//    @Autowired
//    private JacksonTester<CashCard[]> jsonList;
//
//    private CashCard[] cashCards;
//
//    @BeforeEach
//    void setUp() {
//        cashCards = Arrays.array(
//                new CashCard(99L, 123.45, "sarah1"),
//                new CashCard(100L, 1.00, "sarah1"),
//                new CashCard(101L, 150.00, "sarah1"));
//    }
//
//    @Test
//    public void cashCardSerializationTest() throws IOException {
//        CashCard cashCard = cashCards[0];
//        assertThat(json.write(cashCard)).isStrictlyEqualToJson("single.json");
//        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
//        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
//                .isEqualTo(99);
//        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
//        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
//                .isEqualTo(123.45);
//    }
//
//    @Test
//    public void cashCardDeserializationTest() throws IOException {
//        String expected = """
//                {
//                    "id": 99,
//                    "amount": 123.45,
//                    "owner": "sarah1"
//                }
//                """;
//        assertThat(json.parse(expected))
//                .isEqualTo(new CashCard(99L, 123.45, "sarah1"));
//        assertThat(json.parseObject(expected).id()).isEqualTo(99L);
//        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
//    }
//
//    @Test
//    void cashCardListSerializationTest() throws IOException {
//        assertThat(jsonList.write(cashCards)).isStrictlyEqualToJson("list.json");
//    }
//
//    @Test
//    void cashCardListDeserializationTest() throws IOException {
//        String expected = """
//                [
//                     {"id": 99, "amount": 123.45 , "owner": "sarah1"},
//                     {"id": 100, "amount": 1.00 , "owner": "sarah1"},
//                     {"id": 101, "amount": 150.00, "owner": "sarah1"}
//
//                ]
//                """;
//        assertThat(jsonList.parse(expected)).isEqualTo(cashCards);
//    }
//}
//
//
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class CashCardApplicationTests {
//    @Autowired
//    TestRestTemplate restTemplate;
//
//    @Test
//    void shouldReturnACashCardWhenDataIsSaved() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards/99", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        Number id = documentContext.read("$.id");
//        assertThat(id).isEqualTo(99);
//
//        Double amount = documentContext.read("$.amount");
//        assertThat(amount).isEqualTo(123.45);
//    }
//
//    @Test
//    void shouldNotReturnACashCardWithAnUnknownId() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards/1000", String.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//        assertThat(response.getBody()).isBlank();
//    }
//
//    @Test
//    @DirtiesContext
//    void shouldCreateANewCashCard() {
//        CashCard newCashCard = new CashCard(null, 250.00, "sarah1");
//        ResponseEntity<Void> createResponse = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .postForEntity("/cashcards", newCashCard, Void.class);
//        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//        URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
//        ResponseEntity<String> getResponse = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity(locationOfNewCashCard, String.class);
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//        Number id = documentContext.read("$.id");
//        Double amount = documentContext.read("$.amount");
//
//        assertThat(id).isNotNull();
//        assertThat(amount).isEqualTo(250.00);
//    }
//
//    @Test
//    void shouldReturnAllCashCardsWhenListIsRequested() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        int cashCardCount = documentContext.read("$.length()");
//        assertThat(cashCardCount).isEqualTo(3);
//
//        JSONArray ids = documentContext.read("$..id");
//        assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);
//
//        JSONArray amounts = documentContext.read("$..amount");
//        assertThat(amounts).containsExactlyInAnyOrder(123.45, 1.00, 150.00);
//    }
//
//    @Test
//    void shouldReturnAPageOfCashCards() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards?page=0&size=1", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        JSONArray page = documentContext.read("$[*]");
//        assertThat(page.size()).isEqualTo(1);
//    }
//
//    @Test
//    void shouldReturnASortedPageOfCashCards() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards?page=0&size=1&sort=amount,desc", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        JSONArray read = documentContext.read("$[*]");
//        assertThat(read.size()).isEqualTo(1);
//
//        double amount = documentContext.read("$[0].amount");
//        assertThat(amount).isEqualTo(150.00);
//    }
//
//    @Test
//    void shouldReturnASortedPageOfCashCardsWithNoParametersAndUseDefaultValues() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        JSONArray page = documentContext.read("$[*]");
//        assertThat(page.size()).isEqualTo(3);
//
//        JSONArray amounts = documentContext.read("$..amount");
//        assertThat(amounts).containsExactly(1.00, 123.45, 150.00);
//    }
//
//    @Test
//    void shouldNotReturnACashCardWhenUsingBadCredentials() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("BAD-USER", "abc123")
//                .getForEntity("/cashcards/99", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//
//        response = restTemplate
//                .withBasicAuth("sarah1", "BAD-PASSWORD")
//                .getForEntity("/cashcards/99", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//    }
//
//    @Test
//    void shouldRejectUsersWhoAreNotCardOwners() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("hank-owns-no-cards", "qrs456")
//                .getForEntity("/cashcards/99", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
//    }
//
//    @Test
//    void shouldNotAllowAccessToCashCardsTheyDoNotOwn() {
//        ResponseEntity<String> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards/102", String.class); // kumar2's data
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    @DirtiesContext
//    void shouldUpdateAnExistingCashCard() {
//        CashCard cashCardUpdate = new CashCard(null, 19.99, null);
//        HttpEntity<CashCard> request = new HttpEntity<>(cashCardUpdate);
//        ResponseEntity<Void> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .exchange("/cashcards/99", HttpMethod.PUT, request, Void.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//
//        ResponseEntity<String> getResponse = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .getForEntity("/cashcards/99", String.class);
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//        Number id = documentContext.read("$.id");
//        Double amount = documentContext.read("$.amount");
//        assertThat(id).isEqualTo(99);
//        assertThat(amount).isEqualTo(19.99);
//    }
//
//    @Test
//    void shouldNotUpdateACashCardThatDoesNotExist() {
//        CashCard unknownCard = new CashCard(null, 19.99, null);
//        HttpEntity<CashCard> request = new HttpEntity<>(unknownCard);
//        ResponseEntity<Void> response = restTemplate
//                .withBasicAuth("sarah1", "abc123")
//                .exchange("/cashcards/99999", HttpMethod.PUT, request, Void.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//}