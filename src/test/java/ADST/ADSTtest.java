package ADST;

/**
 * Created by Eugene on 10.12.15.
 */

        import java.util.Random;
        import java.util.UUID;
        import java.util.concurrent.TimeUnit;
        import org.junit.*;
        import static org.junit.Assert.*;
        import static org.hamcrest.CoreMatchers.*;
        import org.openqa.selenium.*;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.support.ui.Select;
        import org.testng.Assert;
        import org.testng.annotations.AfterClass;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;


public class ADSTtest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    //test1
    @Test
    public void test01IncorrectLogin() throws Exception {
        baseUrl = "http://adstamer.mkechinov.ru";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        driver.findElement(By.name("commit")).click();
        try {
            assertEquals("Неверный email или пароль.", driver.findElement(By.xpath("//div[contains(text(),'Неверный email или пароль')]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_email")).sendKeys("golubcov.e@gmail.com");
        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.name("commit")).click();
        try {
            assertEquals("Неверный email или пароль.", driver.findElement(By.xpath("//div[contains(text(),'Неверный email или пароль')]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys("123456");
        driver.findElement(By.name("commit")).click();
        try {
            assertEquals("Неверный email или пароль.", driver.findElement(By.xpath("//div[contains(text(),'Неверный email или пароль')]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    //test2
    @Test
    public void test02Login() throws Exception {
        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_email")).sendKeys("golubcov.e@gmail.com");
        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys("123456");
        driver.findElement(By.name("commit")).click();
        // driver.findElement(By.id("pop_up-close")).click(); // close adverts
        //driver.findElement(By.xpath("//a[contains(text(),'Профиль')]")).click(); //a[contains(@href,'/user/edit_profile')]
        driver.findElement(By.xpath("//a[contains(text(),'Редактировать профиль')]")).click();
        try {
            assertEquals("zeke", driver.findElement(By.id("user_login")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    //test3
    @Test
    public void test03AddAdvert() throws Exception {
        driver.get(baseUrl + "/adverts");
        //driver.findElement(By.xpath("//a[contains(text(),'Мои объявления')]")).click();
        //driver.findElement(By.linkText("Мои объявления")).click(); // not working
        driver.get(baseUrl + "/adverts/new");
        //driver.findElement(By.xpath("//a[contains(text(),'Новое объявление')]")).click();
        //driver.findElement(By.linkText("Новое объявление")).click(); // not working
        driver.findElement(By.id("advert_advert_type_task")).click();
        driver.findElement(By.id("advert_comment")).clear();
        driver.findElement(By.id("advert_comment")).sendKeys("Selenium");
        new Select(driver.findElement(By.id("advert_visible_status"))).selectByVisibleText("Публиковать");
        driver.findElement(By.id("advert_min_subscribers")).clear();
        driver.findElement(By.id("advert_min_subscribers")).sendKeys("10");
        driver.findElement(By.id("advert_max_price")).clear();
        driver.findElement(By.id("advert_max_price")).sendKeys("10");
        new Select(driver.findElement(By.id("advert_run_time_from"))).selectByVisibleText("10:00");
        new Select(driver.findElement(By.id("advert_run_time_to"))).selectByVisibleText("22:00");
        new Select(driver.findElement(By.id("advert_post_time"))).selectByVisibleText("4 часа");
        new Select(driver.findElement(By.id("advert_post_time_on_first_position"))).selectByVisibleText("4 часа");
        driver.findElement(By.name("commit")).click();
    }

    //test4
    @Test
    public void test04DelAdvert() throws Exception {
       driver.get(baseUrl + "/adverts");
       //driver.findElement(By.linkText("Изменить")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Изменить')]")).click();
        /*
        try {

            assertEquals("Selenium", driver.findElement(By.id("advert_comment")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        */
        driver.findElement(By.linkText("Удалить")).click();
    }

    //test5
    @Test
    public void test05DirectDeals() throws Exception {
        driver.get(baseUrl + "/adverts");
       // driver.findElement(By.xpath("(//a[contains(@href, '/adverts')])[3]")).click();
   // driver.findElement(By.xpath("//a[contains(text(),'Подобрать площадку')]")).click();
   // driver.findElement(By.xpath("//a[contains(text(),'Заключить сделку')]")).click();
   // driver.findElement(By.xpath("//a[contains(text(),'Выбрать')]")).click();

        driver.findElement(By.xpath("(//a[contains(@href, '/adverts')])[3]")).click();
        driver.findElement(By.xpath("//a[contains(@href, '/platforms/search?advert_id=163')]")).click();
       // driver.navigate().refresh();
        driver.get(baseUrl + "/platforms/68/deal?advert_id=163");
        driver.get(baseUrl + "/deals/new?advert=163&platform=68&role=adverter");
        //driver.findElement(By.xpath("//a[contains(@href, '/platforms/68/deal?advert_id=163')]")).click();
       // driver.findElement(By.xpath("//a[contains(@href, '/deals/new?advert=163&platform=68&role=adverter')]")).click();
    driver.findElement(By.id("deal_run_date_from")).clear();
    driver.findElement(By.id("deal_run_date_from")).sendKeys("2016-02-01");
    driver.findElement(By.id("deal_run_date_to")).clear();
    driver.findElement(By.id("deal_run_date_to")).sendKeys("2016-02-29");
    new Select(driver.findElement(By.id("deal_run_time_from"))).selectByVisibleText("00:00");
    new Select(driver.findElement(By.id("deal_run_time_to"))).selectByVisibleText("23:30");
    new Select(driver.findElement(By.id("deal_post_time"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("deal_post_time_on_first_position"))).selectByVisibleText("1");
    driver.findElement(By.xpath("//input[@name='commit']")).click();
    try {
      assertEquals("Предложение отправлено", driver.findElement(By.cssSelector("div.div.notice-msg > div.text")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    }

    //test6
    @Test
    public void test06ReturnDeals() throws Exception {
        driver.get(baseUrl + "/adverts/search");
        // driver.findElement(By.xpath("(//a[contains(@href, '/adverts')])[3]")).click();
        // driver.findElement(By.xpath("//a[contains(text(),'Подобрать площадку')]")).click();
        // driver.findElement(By.xpath("//a[contains(text(),'Заключить сделку')]")).click();
        // driver.findElement(By.xpath("//a[contains(text(),'Выбрать')]")).click();

        //driver.findElement(By.xpath("(//a[contains(@href, '/advert/search')])[3]")).click();
        driver.findElement(By.xpath("//a[contains(@href, '/adverts/72/deal')]")).click();
        // driver.navigate().refresh();
        driver.get(baseUrl + "/deals/new?advert=72&platform=29&role=platformer");
        //driver.findElement(By.xpath("//a[contains(@href, '/platforms/68/deal?advert_id=163')]")).click();
        // driver.findElement(By.xpath("//a[contains(@href, '/deals/new?advert=163&platform=68&role=adverter')]")).click();
        driver.findElement(By.id("deal_run_date_from")).clear();
        driver.findElement(By.id("deal_run_date_from")).sendKeys("2016-02-01");
        driver.findElement(By.id("deal_run_date_to")).clear();
        driver.findElement(By.id("deal_run_date_to")).sendKeys("2016-02-29");
        new Select(driver.findElement(By.id("deal_run_time_from"))).selectByVisibleText("00:00");
        new Select(driver.findElement(By.id("deal_run_time_to"))).selectByVisibleText("23:30");
        new Select(driver.findElement(By.id("deal_post_time"))).selectByVisibleText("1");
        new Select(driver.findElement(By.id("deal_post_time_on_first_position"))).selectByVisibleText("1");
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        try {
            assertEquals("Предложение отправлено", driver.findElement(By.cssSelector("div.div.notice-msg > div.text")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    //test7
    @Test
    public void test07AddWalletWebMoney() throws Exception {
        driver.get(baseUrl + "/finances");
        driver.findElement(By.xpath("//div[8]/a/span[2]")).click();
        //driver.findElement(By.id("amount")).clear();
        //driver.findElement(By.id("amount")).sendKeys("100");
        driver.findElement(By.xpath("(//input[@id='amount'])")).clear();
        driver.findElement(By.xpath("(//input[@id='amount'])")).sendKeys("100");
        //new Select(driver.findElement(By.id("payment_method"))).selectByVisibleText("yandex");
        //driver.findElement(By.name("commit")).click();
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        try {
            assertEquals("Пополнение счета пользователя №13", driver.findElement(By.cssSelector("td.pay_param_bb_new")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    //test8
    @Test
    public void test08СonclusionWalletWebMoney() throws Exception {
        driver.get(baseUrl + "/finances");
        driver.findElement(By.xpath("//div[8]/a/span[2]")).click();
        driver.findElement(By.xpath("(//input[@id='amount'])[2]")).clear();
        driver.findElement(By.xpath("(//input[@id='amount'])[2]")).sendKeys("100");
        driver.findElement(By.xpath("(//input[@name='commit'])[2]")).click();
        try {
            assertEquals("Транзакция ожидает подтверждения администратором", driver.findElement(By.cssSelector("div.div.notice-msg > div.text")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }
        //test7
   /* @Test
    public void test7Site() throws Exception {
        driver.get(baseUrl + "/users/13");
        driver.findElement(By.linkText("Мои объявления")).click();
        driver.findElement(By.linkText("Поиск площадок")).click();
        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Подробная статистика")).click();
        driver.findElement(By.linkText("Сделки")).click();
        driver.findElement(By.id("order_DESC")).click();
        driver.findElement(By.id("order_ASC")).click();
        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Заявка на подготовку рекламной кампании")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Мои площадки')])[2]")).click();
        driver.findElement(By.linkText("Изменить")).click();
        driver.findElement(By.linkText("Поиск объявлений")).click();
        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Сделки")).click();
        driver.findElement(By.id("order_ASC")).click();
        driver.findElement(By.name("commit")).click();
       // driver.findElement(By.linkText("Рефералы: 14.71")).click();
        driver.findElement(By.linkText("Пополнить")).click();
        //driver.findElement(By.id("pop_up-close")).click();
        driver.findElement(By.linkText("Вывести")).click();
        driver.findElement(By.linkText("История")).click();
        driver.findElement(By.linkText("Мои площадки")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Профиль')]")).click();
        driver.findElement(By.linkText("Избранное")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Профиль')]")).click();
        driver.findElement(By.linkText("Черный список")).click();
        //driver.findElement(By.linkText("Профиль")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Профиль')]")).click();
        //driver.findElement(By.xpath("//a[contains(text(),'Личный кабинет')]")).click();
        driver.findElement(By.linkText("Личный кабинет")).click(); // not working
        driver.findElement(By.linkText("Изменить email и пароль")).click();

        driver.findElement(By.id("user_current_password")).clear();
        driver.findElement(By.id("user_current_password")).sendKeys("123456");

        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys("123456");

        driver.findElement(By.id("user_password_confirmation")).clear();
        driver.findElement(By.id("user_password_confirmation")).sendKeys("123456");

        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Премиум")).click();
        driver.findElement(By.linkText("Новости")).click();
        // driver.findElement(By.linkText("test")).click();
        driver.findElement(By.linkText("Поддержка")).click();
        driver.findElement(By.linkText("FAQ")).click();
        driver.findElement(By.cssSelector("div.container")).click();
        driver.findElement(By.cssSelector("div.container")).click();
        driver.findElement(By.id("dropdownSubmenu")).click();
    }
**/
    //test9
    @Test
    public void test09Logout() throws Exception {
        driver.get(baseUrl + "/");
        //driver.findElement(By.linkText("Выйти")).click();
        //driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[12]/a/span")).click();
        driver.findElement(By.cssSelector("li.exit > a > span.text")).click();
        try {
            assertEquals("Выход из системы выполнен.", driver.findElement(By.cssSelector("div.text")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }


    //test10
    @Test
    public void test10Registration() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.xpath("//a[contains(text(),'Зарегистрироваться')]")).click();
        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_email")).sendKeys(UUID.randomUUID().toString() + "@post.ru");
        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys("123456");
        driver.findElement(By.id("user_password_confirmation")).clear();
        driver.findElement(By.id("user_password_confirmation")).sendKeys("123456");
        driver.findElement(By.id("user_agreement")).click();
        driver.findElement(By.name("commit")).click();
        assertTrue(isElementPresent(By.cssSelector("div.article")));
        //driver.findElement(By.id("pop_up-close")).click();
        driver.findElement(By.cssSelector("span.glyphicon.glyphicon-remove")).click();
        //driver.findElement(By.xpath("//div[@id='navbar-collapse']/ul/li[12]/a/span")).click();
        //driver.findElement(By.linkText("Выйти")).click();
        driver.findElement(By.cssSelector("li.exit > a > span.text")).click();
        try {
            assertEquals("Выход из системы выполнен.", driver.findElement(By.cssSelector("div.text")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }


    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
