package com.qpeka.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.Category;
import com.qpeka.db.exceptions.CategoryException;
import com.qpeka.db.handler.CategoryHandler;


public class CategoryManager {
public static CategoryManager instance= null;
Category category = new Category();
List<Category> categories = null;

public CategoryManager() {
	super();
}

public CategoryManager getInstance() {
	return(instance == null ? instance = new CategoryManager() : instance);
}

public Category createCategory(short categoryid,String type,String categoryField,String genres,int points) {
	
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


	public short updateCategory(Map<String, Object> updatecategory) {

		short counter = 0;
		if (updatecategory.get(Category.CATEGORYID) != null) {
			List<Category> existingCategory = null;
			try {
				existingCategory = CategoryHandler.getInstance()
						.findWhereCategoryidEquals(
								Short.parseShort(updatecategory.get(
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
					if (updatecategory.get(Category.CATEGORY) != null) {
						category.setCategory(updatecategory.get(
								Category.CATEGORY).toString());
					}

					if (updatecategory.get(Category.TYPE) != null) {
						category.setType(updatecategory.get(Category.TYPE)
								.toString());
					}

					if (updatecategory.get(Category.GENRE) != null) {
						category.setGenre(updatecategory.get(Category.GENRE)
								.toString());
					}
					try {
						counter += CategoryHandler.getInstance().update(
								Short.parseShort(updatecategory.get(
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
	try {
		categories = CategoryHandler.getInstance().findAll();
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return categories;
}

public List<Category> readCategory(short categoryid) {
	try {
		categories = CategoryHandler.getInstance().findWhereCategoryidEquals(categoryid);
	} catch (CategoryException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return categories;
}

	public List<Category> readCategory(String categoryGenrefield,
			String categoryGenreField) {
		// Read through Category
		if (categoryGenreField.equalsIgnoreCase("category")) {
			try {
				categories = CategoryHandler.getInstance()
						.findWhereCategoryEquals(categoryGenrefield);
			} catch (CategoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		// Read Categories through type
		else {
			try {
				categories = CategoryHandler.getInstance().findWhereTypeEquals(
						categoryGenrefield);
			} catch (CategoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categories;
	}

public List<Category> readCategory(int points) {
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
		categoryManager.deleteCategory((short)1);
		categoryManager.createCategory((short)1,"book","inspiring","Fiction",(short)1000);
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("categoryid", (short)1);
		updateMap.put("genre", "nonfictional");
		categoryManager.updateCategory(updateMap);
		System.out.println(categoryManager.readCategory((short)1));
		
	}
	*/

}
