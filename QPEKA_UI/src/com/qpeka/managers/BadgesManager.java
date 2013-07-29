package com.qpeka.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.Badges;

import com.qpeka.db.exceptions.BadgesException;
import com.qpeka.db.handler.BadgesHandler;

public class BadgesManager {

	public static BadgesManager instance = null;
	
	public BadgesManager() {
		super();
	}

	public static BadgesManager getInstance() {
		return (instance == null ? instance = new BadgesManager() : instance);
	}

	// Create badges
	public Badges createBadges(short badgeid, short typeid, String badge,
			short level, int points) {
		Badges badges = Badges.getInstance();
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
		List<Badges> badge = null;
		try {
			badge = BadgesHandler.getInstance().findAll();
		} catch (BadgesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return badge;
	} 

	// @ overloading
	// Reading badges through badgeid or Typeid or Level
	public List<Badges> readBadges(short badgeidentifier, String badgeIdentfierString) {
		List<Badges> badges = null;
		if (badgeIdentfierString.equalsIgnoreCase(Badges.BADGEID)) {
			try {
				badges = BadgesHandler.getInstance().findWhereBadgeidEquals(
						badgeidentifier);
			} catch (BadgesException e) {
				e.printStackTrace();
			}

		} else if (badgeIdentfierString.equalsIgnoreCase(Badges.TYPEID)) {
			try {
				badges = BadgesHandler.getInstance().findWhereTypeidEquals(
						badgeidentifier);
			} catch (BadgesException e) {
				e.printStackTrace();
			}
		}

		else if (badgeIdentfierString.equalsIgnoreCase(Badges.LEVEL)) {
			try {
				badges = BadgesHandler.getInstance().findWhereLevelEquals(
						badgeidentifier);
			} catch (BadgesException e) {
				e.printStackTrace();
			}
		} else {
			return badges;
		}
		return badges;
	}

	// @overloading
	// Reading badges through badges;
	public List<Badges> readBadges(String badge) {
		List<Badges> badges = null;
		try {
			badges = BadgesHandler.getInstance().findWhereBadgeEquals(badge);
		} catch (BadgesException e) {
			e.printStackTrace();
		}
		return badges;
	}

	//@Overloading
	//Reading through badges_points
	public List<Badges> readBadges(int badgesPoints) {
		List<Badges> badge = null;
		try {
			badge = BadgesHandler.getInstance().findWherePointsEquals(
					badgesPoints);
		} catch (BadgesException e) {
			e.printStackTrace();
		}
		return badge;
	}

	// Updating badges;
	public short updateBadges(Map<String, Object> updateBadgeMap) {
		short counter = 0;
		if (updateBadgeMap.get(Badges.BADGEID) != null) {
			List<Badges> existingbadge = null;
			try {
				// Retrieving badges from database based on BadgeId
				existingbadge = BadgesHandler.getInstance()
						.findWhereBadgeidEquals(
								Short.parseShort(updateBadgeMap.get(Badges.BADGEID)
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
					if (updateBadgeMap.get(Badges.TYPEID) != null) {
						badges.setTypeid(Short.parseShort(updateBadgeMap.get(
								Badges.TYPEID).toString()));
					}

					if (updateBadgeMap.get(Badges.BADGE) != null) {
						badges.setBadge(updateBadgeMap.get(Badges.BADGE).toString());
					}

					if (updateBadgeMap.get(Badges.LEVEL) != null) {
						badges.setLevel(Short.parseShort(updateBadgeMap
								.get(Badges.LEVEL).toString()));
					}

					if (updateBadgeMap.get(Badges.POINTS) != null) {
						badges.setPoints(Integer.parseInt(updateBadgeMap.get(
								Badges.POINTS).toString()));
					}

					try {
						counter += BadgesHandler.getInstance().update(
								Short.parseShort(updateBadgeMap.get(Badges.BADGEID)
										.toString()), badges);
					} catch (BadgesException e) {
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
		System.out.println(badgesManager.readBadges((short) 3, "badgeid")); 
		System.out.println(badgesManager.readBadges((short) 2, "level"));
		System.out.println(badgesManager.readBadges("programmer")); 
		System.out.println(badgesManager.readBadges(1000));  
		}

}

