package pl.dmcs.validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.dmcs.domain.AppUser;

import java.util.regex.Pattern;

public class RegisterAppUserValidator implements Validator {

    EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class clazz) {
        return AppUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object arg0, Errors errors) {
        AppUser appUser = (AppUser) arg0;
        ValidationUtils.rejectIfEmpty(errors, "login", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "firstName", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "telephone", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "pesel.PESEL", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "address", "error.field.required");

        if (!StringUtils.hasText(appUser.getRole()) || !Pattern.matches("ROLE_USER|ROLE_ADMIN", appUser.getRole())) {
            errors.rejectValue("role", "error.role.invalid");
        }

        if (errors.getErrorCount() == 0) {
            if (StringUtils.hasText(appUser.getEmail()) && !emailValidator.isValid(appUser.getEmail())) {
                errors.rejectValue("email", "error.email.invalid");
            }
        }
        if (StringUtils.hasText(appUser.getPesel().getPESEL()) && !isValidPesel(appUser.getPesel().getPESEL())) {
            errors.rejectValue("pesel.PESEL", "error.pesel.invalid");
        }
    }

    private boolean isValidPesel(String pesel) {
        //simplest pesel check
        return pesel.matches("\\d{11}");
    }
}

