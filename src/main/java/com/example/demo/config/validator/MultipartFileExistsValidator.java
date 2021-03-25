package com.example.demo.config.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileExistsValidator extends AbstractMultipartFileValidator
        implements ConstraintValidator<MultipartFileExists, MultipartFile> {

    @Override
    public void initialize(MultipartFileExists multipartFileExists) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        return isPresent(file);
    }
}