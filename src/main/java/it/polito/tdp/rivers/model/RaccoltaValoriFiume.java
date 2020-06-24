package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class RaccoltaValoriFiume {
	
	LocalDate primaData;
	LocalDate ultimaData;
	int conta;
	double media;
	
	public RaccoltaValoriFiume(LocalDate primaData, LocalDate ultimaData, int conta, double media) {
		super();
		this.primaData = primaData;
		this.ultimaData = ultimaData;
		this.conta = conta;
		this.media = media;
	}

	public LocalDate getPrimaData() {
		return primaData;
	}

	public void setPrimaData(LocalDate primaData) {
		this.primaData = primaData;
	}

	public LocalDate getUltimaData() {
		return ultimaData;
	}

	public void setUltimaData(LocalDate ultimaData) {
		this.ultimaData = ultimaData;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}
	
	
}
