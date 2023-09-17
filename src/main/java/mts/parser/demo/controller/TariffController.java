package mts.parser.demo.controller;

import lombok.RequiredArgsConstructor;
import mts.parser.demo.dto.TariffDto;
import mts.parser.demo.service.TariffService;
import mts.parser.demo.utils.WebDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static mts.parser.demo.utils.ParseUtils.getFeatures;
import static mts.parser.demo.utils.ParseUtils.getPrice;

@Controller
@RequiredArgsConstructor
public class TariffController {
    private final TariffService tariffService;
    private final WebDriver webDriver;
    @Value("${target.application.baseUrl}")
    private String url;

    @GetMapping("/")
    public String getFirstPage() {
        return "tariffs";
    }

    @PostMapping("/")
    public String getTariffs(Model model) {
        Document page = Jsoup.parse(webDriver.getPage());
        Elements cards = page.select("div.card.card__wrapper");
        System.out.println(cards);
        Elements features = page.select("div.card-features");

        for (int i = 0; i < 14; i++) {
            String name = cards.get(i).select("span.card-title__link").text();
            String benefits = cards.get(i)
                    .select("span.benefits-description.benefits-description__margin")
                    .text();
            long price = getPrice(i, cards);

            TariffDto tariffDto = new TariffDto(name, benefits, price);

            getFeatures(i, features, tariffDto);
            tariffService.createTariff(tariffDto);
        }

        model.addAttribute("tariffs", tariffService.getTariffs());
        return "tariffs";
    }

    @GetMapping("/stay-with-mts")
    public String activateTariff(Model model) {
        return "success";
    }
}
