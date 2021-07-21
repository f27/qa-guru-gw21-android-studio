package helpers;

import config.Project;

import static io.restassured.RestAssured.given;

public class BrowserstackApi {
    public static String getVideoUrl(String sessionId) {
        return given()
                .auth().basic(Project.deviceConfig.hubUsername(), Project.deviceConfig.hubPassword())
                .when()
                .get(Project.deviceConfig.bsApiUrl() + "/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .path("automation_session.video_url");
    }
}
