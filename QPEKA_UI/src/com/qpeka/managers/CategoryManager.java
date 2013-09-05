package com.qpeka.managers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.util.Hash;
import com.qpeka.db.Category;
import com.qpeka.db.Genre;

import com.qpeka.db.exceptions.CategoryException;
import com.qpeka.db.exceptions.GenreException;

import com.qpeka.db.handler.CategoryHandler;
import com.qpeka.db.handler.GenreHandler;

public class CategoryManager {
	public static CategoryManager instance= null;

	public CategoryManager() {
		super();
	}
	
	public static CategoryManager getInstance() {
		return(instance == null ? instance = new CategoryManager() : instance);
	}

	public Category createCategory(String type,String categoryField) {
	Category category = Category.getInstance();
	category.setType(type);
	category.setCategory(categoryField);
	
	try {
		CategoryHandler.getInstance().insert(category);
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return category;
} 

public boolean deleteCategory(short categoryid) {
	try {
		CategoryHandler.getInstance().delete(categoryid);
		return true;
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}

	public short updateCategory(Map<String, Object> updatecategoryMap) {
		short counter = 0;
		if (updatecategoryMap.get(Category.CATEGORYID) != null) {
			List<Category> existingCategory = null;
			try {
				existingCategory = CategoryHandler.getInstance()
						.findWhereCategoryidEquals(
								Short.parseShort(updatecategoryMap.get(
										Category.CATEGORYID).toString()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CategoryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (existingCategory != null) {
				for (Category category : existingCategory) {
					if (updatecategoryMap.get(Category.CATEGORY) != null) {
						category.setCategory(updatecategoryMap.get(
								Category.CATEGORY).toString());
					}

					if (updatecategoryMap.get(Category.TYPE) != null) {
						category.setType(updatecategoryMap.get(Category.TYPE)
								.toString());
					}

					try {
						counter += CategoryHandler.getInstance().update(
								Short.parseShort(updatecategoryMap.get(
										Category.CATEGORYID).toString()),
								category);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CategoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}

// find all category and genre in the database
public Set<String> readCategory() {
	List<Category> categories = null;
	List<Genre> genries = null;
	Set<String> uniqueCategoryGenre = new HashSet<String>();
	try {
		categories = CategoryHandler.getInstance().findAll();
		genries = GenreHandler.getInstance().findAll();
		
		for(Category category : categories) {
			uniqueCategoryGenre.add(category.getCategory());
		}
		for(Genre genre : genries) {
			uniqueCategoryGenre.add(genre.getGenre());
		}
		
	} catch (GenreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return uniqueCategoryGenre;
}

// read category by type
public Set<String> readCategoryByType(String type) {
	List<Category> categories = null;
	Set<Genre> genries = new HashSet<Genre>();
	Set<String> uniqueCategoryGenre = new HashSet<String>();
	
	try {
		categories = CategoryHandler.getInstance().findWhereTypeEquals(
				type);
		for(Category cat : categories) {
			genries.addAll(GenreHandler.getInstance().findWhereCategoryidEquals(cat.getCategoryid()));
			uniqueCategoryGenre.add(cat.getCategory());
		}
		for(Genre g : genries) {
			uniqueCategoryGenre.add(g.getGenre());
		}
		
	} catch (GenreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return uniqueCategoryGenre;
}

public List<Category> readCategory(short categoryid) {
	List<Category> categories = null;
	try {
		categories = CategoryHandler.getInstance().findWhereCategoryidEquals(categoryid);
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return categories;
}
	
	// READ CATEGORY THROUGH CATEGORY OR TYPE
	public Map<String, Object> readCategory(String categoryIdentifier,
			String categoryIdentifierString) {
		Map<String, Object> categoryMap = new HashMap<String, Object>();
		// Read through Category
		if (categoryIdentifierString.equalsIgnoreCase(Category.CATEGORY)) {
			try {
				categoryMap.put(categoryIdentifier, CategoryHandler.getInstance()
						.findWhereCategoryEquals(categoryIdentifier));
			} catch (CategoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		// Read Categories through type
		else if(categoryIdentifierString.equalsIgnoreCase(Category.TYPE)){
				categoryMap.put(categoryIdentifier, readCategoryByType(categoryIdentifier));
		} else {
			return null;
		}
		return categoryMap;
	}
	

/* Read category through category type */
/*public Map<Short, Map.Entry<String, String>> readCategory(String type, String typeString) {
	List<Category> existingCategory = null;
	try {
		if(typeString.equalsIgnoreCase(Category.TYPE)) {
			existingCategory = CategoryHandler.getInstance().findWhereTypeEquals(type);
		}
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return retrieveCategory(existingCategory);
}
*/
	
//categoryid, category, genre : fully working module
/*public Map<Short, Map.Entry<String, String>> retrieveCategory(
			List<Category> existingCategory) {
	Map<Short, Map.Entry<String, String>> outerMap = new HashMap<Short, Map.Entry<String, String>>();
		Map<String, String> innerMap = new HashMap<String, String>();
		for (Category category : existingCategory) {
			innerMap.put(category.getCategory(), category.getGenre());
		}
		Iterator iterator = innerMap.entrySet().iterator();
		while (iterator.hasNext()) {
			for (Category category : existingCategory) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				outerMap.put(category.getCategoryid(), mapEntry);
				System.out.println(mapEntry);
			}
		}
		return null;
	}*/

//@SuppressWarnings("unchecked")
/*	public Map<Short, Map<String, String>> retrieveCategory(
			List<Category> existingCategory) {
		Map<String, String> categoryGenreMap = new HashMap<String, String>();
		Map<Short, Map<String, String>> categoryIdentifierMap = new HashMap<Short, Map<String, String>>();
		for (Category category : existingCategory) {
			// category.getCategory();
			categoryGenreMap.put(category.getCategory(), category.getGenre());
		}
		for (Category category : existingCategory) {
			Iterator<String, String> it = categoryGenreMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Map<String, String> mapEntry = (Map<String, String>) it
						.next();
				System.out.println(mapEntry.toString());
				categoryIdentifierMap.put(category.getCategoryid(),mapEntry);
			}
		}
		return categoryIdentifierMap;
	}

*/
/*public Map<Short, Map<String, String>> readCategory(String type) {
	//Map<Short, Map<String, String>> retrievedCategory = new HashMap<Short, Map<String,String>>();
	List<Category> existingCategory = null;
	List<Category> readCategory = null;
	try {
		existingCategory = CategoryHandler.getInstance().findWhereTypeEquals(type);
		for(Category category : existingCategory) {
		}
			//categoryGenreMap.put(category.getCategory(),category.getGenre());	
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return retrieveCategory(existingCategory);
	
}

public Map<Short, Map<String, String>> retrieveCategory(List<Category> existingCategory) {
	List<Category> genres = null;
	Map<String, String> categoryGenreMap = new HashMap<String, String>();
	Map<Short, Map<String, String>> categoryIdentifierMap = new HashMap<Short, Map<String, String>>();
	for(Category category : existingCategory) {
		categoryGenreMap.put(category.getCategory(),category.getGenre());	
		categoryIdentifierMap.put(category.getCategoryid(), categoryGenreMap);
	}
	
	return categoryIdentifierMap;
}
*/
	/**
	 * @param argsl
	 */

	public static void main(String[] args) {
		CategoryManager categoryManager = new CategoryManager();
		System.out.println(categoryManager.deleteCategory((short)10));
		//categoryManager.createCategory("book","Non-Fiction");
		
	//Map<String, Object> updateMap = new HashMap<String, Object>();
	//	updateMap.put(Category.CATEGORYID, (short)1);
	//	updateMap.put(Category.GENRE, "nonfictional");
	//	categoryManager.updateCategory(updateMap);
		System.out.println(categoryManager.readCategory("book",Category.TYPE));
		//System.out.println(categoryManager.readCategory((short)1));
		//System.out.println(categoryManager.readCategory());
		//System.out.println(categoryManager.readCategory("book"));
		
	}
	
}
