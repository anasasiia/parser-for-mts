package mts.parser.demo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebDriver {
    private org.openqa.selenium.WebDriver webDriver;
    @Value("${target.application.baseUrl}")
    private String url;

    @Value("${target.application.buttonXpath}")
    private String buttonXpath;

    @PostConstruct
    public void init() {
        WebDriverManager.firefoxdriver().setup();
    }

    private void setWebDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/lib/firefox/firefox");
        options.addArguments("-headless");
        webDriver = new FirefoxDriver(options);
    }

    public String getPage() {
        setWebDriver();
        webDriver.get(url);
        WebElement seeMoreBtn = webDriver.findElement(By.xpath(buttonXpath));
        seeMoreBtn.click();
        return webDriver.getPageSource();
    }

    @PreDestroy
    public void destroy() {
        webDriver.close();
    }
}
