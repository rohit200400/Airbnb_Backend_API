//package airbnb.clone.api.controllerTests;
//
//import airbnb.clone.api.entity.Users;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.json.JacksonTester;
//
//import java.util.Date;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class UsersTest {
//
//    private JacksonTester<Users> jsonTester;
//
//    @BeforeEach
//    void setUp() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JacksonTester.initFields(this, objectMapper);
//    }
//
//    @Test
//    void serializesToJson() throws Exception {
//        Users users = new Users();
//        users.setId(1L);
//        users.setFirstName("John");
//        users.setLastName("Doe");
//        users.setDateOfBirth(new Date());
//        users.setEmail("john.doe@example.com");
//        users.setContactNumber("123-456-7890");
//        users.setEmergencyContactNumber("911");
//        users.setPassword("password");
//        users.setRole("ROLE_USER");
//
//        String json = jsonTester.write(users).getJson();
//
//        assertThat(json).isEqualTo(
//                "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"dateOfBirth\":\"2023-08-29\",\"email\":\"john.doe@example.com\",\"contactNumber\":\"123-456-7890\",\"emergencyContactNumber\":\"911\",\"password\":\"password\",\"role\":\"ROLE_USER\"}"
//        );
//    }
//
//    @Test
//    void deserializesFromJson() throws Exception {
//        String json = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"dateOfBirth\":\"2023-08-29\",\"email\":\"john.doe@example.com\",\"contactNumber\":\"123-456-7890\",\"emergencyContactNumber\":\"911\",\"password\":\"password\",\"role\":\"ROLE_USER\"}";
//
//        Users users = jsonTester.parseObject(json);
//
//        assertThat(users).isEqualTo(
//                new Users(1L, "John", "Doe", new Date(), "john.doe@example.com", "123-456-7890", "911", "password", "ROLE_USER")
//        );
//    }
//}
//
