package mts.parser.demo.service;

import lombok.RequiredArgsConstructor;
import mts.parser.demo.dto.TariffDto;
import mts.parser.demo.entity.Tariff;
import mts.parser.demo.repository.TariffRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;


    @Override
    public void createTariff(TariffDto tariffDto) {
        Tariff tariff = new Tariff();
        tariff.setName(tariffDto.getName());
        tariff.setBenefits(tariffDto.getBenefits());
        tariff.setInternet(tariffDto.getInternet());
        tariff.setMinutes(tariffDto.getMinutes());
        tariff.setTv(tariffDto.getTv());
        tariff.setWifi(tariffDto.getWifi());
        tariff.setPrice(tariffDto.getPrice());
        tariffRepository.save(tariff);
    }

    @Override
    public List<Tariff> getTariffs() {
        return tariffRepository.findAll();
    }


}
