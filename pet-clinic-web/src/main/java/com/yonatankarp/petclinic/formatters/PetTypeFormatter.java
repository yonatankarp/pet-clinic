package com.yonatankarp.petclinic.formatters;

import java.text.ParseException;
import java.util.Locale;
import com.yonatankarp.petclinic.model.PetType;
import com.yonatankarp.petclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 * Instructs Spring MVC on how to parse and print elements of type 'PetType'. Starting
 * from Spring 3.0, Formatters have come as an improvement in comparison to legacy
 * PropertyEditors. See the following links for more details: - The Spring ref doc:
 * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 */
@Component
@RequiredArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Override
    public String print(final PetType petType, final Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(final String text, final Locale locale) throws ParseException {
        final var petType = petTypeService.findByName(text);

        if (petType == null) {
            throw new ParseException("type not found: " + text, 0);
        }

        return petType;
    }
}
