package bg.softuni.jsoonexrcise.utils.impl;

import bg.softuni.jsoonexrcise.utils.ValidatorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class ValidatorServiceImpl implements ValidatorService {
    private final Validator validatorService = Validation.buildDefaultValidatorFactory().getValidator();
    @Override
    public <E> boolean isValid(E entity) {
        return this.validatorService.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> validate(E entity) {
        return this.validatorService.validate(entity);
    }
}
