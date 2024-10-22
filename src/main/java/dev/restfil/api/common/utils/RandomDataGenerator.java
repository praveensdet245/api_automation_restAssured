package dev.restfil.api.common.utils;


import com.github.javafaker.Faker;

import java.util.Random;
import java.util.UUID;

public class RandomDataGenerator {

    private static final Random random = new Random();
    private static Faker faker = new Faker();

    public static UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    public static String generateRandomFirstName() {
        return faker.name().firstName();
    }

    public static String generateRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String generateRandomEmail() {
        String firstName = generateRandomFirstName().toLowerCase();
        String domains[] = {"@gmail.com","@yahoo.com","@rtzen.ai"};
        return firstName + domains[random.nextInt(domains.length)];
    }

    public static String generateRandomPhoneNumber() {
        int areaCode = 200 + random.nextInt(800);
        int centralOfficeCode = 200 + random.nextInt(800);
        int lineNumber = random.nextInt(10000);
        return String.format("%03d-%03d-%04d", areaCode, centralOfficeCode, lineNumber);
    }

    public static String generateRandomZipCode() {
        int min = 10000;
        int max = 99999;
        int randomZipCode = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomZipCode);
    }
}
