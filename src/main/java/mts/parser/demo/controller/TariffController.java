package mts.parser.demo.controller;

import lombok.RequiredArgsConstructor;
import mts.parser.demo.dto.TariffDto;
import mts.parser.demo.service.TariffService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URL;

import static mts.parser.demo.utils.ParseUtils.getFeatures;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TariffController {
    private final TariffService tariffService;
    @GetMapping
    public String getTariffs(Model model) {
        try {
            String url = "https://moskva.mts.ru/personal/mobilnaya-svyaz/tarifi/vse-tarifi/mobile-tv-inet";
            Document page = Jsoup.parse(new URL(url), 3000);
            Element tariffsInfo = page.select("div[class=filters__content-item]").first();
            Elements cards = tariffsInfo.select("div.card.card__wrapper");

            WebDriver driver = new ChromeDriver();
            driver.get(url);
            WebElement seeMoreBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[2]" +
                    "/mts-tariffs-catalog/div[2]/div/div/div/mts-actual-tariffs-catalog/div/div[3]/mts-actual-tariffs" +
                    "/div[1]/mts-actual-tariffs-group/div/button"));
            seeMoreBtn.click();
            Document page1 = Jsoup.parse(driver.getPageSource());
            Elements features = page1.select("div.card-features");

            for (int i = 0; i < 14; i++) {
                String name = cards.get(i).select("div.card-title.card-title__margin").text();
                String description = cards.get(i).select("div.card-description").text();
                String benefits = cards.get(i)
                        .select("span.benefits-description.benefits-description__margin")
                        .text();
                Element priceMain = cards.get(i).select("div.price-main").first();
                long price = Long.parseLong(priceMain.select("span.price-text").text());

                TariffDto tariffDto = new TariffDto(name, description, benefits, price);

                getFeatures(i, features, tariffDto);
                tariffService.createTariff(tariffDto);
            }
        } catch (IOException e) {
            throw new RuntimeException("Что-то пошло не так! Попробуйте еще раз" + e.getLocalizedMessage());
        }

        model.addAttribute("tariffs", tariffService.getTariffs());
        return "tariffs";
    }

    @GetMapping("/stay-with-mts")
    public String activateTariff(Model model) {
        return "success";
    }
}
