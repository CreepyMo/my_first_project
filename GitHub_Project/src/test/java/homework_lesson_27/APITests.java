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

        List<String> zipCodes = jsonPath.getList("address.zipcode");
        for (String zipCode : zipCodes) {
            System.out.print(zipCode.replace("-", "") + " ");
        }
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

        List<String> usernames = jsonPath.getList("username");

        List<Double> doubleLats = new ArrayList<>();
        for (String lat : lats) {
            doubleLats.add(Double.parseDouble(lat));
        }

        List<Double> doubleLngs = new ArrayList<>();
        for (String lng : lngs) {
            doubleLngs.add(Double.parseDouble(lng));
        }

        for (int i = 0; i < names.size(); i++) {
            if (doubleLngs.get(i) < 0 && doubleLats.get(i) < 0) {
                System.out.println(usernames.get(i));
            }
        }

        // Роздрукувати username лише для тих в кого значення website закінчується на .info (приклад: Samantha - ramiro.info і так далі)
        System.out.println("\nРоздрукувати username лише для тих в кого значення website закінчується на .info");

        List<String> websites = jsonPath.getList("website");

        for (int i = 0; i < websites.size(); i++) {
            if (websites.get(i).endsWith(".info")) {
                System.out.println(usernames.get(i));
            }
        }


        // Вивести на екран name в якого значення lng найбільше
        System.out.println("\nВивести на екран name в якого значення lng найбільше");

        List<Double> lngsValues = new ArrayList<>();
        for (String lng : lngs) {
            lngsValues.add(Double.parseDouble(lng));
        }

        Double maxLengthValue = Collections.max(lngsValues);
        int maxLengthIndex = lngsValues.indexOf(maxLengthValue);

        System.out.println("Name with max lng value is: " + names.get(maxLengthIndex));


        // Вивести на екран name в якого найдовша catchPhrase
        System.out.println("\nВивести на екран name в якого найдовша catchPhrase");

        List<String> catchPhrases = jsonPath.getList("company.catchPhrase");

        List<Integer> phrasesLenghts = new ArrayList<>();
        for (String length : catchPhrases) {
            phrasesLenghts.add(length.length());
        }
        int maxValue = Collections.max(phrasesLenghts);
        int index = phrasesLenghts.indexOf(maxValue);

        System.out.println("Name with max catchPhrase length is: " + names.get(index));
    }

}
