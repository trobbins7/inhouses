package com.example.inhouses.DAO;

import java.util.List;

import com.example.inhouses.entity.Entry;

public interface EntryDAO {
	public List<Entry> findAll();
	
	public Entry findById(int theID);
	
	public void save (Entry theEntry);
	
	public void deleteById(int theId);

	public double getKDA(String player);

	public Object getPlayer(String player);

	public double getWinRate(String player);

	double getAverageKills(String player);

	double getAverageDeaths(String player);

	double getAverageAssists(String player);

	public double getDuoWinRate(String player1, String player2);
}
