package com.qpeka.managers;

import java.util.List;
import java.util.Map;

import com.qpeka.db.Genre;
import com.qpeka.db.exceptions.GenreException;
import com.qpeka.db.handler.GenreHandler;

public class GenreManager {

	public static GenreManager instance= null;
	
	public GenreManager() {
		super();
	}

	public static GenreManager getInstance() {
		return(instance == null ? instance = new GenreManager() : instance);
	}

public Genre createGenre(short categoryid,String genres,int points) {
	Genre genre = Genre.getInstance();
	genre.setCategoryid(categoryid);
	genre.setGenre(genres);
	genre.setPoints(points);
	try {
		GenreHandler.getInstance().insert(genre);
	} catch (GenreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return genre;
} 

public boolean deleteGenre(short genreid) {
	try {
		GenreHandler.getInstance().delete(genreid);
		return true;
	} catch (GenreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

	public short updateGenre(Map<String, Object> updateGenreMap) {
		short counter = 0;
		if (updateGenreMap.get(Genre.GENREID) != null) {
			List<Genre> existingGenre = null;
			try {
				existingGenre = GenreHandler.getInstance()
						.findWhereGenreidEquals(
								Short.parseShort(updateGenreMap.get(
										Genre.GENREID).toString()));
			
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (GenreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (existingGenre != null) {
				for (Genre genre : existingGenre) {
					if (updateGenreMap.get(Genre.CATEGORYID) != null) {
						genre.setCategoryid(Short.parseShort(updateGenreMap
								.get(Genre.CATEGORYID).toString()));
					}
					
					if (updateGenreMap.get(Genre.GENRE) != null) {
						genre.setGenre(updateGenreMap.get(Genre.GENRE)
								.toString());
					}
					
					if (updateGenreMap.get(Genre.POINTS) != null) {
						genre.setPoints(Integer.parseInt(updateGenreMap
								.get(Genre.POINTS).toString()));
					}
					
					try {
						counter += GenreHandler.getInstance().update(
								Short.parseShort(updateGenreMap.get(
										Genre.GENREID).toString()), genre);
				
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (GenreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}

// Read Genre through genreid or categoryid
public List<Genre> readGenre(short id, String genreIdentifier) {
	List<Genre> genries = null;
	try {
		if(genreIdentifier.equalsIgnoreCase(Genre.GENREID)) {
			genries = GenreHandler.getInstance().findWhereGenreidEquals(id);
		} else if(genreIdentifier.equalsIgnoreCase(Genre.CATEGORYID)) {
			genries = GenreHandler.getInstance().findWhereCategoryidEquals(id);
		}
	} catch (GenreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return genries;
}
	
// READ GENRE THROUGH genre
public List<Genre> readGenre(String genre) {
		 List<Genre> genries = null;
		 try {
			 genries = GenreHandler.getInstance().findWhereGenreEquals(genre); 
			} catch (GenreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return genries;
	}

//READ GENRE THROUGH POINTS
public List<Genre> readGenre(int points) {
	List<Genre> genries = null;
	try {
		genries = GenreHandler.getInstance().findWherePointsEquals(points);
	} catch (GenreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return genries;
}

public static void main(String[] args) {
		//categoryManager.deleteCategory((short)5);
		//categoryManager.createCategory("book","Education","Children Learning",0);
//System.out.println(GenreManager.getInstance().deleteGenre((short)1));
		//System.out.println(GenreManager.getInstance().createGenre((short)3,"Adult",0));
	//Map<String, Object> updateMap = new HashMap<String, Object>();
	//	updateMap.put(Category.CATEGORYID, (short)1);
	//	updateMap.put(Category.GENRE, "nonfictional");
	//	categoryManager.updateCategory(updateMap);
		System.out.println(GenreManager.getInstance().readGenre("Adult"));
		//System.out.println(categoryManager.readCategory((short)1));
		//System.out.println(categoryManager.readCategory());
		//System.out.println(categoryManager.readCategory("book"));
		
	}
	
}

