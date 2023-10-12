package taskInClass1;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTodoLy {
    @Test
    public void createUserAndAddItem() {
        // Crear un nuevo usuario
        JSONObject newUser = new JSONObject();
        newUser.put("Email", "ganzo@tuqui.com");
        newUser.put("Password", "ganzo1234");
        newUser.put("FullName", "Emanuel");

        Response userResponse = given()
                .body(newUser.toString())
                .log()
                .all()
                .when()
                .post("https://todo.ly/api/user.json");

        userResponse.then()
                .log()
                .all()
                .statusCode(200)
                .body("Email", equalTo(newUser.get("Email")));

        // Usar el usuario creado para crear un ítem
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content", "Emanuel API");

        Response itemResponse = given()
                .auth()
                .preemptive()
                .basic(newUser.getString("Email"), newUser.getString("Password"))
                .body(bodyProject.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json");

        itemResponse.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyProject.get("Content")));
    }
}
