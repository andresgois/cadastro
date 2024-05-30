package io.github.andregois.cad.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import io.github.andregois.cad.enuns.SexoEnum;

@Converter(autoApply = true)
public class SexoEnumConverter implements AttributeConverter<SexoEnum, String>{

	@Override
	public String convertToDatabaseColumn(SexoEnum sexo) {
		 if (sexo == null) {
	            return null;
	        }
	     return sexo.getCodigo();
	}

	@Override
	public SexoEnum convertToEntityAttribute(String codigo) {
		if (codigo == null) {
            return null;
        }
        return SexoEnum.fromCodigo(codigo);
	}

}
