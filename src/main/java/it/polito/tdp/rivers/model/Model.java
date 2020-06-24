package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao;
	
	

	public void setDao(RiversDAO dao) {
		this.dao = dao;
	}
	
	public List<River> getAllRivers() {
		
		return dao.getAllRivers();
	}

	public RaccoltaValoriFiume ottieniValoriFiume(int id) {
		
		return dao.ottieniValoriFiume(id);
	}

	public String simula(int id, double fMed, double k, int numGiorni) {
		
		fMed = fMed*86400; //conversione DA m cubi al sec A m cubi al giorno
		double q = k*fMed*30;
		double c=q/2;
		double fOutMin = 0.8*fMed;
		
		List<Double> listaValoriFlussi = dao.getAllFlowValuesByRiver(id);
		
		int contaGiorniInsuff = 0;
		
		double sommatoriaC = 0.0;
		double cMed = 0.0;
		
		for (Double d : listaValoriFlussi) {
			
			double fIn = d*86400;
			double fOut;
			if(Math.random()<0.05) {
				
				fOut = 10*fOutMin;
				System.out.println("Irrigazione");
			}
			else {
				
				fOut = fOutMin;
			}
			
			System.out.println(c+" +"+fIn+" -"+fOut);
			c = c + fIn - fOut;
			
			if(c<0) {
				
				c=0;
				contaGiorniInsuff++;
				System.out.println("Giornata Insufficiente");
			}
			
			if(c>q) {
				
				c=q;
			}
			
			sommatoriaC += c;
		}
		
		cMed = sommatoriaC/numGiorni;
		
		String result = "Numero giorni in cui non si è potuta erogare la quantità minima: "+contaGiorniInsuff+"\nCapienza media: " +cMed;
		
		return result;
	}

}
