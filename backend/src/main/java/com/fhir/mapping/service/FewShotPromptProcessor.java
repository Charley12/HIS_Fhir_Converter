package com.fhir.mapping.service;

import org.springframework.stereotype.Service;

@Service
public class FewShotPromptProcessor {

    public String appendExamples(String prompt, String sourceSchema) {
        StringBuilder promptBuilder = new StringBuilder(prompt);

        promptBuilder.append("\n\n### Few-Shot Examples Based on Your Schema ###\n");
        if (sourceSchema != null && sourceSchema.toLowerCase().contains("bp")) {
            promptBuilder.append("Example: Merging separate 'sys_bp' and 'dia_bp' local fields into a single nested FHIR Observation.component object:\n");
            promptBuilder.append("{\n" +
                    "  \"resourceType\": \"Observation\",\n" +
                    "  \"code\": { \"coding\": [ { \"system\": \"http://loinc.org\", \"code\": \"85354-9\", \"display\": \"Blood pressure panel with all children optional\" } ] },\n" +
                    "  \"component\": [\n" +
                    "    { \"code\": { \"coding\": [ { \"system\": \"http://loinc.org\", \"code\": \"8480-6\", \"display\": \"Systolic blood pressure\" } ] }, \"valueQuantity\": { \"value\": 120, \"unit\": \"mmHg\" } },\n" +
                    "    { \"code\": { \"coding\": [ { \"system\": \"http://loinc.org\", \"code\": \"8462-4\", \"display\": \"Diastolic blood pressure\" } ] }, \"valueQuantity\": { \"value\": 80, \"unit\": \"mmHg\" } }\n" +
                    "  ]\n" +
                    "}\n");
        } else {
             promptBuilder.append("Example: Simple Concept Mapping: 'M' -> 'male' in http://hl7.org/fhir/administrative-gender.\n");
        }

        return promptBuilder.toString();
    }
}
