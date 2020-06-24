package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.RaccoltaValoriFiume;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	public RaccoltaValoriFiume ottieniValoriFiume(int id) {
		
		final String sql = "SELECT MIN(f.day), MAX(f.day), COUNT(*), AVG(f.flow)\n" + 
				"FROM flow AS f\n" + 
				"WHERE f.river=?";
		
		RaccoltaValoriFiume rac = null;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			if (res.next()) {
				
				LocalDate primaData = res.getDate(1).toLocalDate();
				LocalDate ultimaData = res.getDate(2).toLocalDate();
				int conta = res.getInt(3);
				double media = res.getDouble(4);
				
				rac = new RaccoltaValoriFiume(primaData, ultimaData, conta, media);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rac;
	}

	public List<Double> getAllFlowValuesByRiver(int id) {
		
		final String sql = "SELECT f.flow\n" + 
				"FROM flow AS f\n" + 
				"WHERE f.river=?";
		
		List<Double> listaFlussi = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			while(res.next()) {
				
				Double d = res.getDouble(1);
				listaFlussi.add(d);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return listaFlussi;
	}
}
