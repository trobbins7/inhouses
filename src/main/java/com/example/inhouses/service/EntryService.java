package com.example.inhouses.service;

import java.util.List;

import com.example.inhouses.entity.Entry;


public interface EntryService {

	public List<Entry> findAll();
	
	public Entry findById(int theID);
	
	public void save (Entry theEntry);
	
	public void deleteById(int theId);

	public double findKDA(String player);

	Object findPlayer(String player);

	public double findPlayerWinRate(String player);

	public double getKills(String player);

	double getDeaths(String player);

	double getAssists(String player);

	public double findDuoWinRate(String player1, String player2);
}
