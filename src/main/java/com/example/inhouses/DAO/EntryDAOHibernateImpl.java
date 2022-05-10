package com.example.inhouses.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.example.inhouses.entity.Entry;

@Repository
public class EntryDAOHibernateImpl implements EntryDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EntryDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Entry> findAll() {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Entry> theQuery = currentSession.createQuery("from Entry",Entry.class);
		
		//execute query
		List<Entry> entry = theQuery.getResultList();
		
		//return the results
		return entry;
	}

	@Override
	public Entry findById(int theID) {
		Session currentSession = entityManager.unwrap((Session.class));
		
		Entry theEntry = currentSession.get(Entry.class, theID);
		
		return theEntry;
	}
	
	@Override
	public double getAverageKills(String player) {
		Session currentSession = entityManager.unwrap((Session.class));
		
		long kills= (long) currentSession.createQuery("SELECT sum(kills) from Entry where player ='"+player+"'").getSingleResult();
		long total= (long) currentSession.createQuery("SELECT count(id) from Entry where player ='"+player+"'").getSingleResult();

		return (double)kills/total;
	}
	@Override
	public double getAverageDeaths(String player) {
		Session currentSession = entityManager.unwrap((Session.class));
		
		long deaths= (long) currentSession.createQuery("SELECT sum(deaths) from Entry where player ='"+player+"'").getSingleResult();
		long total= (long) currentSession.createQuery("SELECT count(id) from Entry where player ='"+player+"'").getSingleResult();

		return (double)deaths/total;
	}
	@Override
	public double getAverageAssists(String player) {
		Session currentSession = entityManager.unwrap((Session.class));
		
		long assists= (long) currentSession.createQuery("SELECT sum(assists) from Entry where player ='"+player+"'").getSingleResult();
		long total= (long) currentSession.createQuery("SELECT count(id) from Entry where player ='"+player+"'").getSingleResult();

		return (double)assists/total;
	}

	@Override
	public double getKDA(String player) {
		double kills = getAverageKills(player);
		double deaths = getAverageDeaths(player);
		double assists = getAverageAssists(player);

		return (kills+assists)/deaths;
	}
	
	@Override
	public List<Entry> getPlayer(String player) {
		Session currentSession = entityManager.unwrap((Session.class));
		List<Entry> data = currentSession.createQuery("from Entry where player ='"+player+"'",Entry.class).getResultList();
		
		return data;
	}

	@Override
	public double getWinRate(String player) {
		Session currentSession = entityManager.unwrap((Session.class));
		long wins= (long) currentSession.createQuery("SELECT sum(win) from Entry where player ='"+player+"'").getSingleResult();
		long total= (long) currentSession.createQuery("SELECT count(id) from Entry where player ='"+player+"'").getSingleResult();
		
		return (double) wins/total;
	}
	
	@Override
	public double getDuoWinRate(String player1,String player2) {
		//Session currentSession = entityManager.unwrap((Session.class));
//		long wins= (long) currentSession
//				.createQuery("SELECT count(sub1.wins) "
//						+ "from (select sub.Gamenum,count(id) as players, sum(sub.win) as wins from "
//						+ "(select * from Entry where player!='"+player1+"' and player!='"+player2+"') sub group by Gamenum)sub1"
//								+ "where players=8 and wins=3"
//								+ "group by wins").getSingleResult();
		//long total= (long) currentSession.createQuery("select id, gameNum, player, win from Entry where player!='"+player1+"' and player!='"+player2+"'").();
		//long total= (long) currentSession.createQuery("SELECT count(id) from Entry where player ='"+player+"'").getSingleResult();
		
		//get current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);
				
				ProjectionList projectionList = Projections.projectionList(); 
				projectionList.add(Projections.alias(Projections.groupProperty("gameNum"), "players"))
				.add(Projections.sum("win").as("win"));
				//.add(Projections.count("id"));
				
				
				List<Object[]> games = currentSession.createCriteria(Entry.class)
						//.setProjection(projectionList)
						.add(Restrictions.ne("player", player1))
						.add(Restrictions.neOrIsNotNull("player", player2))
						.list();
				
				//create a query
				Query<Entry> theQuery = currentSession.createQuery("from Entry where player!='"+player1+"'",Entry.class);
				
				//execute query
				List<Entry> entry = theQuery.getResultList();

				System.out.println(games.toString());

		
		return 1.0;
		//return (double) wins;
	}
	
	
	@Override
	public void save(Entry theEntry) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theEntry);
		
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Entry where id=:player");
		
		theQuery.setParameter("player",theId);
		
		theQuery.executeUpdate();
		
	}

}
