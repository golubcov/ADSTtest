package ADST;

/**
 * Created by Eugene on 18.12.15.
 */
import com.thoughtworks.selenium.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.regex.Pattern;

public class deals extends SeleneseTestNgHelper {
    @Test public void testDeals() throws Exception {
        selenium.open("http://adstamer.mkechinov.ru/");
        selenium.click("link=Войти");
        selenium.waitForPageToLoad("30000");
        selenium.type("id=user_email", "golubcov.e@gmail.com");
        selenium.type("id=user_password", "123456");
        selenium.click("name=commit");
        selenium.waitForPageToLoad("30000");
        selenium.open("/users/13");
        selenium.click("link=Мои объявления");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Подобрать площадку");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Заключить сделку");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Выбрать");
        selenium.waitForPageToLoad("30000");
        selenium.type("id=deal_run_date_from", "2015-12-18");
        selenium.type("id=deal_run_date_to", "2015-12-31");
        selenium.select("id=deal_run_time_from", "label=00:00");
        selenium.select("id=deal_run_time_to", "label=09:00");
        selenium.select("id=deal_post_time", "label=1");
        selenium.select("id=deal_post_time_on_first_position", "label=1");
        selenium.click("name=commit");
        selenium.waitForPageToLoad("30000");
        verifyEquals(selenium.getText("css=div.notification"), "Предложение отправлено");
    }
}