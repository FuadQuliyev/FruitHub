package com.example.fruithub.seeder;


import com.example.fruithub.model.Currency;
import com.example.fruithub.model.Status;
import com.example.fruithub.repository.CurrencyRepository;
import com.example.fruithub.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CurrencySeeder implements CommandLineRunner {

    private final CurrencyRepository currencyRepository;
    private final StatusRepository statusRepository;

    @Override
    public void run(String... args) throws Exception {

        UUID activeStatusId = UUID.fromString("c1598a4f-81c3-4293-b53b-98a61f143263");
        Optional<Status> activeStatus = statusRepository.findById(activeStatusId);

        UUID deactiveStatusId = UUID.fromString("54974a03-74f6-4251-ae63-1cca8b154fd3");
        Optional<Status> deactiveStatus = statusRepository.findById(deactiveStatusId);

        if (!currencyRepository.existsByName("Manat")) {
            Currency currencyManat = Currency.builder()
                    .name("Manat")
                    .abbreviation("MAN")
                    .status(activeStatus.get())
                    .build();
            currencyRepository.save(currencyManat);
        }

        if (!currencyRepository.existsByName("Dollar")) {
            Currency currencyDollar = Currency.builder()
                    .name("Dollar")
                    .abbreviation("USD")
                    .status(activeStatus.get())
                    .build();
            currencyRepository.save(currencyDollar);
        }

        if (!currencyRepository.existsByName("Euro")) {
            Currency currencyEuro = Currency.builder()
                    .name("Euro")
                    .abbreviation("EUR")
                    .status(deactiveStatus.get())
                    .build();
            currencyRepository.save(currencyEuro);
        }
    }
}
