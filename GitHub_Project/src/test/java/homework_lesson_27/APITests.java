package homework_lesson_27;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;

public class APITests {

    @Test
    public void testGet() {
        baseURI = "https://jsonplaceholder.typicode.com";

        String str = given().
                when().
                get(baseURI + "/users").
                then().
                extract().body().asString();


        JsonPath jsonPath = new JsonPath(str);

        // Роздрукувати всі email
        System.out.println("\nРоздрукувати всі email");

        System.out.println(jsonPath.getList("email"));

        // Роздрукувати всі zipcode
        System.out.println("\nРоздрукувати всі zipcode");

        System.out.println(jsonPath.getList("address.zipcode"));

        // Роздрукувати всі zipcode без тире
        System.out.println("\nРоздрукувати всі zipcode без тире");

        System.out.println(jsonPath.getList("findAll{!it.address.zipcode.contains('-')}.address.zipcode"));

        System.out.println();

        // Для кожного name вивести значення lat та lng (приклад: Leanne Graham is situated at: lat = -37.3159 and lng = 81.1496)
        System.out.println("\nДля кожного name вивести значення lat та lng");

        List<String> names = jsonPath.getList("name");
        List<String> lats = jsonPath.getList("address.geo.lat");
        List<String> lngs = jsonPath.getList("address.geo.lng");

        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%s is situated at: lat = %s and lng = %s \n", names.get(i), lats.get(i), lngs.get(i));
        }

        // Роздрукувати username лише для тих, в кого значення lat та lng відʼємні
        System.out.println("\nРоздрукувати username лише для тих, в кого значення lat та lng відʼємні");

        System.out.println(jsonPath.getList("findAll{it.address.geo.lat < '0' && it.address.geo.lng < '0'}.username"));

        // Роздрукувати username лише для тих в кого значення website закінчується на .info (приклад: Samantha - ramiro.info і так далі)
        System.out.println("\nРоздрукувати username лише для тих в кого значення website закінчується на .info");

        List<String> usernames = jsonPath.getList("username");
        List<String> websites = jsonPath.getList("website");

        for (int i = 0; i < websites.size(); i++) {
            if (websites.get(i).endsWith(".info")) {
                System.out.println(usernames.get(i));
            }
        }

        // Вивести на екран name в якого значення lng найбільше
        System.out.println("\nВивести на екран name в якого значення lng найбільше");

        String maxLngStr = jsonPath.getString("address.geo.lng.max()");
        System.out.println(jsonPath.getString("find{it.address.geo.lng == \'" + maxLngStr + "\'}.name"));

        // Вивести на екран name в якого найдовша catchPhrase
        System.out.println("\nВивести на екран name в якого найдовша catchPhrase");

        int maxPhraseLength = jsonPath.getInt("collect{it.company.catchPhrase.length()}.max()");
        System.out.println(jsonPath.getString("find{it.company.catchPhrase.length() == "+ maxPhraseLength +"}.name"));
    }

}
