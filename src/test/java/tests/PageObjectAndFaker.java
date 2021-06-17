package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;

public class PageObjectAndFaker extends TestBase {

    Faker faker = new Faker(new Locale("pl"));
    RegistrationPage registrationPage = new RegistrationPage();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            state = "NCR",
            city = "Delhi",
            gender = getRandomGender(),
            month = getRandomMonth(),
            year = getRandomYear(),
            day = getRandomDay(),
            hobby = getRandomHobby();

    File photoOfTheCat = new File("src/test/resources/IMG_4209.jpeg");

    @Test
    void successfulSubmitForm() {
        step("Open students registration form", () -> {
                    open("https://demoqa.com/automation-practice-form");
                });
        //fill the form
        step("Fill students registration form", () -> {
        registrationPage.typeFirstName(firstName);
        registrationPage.typeLastName(lastName);
        registrationPage.typeUserEmail(email);
        registrationPage.selectUserGender(gender);
        registrationPage.typeUserPhone(phoneNumber);
        });
        step("Set date", () -> {
        registrationPage.setDateOfBirth(day, month, year);
        });
        step("Set subjects", () -> {
        registrationPage.selectSubject("Co");
        });
        step("Set hobbies", () -> {
        registrationPage.selectHobby(hobby);
        });
        step("Upload image", () -> {
        registrationPage.uploadPicture(photoOfTheCat);
        });
        step("Set address", () -> {
        registrationPage.selectState(state);
        registrationPage.selectCity(city);
        });
        step("Submit form", () -> {
        registrationPage.clickSubmit();
        });
        //check the form
        step("Verify successful form submit", () -> {
        registrationPage.verifyAllFilledCorrect(firstName, lastName, email, gender, phoneNumber,
                day, month, year, "Computer Science", hobby, "IMG_4209.jpeg", state, city);
        });


    }


    }




