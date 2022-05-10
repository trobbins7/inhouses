package com.example.inhouses.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inhouses.DAO.EntryDAO;
import com.example.inhouses.entity.Entry;
import com.example.inhouses.service.EntryService;

@RestController
@RequestMapping("/api")
public class EntryRestController {

	private EntryService entryService;
	
	@Autowired
	public EntryRestController(EntryService theEntryService) {
		entryService = theEntryService;
	}
	
	@GetMapping("/entry")
	public List<Entry> findAll() {
		return entryService.findAll();
	}
	
	@GetMapping("/entry/{id}")
	public Entry getEntry(@PathVariable int id) {
		Entry theEntry = entryService.findById(id);
		if(theEntry == null) {
			throw new RuntimeException("Entry id not found - " + id);
		}
		
		return theEntry;
	}
	
	@GetMapping("/entry/player/kills/{player}")
	public double playerKills(@PathVariable String player){
		double kda = entryService.getKills(player);
		return kda;
	}
	@GetMapping("/entry/player/deaths/{player}")
	public double playerDeaths(@PathVariable String player){
		double kda = entryService.getDeaths(player);
		return kda;
	}
	@GetMapping("/entry/player/assists/{player}")
	public double playerAssists(@PathVariable String player){
		double kda = entryService.getAssists(player);
		return kda;
	}
	
	@GetMapping("/entry/player/kda/{player}")
	public double playerKDA(@PathVariable String player){
		double kda = entryService.findKDA(player);
		return kda;
	}
	
	@GetMapping("/entry/players/winrate/{player1}/{player2}")
	public double playersWinRate(@PathVariable String player1,@PathVariable String player2){
		double winRate = entryService.findDuoWinRate(player1,player2);
		return winRate;
	}
	
	@GetMapping("/entry/player/{player}")
	public Object playerGames(@PathVariable String player){
		Object kda = entryService.findPlayer(player);
		return kda;
	}
	
	@GetMapping("entry/player/winrate/{player}")
	public double playerWinRate(@PathVariable String player){
		double winRate = entryService.findPlayerWinRate(player);
		return winRate;
	}
	
	
	@PostMapping("/entry")
	public Entry addEntry(@RequestBody Entry theEntry) {
		theEntry.setPlayer("Hank");
		
		entryService.save(theEntry);
		
		
		return theEntry;
	}

	
	@PutMapping("/entry")
	public Entry updateEntry(@RequestBody Entry theEntry) {
		entryService.save(theEntry);
		
		return theEntry;
	}
	
	@DeleteMapping("entry/{id}")
	public String deleteEntry(@PathVariable int id) {
		Entry theEntry = entryService.findById(id);
		
		if (theEntry == null) {
			throw new RuntimeException("Entry id not found - "+id);
		}
		entryService.deleteById(id);
		return "Deleted Entry ID "+ id;
	}
}
