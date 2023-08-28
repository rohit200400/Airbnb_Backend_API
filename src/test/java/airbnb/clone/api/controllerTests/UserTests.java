package airbnb.clone.api.controllerTests;

import airbnb.clone.api.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;



@JsonTest
public class UserTests {

    @Autowired
    private JacksonTester<Users> json;

    @Autowired
    private JacksonTester<Users[]> jsonList;

    private Users[] users;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2016-29-09");

        users = new Users[]{
                new Users(1L, "Widget", "Francis", date, "email@email.com",
                        "408-867-5309", "408-867-5309",
                        "Pass@123", "user"),
                new Users(2L, "Widget", "Francis", date, "email1@email.com",
                        "408-867-5309", "408-867-5309",
                        "Pass@123", "user")
        };
    }

    @Test
    public void cashCardSerializationTest() throws Exception {
        Users testUser = this.users[0];
        // checking if all the fields are present
        assertThat(json.write(testUser)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.firstName");
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.lastName");
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.DateOfBirth"); // Updated
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.email");
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.contactNumber");
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.EmergencyContactNumber"); // Updated
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.password");
        assertThat(json.write(testUser)).hasJsonPathStringValue("@.role");

        // checking the values if correct
        assertThat(json.write(testUser)).extractingJsonPathStringValue("@.password")
                .isEqualTo("Pass@123");
        assertThat(json.write(testUser)).extractingJsonPathStringValue("@.email")
                .isEqualTo("email@email.com");
    }

    @Test
    public void cashCardDeserializationTest() throws Exception {
        String expected = """
            {
               "id": 1,
               "firstName": "Widget",
               "lastName": "Francis",
               "DateOfBirth": "2016-29-09", // Updated
               "email": "examle@gmail.com", // Updated
               "contactNumber": "408-867-5309",
               "EmergencyContactNumber": "408-867-5309", // Updated
               "password": "Pass@123",
               "role": "user"
            }
            """;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2016-29-09");
        assertThat(json.parseObject(expected).getPassword()).isEqualTo("Pass@123");
        assertThat(json.parseObject(expected).getEmail()).isEqualTo("examle@gmail.com");
    }

}
