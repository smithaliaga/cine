package com.teamwork.cineperu.Util;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

/*CLASE UTILIZADA PARA EL PARSEO DE OBJECTOS A STRING CON EL OBJECT ObjectMapper*/

public class UtilPropertyNamingStrategy extends PropertyNamingStrategy {

	private static final long serialVersionUID = 1L;

	@Override
	public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
		return field.getName();
	}

	@Override
	public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
		return convert(method, defaultName);
	}

	@Override
	public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
		return convert(method, defaultName);
	}

	private String convert(AnnotatedMethod method, String defaultName) {
		Class<?> clazz = method.getDeclaringClass();
		Field[] flds = clazz.getDeclaredFields();
		for (Field fld : flds) {
			if (fld.getName().equalsIgnoreCase(defaultName)) {
				return fld.getName();
			}
		}

		return defaultName;
	}
}
