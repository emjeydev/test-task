package dev.emjey.testTask.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// This file is made by EmJey
// Project: testTask
// FileName: CountOrder.java
// Date: 2024/07/02
// Modified Date: 2024/07/02
// Email: emjeydev@gmail.com
// Github: emjeydev


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountOrderValidator.class)
public @interface CountOrder {
    String message() default "Invalid Data. The count must be between 1 and 10";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
