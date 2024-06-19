package pl.dmcs.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.dmcs.domain.Pesel;

@Component
public class StringToPeselConverter implements Converter<String, Pesel> {

    @Override
    public Pesel convert(String source) {
        Pesel pesel = new Pesel();
        pesel.setPESEL(source);
        return pesel;
    }
}