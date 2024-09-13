package org.example.taas_gateway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SeleniumTest {

    public static void main(String[] args) throws Exception {
        // Choose the desired tenant: saucelabs, lambdatest, browserstack
        String tenant = "saucelabs"; // Change as needed

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("platformName", "Windows 10");

        // Add service-specific capabilities if required
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "girivardhan_velpula");
        sauceOptions.put("accessKey", "0f69442b-5d5f-4700-9aa5-6c2b649bcf27");
        long currentTime = System.currentTimeMillis();
        sauceOptions.put("build", "interns-project-giri-build" + currentTime);
        sauceOptions.put("name", "Interns Project - Giri - " + currentTime);
        capabilities.setCapability("sauce:options", sauceOptions);

        WebDriver driver = new RemoteWebDriver(
                new URL("http://localhost:9998/wd/hub/" + tenant + "/"),
                capabilities
        );

        // Your test steps
        driver.get("https://simply-travel.testsigma.com/");
        driver.findElement(By.id("login-signup")).click();
        driver.findElement(By.id("login-popUp")).click();
        driver.findElement(By.id("email-address-login")).sendKeys("abcd1234@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("pass1234");
        driver.findElement(By.id("submit")).click();
        String username = driver.findElement(By.xpath("//*[@id=\"login-signup\"]/div/div[1]")).getText();
        boolean jobStatus = (Objects.equals(username, "abcd1234@gmail.com"));
        ((RemoteWebDriver) driver).executeScript("sauce:job-result=" + jobStatus);
        driver.quit();
        System.out.println("Logged in successfully");

        driver.quit();
    }
}
