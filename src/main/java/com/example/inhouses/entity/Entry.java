package com.example.inhouses.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="inhouse")
public class Entry implements Serializable{

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="player")
	private String player;
	
	@Column(name="gamenum")
	private String gameNum;
	
	@Column(name="kills")
	private int kills;
	
	@Column(name="deaths")
	private int deaths;
	
	@Column(name="assists")
	private int assists;
	
	@Column(name="win")
	private int win;
	
	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGameNum() {
		return gameNum;
	}

	public void setGameNum(String gameNum) {
		this.gameNum = gameNum;
	}

	public Entry() {
	}

	public Entry(String player, String gameNum, int kills) {
		super();
		this.player = player;
		this.kills = kills;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}



	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	
	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", player=" + player + ", gameNum=" + gameNum + ", kills=" + kills + ", deaths="
				+ deaths + ", assists=" + assists + ", win=" + win + "]";
	}
	
	
}
