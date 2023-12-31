package mts.parser.demo.utils;

import mts.parser.demo.dto.TariffDto;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseUtils {
    public static void getFeatures(int i, Elements features, TariffDto tariffDto) {
        Elements elements = features.get(i).select("span.feature-description.feature-description__text.feature-description__margin");

        for (Element element : elements) {
            String feature = element.text();
            String[] arg = feature.split(" ");

            switch (arg[1]) {
                case "ГБ" -> tariffDto.setInternet(feature);
                case "ТВ" -> tariffDto.setTv(feature);
                case "минут" -> tariffDto.setMinutes(feature);
                default -> tariffDto.setWifi(feature);
            }
        }
    }

    public static long getPrice(int i, Elements cards) {
        Element priceMain = cards.get(i).select("div.price-main").first();
        String[] priceArg = priceMain.select("span.price-text").text().split(" ");

        if (priceArg.length > 2) {
            return Long.parseLong(priceArg[0] + priceArg[1]);
        }
        return Long.parseLong(priceArg[0]);
    }
}
