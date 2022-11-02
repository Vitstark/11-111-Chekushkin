package util.validators;

import com.example.app.models.Person;
import com.example.app.service.PeopleService;
import lombok.RequiredArgsConstructor;
import util.exceptions.EmailValidationException;

import java.util.Optional;

@RequiredArgsConstructor
public class EmailValidator implements Validator<String> {
    private static final String EMAIL_PATTERN = "\\w+@\\w+\\.[a-z]+";

    private final PeopleService peopleService;
    @Override
    public void validate(String email) throws EmailValidationException {
        if (!email.matches(EMAIL_PATTERN)) {
            throw new EmailValidationException("Email must be correct, example123@domainname.domainzone");
        }

        Optional<Person> person = peopleService.findByEmail(email);

        if (person.isPresent()) {
            throw new EmailValidationException("Person with this email already exists");
        }
    }
}
