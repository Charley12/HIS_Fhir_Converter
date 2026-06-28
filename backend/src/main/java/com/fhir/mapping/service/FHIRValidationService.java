package com.fhir.mapping.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;
import ca.uhn.fhir.validation.SingleValidationMessage;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FHIRValidationService {

    private final FhirContext fhirContext;
    private final FhirValidator validator;

    public FHIRValidationService() {
        this.fhirContext = FhirContext.forR4();
        this.validator = this.fhirContext.newValidator();
        // Here we would configure validation modules like FhirInstanceValidator for profile validation
    }

    public String validateResource(String jsonPayload) {
        try {
            IBaseResource resource = fhirContext.newJsonParser().parseResource(jsonPayload);
            ValidationResult result = validator.validateWithResult(resource);

            if (result.isSuccessful()) {
                return "Valid FHIR Resource";
            } else {
                return result.getMessages().stream()
                        .map(SingleValidationMessage::getMessage)
                        .collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            return "Parsing error: " + e.getMessage();
        }
    }
}
