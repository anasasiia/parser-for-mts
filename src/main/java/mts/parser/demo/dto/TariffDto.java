package mts.parser.demo.dto;

import lombok.Data;

@Data
public class TariffDto {
    private String name;
    private String benefits;
    private String internet;
    private String minutes;
    private String tv;
    private String wifi;
    private long price;

    public TariffDto(String name, String benefits, long price) {
        this.name = name;
        this.benefits = benefits;
        this.price = price;
    }
}
