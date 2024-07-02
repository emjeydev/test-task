package dev.emjey.testTask.Validation;


// This file is made by EmJey
// Project: testTask
// FileName: CountOrderValidator.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountOrderValidator implements ConstraintValidator<CountOrder, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value > 0 && value <= 10;
    }
}
