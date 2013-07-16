package com.qpeka.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.Badges;

import com.qpeka.db.exceptions.BadgesException;
import com.qpeka.db.handler.BadgesHandler;

public class BadgesManager {

	public static BadgesManager instance = null;
	Badges badges = new Badges();
	List<Badges> badge = null;
	
	public BadgesManager() {
		super();
	}

	public BadgesManager getInstance() {
		return (instance == null ? instance = new BadgesManager() : instance);
	}

	// Create badges
	public Badges createBadges(short badgeid, short typeid, String badge,
			short level, int points) {
		//badges = null;
		badges.setBadgeid(badgeid);
		badges.setTypeid(typeid);
		badges.setBadge(badge);
		badges.setLevel(level);
		badges.setPoints(points);
		try {
			BadgesHandler.getInstance().insert(badges);
		} catch (BadgesException e) {
			e.printStackTrace();
		}
		return badges;
	}

	// Delete Badges
	public boolean deleteBadges(short badgeid) {
		try {
			BadgesHandler.getInstance().delete(badgeid);
			return true;
		} catch (BadgesException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//Reading all badges
	public List<Badges> readBadges() {
		//badge = null;
		try {
			badge = BadgesHandler.getInstance().findAll();
		} catch (BadgesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return badge;
	} 
	
	//@ overloading
    // Reading badges through badgeid or Typeid or Level
	public List<Badges> readBadges(short badgeid_level, String id_level) {
		//badge = null;
		if (id_level.equalsIgnoreCase(Badges.BADGEID)) {
			try {
				badge = BadgesHandler.getInstance().findWhereBadgeidEquals(
						badgeid_level);
			} catch (BadgesException e) {
				e.printStackTrace();
			}

		} else if (id_level.equalsIgnoreCase(Badges.TYPEID)) {
			try {
				badge = BadgesHandler.getInstance()
						.findWhereTypeidEquals(badgeid_level);
			} catch (BadgesException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				badge = BadgesHandler.getInstance()
						.findWhereLevelEquals(badgeid_level);
			} catch (BadgesException e) {
				e.printStackTrace();
			}
		}
		return badge;
	}

	// @overloading
	// Reading badges through badges;
	public List<Badges> readBadges(String badgeField) {
	//	badge = null;
		try {
			badge = BadgesHandler.getInstance().findWhereBadgeEquals(badgeField);
		} catch (BadgesException e) {
			e.printStackTrace();
		}
		return badge;
	}

	//@Overloading
	//Reading through badges_points
	public List<Badges> readBadges(int badges_points) {
		//badge= null;
		try {
			badge = BadgesHandler.getInstance().findWherePointsEquals(
					badges_points);
		} catch (BadgesException e) {
			e.printStackTrace();
		}
		return badge;
	}

	// Updating badges;
	public short updateBadges(Map<String, Object> badge) {
		short counter = 0;
		if (badge.get(Badges.BADGEID) != null) {
			List<Badges> existingbadge = null;
			try {
				// Retrieving badges from database based on BadgeId
				existingbadge = BadgesHandler.getInstance()
						.findWhereBadgeidEquals(
								Short.parseShort(badge.get(Badges.BADGEID)
										.toString()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadgesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingbadge != null) {
				for (Badges badges : existingbadge) {
					if (badge.get(Badges.TYPEID) != null) {
						badges.setTypeid(Short.parseShort(badge.get(
								Badges.TYPEID).toString()));
					}

					if (badge.get(Badges.BADGE) != null) {
						badges.setBadge(badge.get(Badges.BADGE).toString());
					}

					if (badge.get(Badges.LEVEL) != null) {
						badges.setLevel(Short.parseShort(badge
								.get(Badges.LEVEL).toString()));
					}

					if (badge.get(Badges.POINTS) != null) {
						badges.setPoints(Integer.parseInt(badge.get(
								Badges.POINTS).toString()));
					}

					try {
						counter += BadgesHandler.getInstance().update(
								Short.parseShort(badge.get(Badges.BADGEID)
										.toString()), badges);
					} catch (BadgesException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}

	/**
	 * @param args
	 *            badgesManager.updateBadges();
	 */

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BadgesManager badgesManager = new BadgesManager();
		System.out.println(badgesManager.deleteBadges((short) 3));
		System.out.println(badgesManager.createBadges((short) 3, (short) 3,
			"expert", (short) 5, 1000));
		Map<String, Object> update1 = new HashMap<String, Object>();
		update1.put("badgeid", 3);
		update1.put("typeid", 3);
		update1.put("badge", "programmer");
		update1.put("level", 5);
		update1.put("points", 1000);
		System.out.println(badgesManager.updateBadges(update1));
		System.out.println(badgesManager.readBadges()); 
		System.out.println(badgesManager.readBadges((short) 2, "badgeid")); 
		System.out.println(badgesManager.readBadges((short) 4, "typeid"));
		System.out.println(badgesManager.readBadges("programmer")); 
		System.out.println(badgesManager.readBadges(1000));  
		
		
	}
*/
	
}
