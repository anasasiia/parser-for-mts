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
                case "Гбит/c", "Мбит/c" -> tariffDto.setWifi(feature);
            }
        }
    }
}
