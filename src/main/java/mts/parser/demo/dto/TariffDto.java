package mts.parser.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TariffDto {
    private String name;
    private String description;
    private String benefits;
    private String internet;
    private String minutes;
    private String tv;
    private String wifi;
    private long price;

    public TariffDto(String name, String description, String benefits, long price) {
        this.name = name;
        this.description = description;
        this.benefits = benefits;
        this.price = price;
    }
}
