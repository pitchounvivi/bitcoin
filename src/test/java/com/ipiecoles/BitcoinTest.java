package com.ipiecoles;

import org.junit.Test;
import org.assertj.core.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BitcoinTest {

    @Test
    public void testGetBitcoin() throws IOException {
        //Given

        //
        Bitcoin bitcoin = new Bitcoin();
        Double amount = 4.0;
        List<String> currencyList = new ArrayList<>();
        currencyList.add("EUR");
        currencyList.add("USD");
        String courtBC = "{\"EUR\":10.00,\"USD\":5.00}";

        BitcoinData bitcoinData = new BitcoinData(amount, currencyList);

        //When

        //Resultat de la request
        BitcoinOutput data = bitcoin.getBitcoin(bitcoinData, courtBC);


        //Then
        Assertions.assertThat(data.getBitcoinAmount()).isEqualTo(4.0);
        Assertions.assertThat(data.getCurrencyEquivalent().get("EUR")).isEqualTo(40.0);
        Assertions.assertThat(data.getCurrencyEquivalent().get("USD")).isEqualTo(20.0);



    }

    @Test
    public void testGetBitcoinOutput() throws IOException {
        //Given
        Bitcoin bitcoin = new Bitcoin();
        Double amount = 4.0;
        List<String> currencyList = new ArrayList<>();
        currencyList.add("EUR");
        currencyList.add("USD");
        currencyList.add("AOA");

        BitcoinData bitcoinData = new BitcoinData(amount, currencyList);

        //When

        //Resultat de la request
        BitcoinOutput data = bitcoin.getBitcoinOutput(bitcoinData);


        //Then

        Assertions.assertThat(data.getBitcoinAmount()).isEqualTo(4.0);
        Assertions.assertThat(data.getCurrencyEquivalent()).containsKeys("EUR", "USD", "AOA");
        Assertions.assertThat(data.getCurrencyEquivalent().get("EUR")).isGreaterThan(0.0);
        Assertions.assertThat(data.getCurrencyEquivalent().get("USD")).isGreaterThan(0.0);
        Assertions.assertThat(data.getCurrencyEquivalent().get("AOA")).isGreaterThan(0.0);


    }

}
