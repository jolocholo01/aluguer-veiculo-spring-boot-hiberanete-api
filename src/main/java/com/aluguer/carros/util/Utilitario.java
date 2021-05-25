package com.aluguer.carros.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Utilitario {
	private static DateFormat dataFormatoBanco = new SimpleDateFormat("yyyy-MM-dd");

	public static long calcularDataBanco(Date dtInicial, Date dtFinal){
		return (dtFinal.getTime() - dtInicial.getTime() + 3600000L) / 86400000L;
	}

	public static void main(String[] args) throws Exception {
		Date dtInicial= new Date();
		dtInicial.setDate(20);
		long dias = Utilitario.calcularDataBanco(dtInicial, new Date());
		System.out.println(dias);
	}
}
