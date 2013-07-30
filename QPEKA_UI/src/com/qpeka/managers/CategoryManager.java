package com.qpeka.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.Category;
import com.qpeka.db.exceptions.CategoryException;
import com.qpeka.db.handler.CategoryHandler;


public class CategoryManager {
public static CategoryManager instance= null;



public CategoryManager() {
	super();
}

public static CategoryManager getInstance() {
	return(instance == null ? instance = new CategoryManager() : instance);
}

public Category createCategory(short categoryid,String type,String categoryField,String genres,int points) {
	Category category = Category.getInstance();
	category.setCategoryid(categoryid);
	category.setType(type);
	category.setCategory(categoryField);
	category.setGenre(genres);
	category.setPoints(points);
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

					if (updatecategoryMap.get(Category.GENRE) != null) {
						category.setGenre(updatecategoryMap.get(Category.GENRE)
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

public List<Category> readCategory() {
	List<Category> categories = null;
	try {
		categories = CategoryHandler.getInstance().findAll();
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return categories;
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
	public List<Category> readCategory(String categoryIdentifier,
			String categoryIdentifierString) {
		List<Category> categories = null;
		// Read through Category
		if (categoryIdentifierString.equalsIgnoreCase(Category.CATEGORY)) {
			try {
				categories = CategoryHandler.getInstance()
						.findWhereCategoryEquals(categoryIdentifier);
			} catch (CategoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		// Read Categories through type
		else if(categoryIdentifierString.equalsIgnoreCase(Category.GENRE)){
			try {
				categories = CategoryHandler.getInstance().findWhereTypeEquals(
						categoryIdentifier);
			} catch (CategoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return categories;
		}
		return categories;
	}

	//READ CATEGORY THROUGH POINTS
public List<Category> readCategory(int points) {
	List<Category> categories = null;
	try {
		categories= CategoryHandler.getInstance().findWherePointsEquals(points);
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return categories;
}

/**
	 * @param args
	 */
/*
	public static void main(String[] args) {
		CategoryManager categoryManager = new CategoryManager();
		categoryManager.deleteCategory((short)2);
		categoryManager.createCategory((short)2,"book","horror","Fiction",(short)5000);
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put(Category.CATEGORYID, (short)1);
		updateMap.put(Category.GENRE, "nonfictional");
		categoryManager.updateCategory(updateMap);
		System.out.println(categoryManager.readCategory((short)1));
		System.out.println(categoryManager.readCategory());
		
	}
*/	

}
