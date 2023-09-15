package mts.parser.demo.service;

import mts.parser.demo.dto.TariffDto;
import mts.parser.demo.entity.Tariff;


import java.util.List;

public interface TariffService {
    void createTariff(TariffDto tariffDto);
    List<Tariff> getTariffs();
}
