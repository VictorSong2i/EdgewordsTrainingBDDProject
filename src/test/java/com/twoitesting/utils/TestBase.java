package com.twoitesting.utils;

import com.twoitesting.pompages.shoppingmenus.LoginPOM;
import com.twoitesting.pompages.shoppingmenus.MainMenuPOM;
import com.twoitesting.pompages.shoppingmenus.TaskBarMenuPOM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    public WebDriver driver; //Field in scope for all methods in this test class

    @BeforeEach
    void login(){
        driver = new ChromeDriver(); //Initiate chrome
        driver.get("https://www.edgewordstraining.co.uk/demo-site/"); //Access shop webpage

        //Login
        TaskBarMenuPOM home = new TaskBarMenuPOM(driver);
        home.myAccountClick(); //Access login page

        //Login with details
        LoginPOM login = new LoginPOM(driver);
        login.setUsername("victor.song@2itesting.com")
                .setPassword("Edgewords123456?")
                .submitForm();
    }

    @AfterEach
    void tearDown () throws InterruptedException{
        Thread.sleep(3000); //Sleep 3 seconds
        driver.quit(); //Close Chrome and ChromeDriver
    }
}
