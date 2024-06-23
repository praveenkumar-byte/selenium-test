package org.example;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running Selenium Scripts");

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-site-isolation-trials");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        System.out.println("Drivers loaded");
        System.out.println("Opening browser");

        driver.get("http://3.111.149.125:8081/contact.html#");

        System.out.println("Application loading");

        // Using explicit waits
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Name']")));
        WebElement phoneNumber = driver.findElement(By.cssSelector("body > div.contact_section.layout_padding > div > div > div > div.col-md-6.padding_left_0 > div > input:nth-child(2)"));
        WebElement emailInput = driver.findElement(By.cssSelector("body > div.contact_section.layout_padding > div > div > div > div.col-md-6.padding_left_0 > div > input:nth-child(3)"));
        WebElement message = driver.findElement(By.id("comment"));
        WebElement btn = driver.findElement(By.cssSelector("body > div.contact_section.layout_padding > div > div > div > div.col-md-6.padding_left_0 > div > div"));

        nameInput.sendKeys("praveen");
        phoneNumber.sendKeys("9490725918");
        emailInput.sendKeys("praveen@gmail.com");
        message.sendKeys("Hi");
        btn.click();

        // Wait for the response element to be visible
        WebElement responseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

        // Check if form submission was successful by looking for a response element
        try {
            String responseText = responseElement.getText();
            System.out.println("Response: " + responseText);

            // Verify the response content
            if (responseText.contains("Email Sent")) {
                System.out.println("Form submission successful");
            } else {
                System.out.println("Form submission failed: Unexpected response");
            }
        } catch (Exception e) {
            System.out.println("Form submission failed: No response element found");
        }

        // Quit the driver
        driver.quit();
    }
}
