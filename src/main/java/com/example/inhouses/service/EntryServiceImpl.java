package com.example.inhouses.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inhouses.DAO.EntryDAO;
import com.example.inhouses.entity.Entry;

@Service
public class EntryServiceImpl implements EntryService {

	private EntryDAO entryDAO;
	
	
	public EntryServiceImpl() {
	}

	@Autowired
	public EntryServiceImpl(EntryDAO theEntryDAO) {
		entryDAO=theEntryDAO;
	}
	
	@Override
	@Transactional
	public List<Entry> findAll() {
		// TODO Auto-generated method stub
		return entryDAO.findAll();
	}

	@Override
	@Transactional
	public Entry findById(int theID) {
		// TODO Auto-generated method stub
		return entryDAO.findById(theID);
	}
	
	@Override
	@Transactional
	public double getKills(String player) {
		// TODO Auto-generated method stub
		return entryDAO.getAverageKills(player);
	}
	@Override
	@Transactional
	public double getDeaths(String player) {
		// TODO Auto-generated method stub
		return entryDAO.getAverageDeaths(player);
	}
	@Override
	@Transactional
	public double getAssists(String player) {
		// TODO Auto-generated method stub
		return entryDAO.getAverageAssists(player);
	}
	
	@Override
	@Transactional
	public double findKDA(String player){
		return entryDAO.getKDA(player);
	}
	
	@Override
	@Transactional
	public Object findPlayer(String player){
		return entryDAO.getPlayer(player);
	}
	
	@Override
	@Transactional
	public double findPlayerWinRate(String player){
		return entryDAO.getWinRate(player);
	}

	@Override
	@Transactional
	public double findDuoWinRate(String player1,String player2){
		return entryDAO.getDuoWinRate(player1,player2);
	}

	@Override
	@Transactional
	public void save(Entry theEntry) {
		// TODO Auto-generated method stub
		entryDAO.save(theEntry);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		entryDAO.deleteById(theId);

	}

	

}
