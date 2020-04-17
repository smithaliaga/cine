package com.teamwork.cineperu.Util;

public class UtilString {

	public static String ofuscarTarjeta(String numeroTarjeta) {
		String retorno = "";
		retorno = "**** ****** " + numeroTarjeta.substring(numeroTarjeta.length() - 4, numeroTarjeta.length());
		return retorno;
	}
}
