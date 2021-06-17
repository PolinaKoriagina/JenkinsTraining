package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
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
        open(" ");
        //fill the form
        registrationPage.typeFirstName(firstName);
        registrationPage.typeLastName(lastName);
        registrationPage.typeUserEmail(email);
        registrationPage.selectUserGender(gender);
        registrationPage.typeUserPhone(phoneNumber);
        registrationPage.setDateOfBirth(day, month, year);
        registrationPage.selectSubject("Co");
        registrationPage.selectHobby(hobby);
        registrationPage.uploadPicture(photoOfTheCat);
        registrationPage.selectState(state);
        registrationPage.selectCity(city);
        registrationPage.clickSubmit();

        //check the form
        registrationPage.verifyAllFilledCorrect(firstName, lastName, email, gender, phoneNumber,
                day, month, year, "Computer Science", hobby, "IMG_4209.jpeg", state, city);



    }



}
