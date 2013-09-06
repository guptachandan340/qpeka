package com.qpeka.db;

import java.io.Serializable;

public class Category implements Serializable {
		
	private static final long serialVersionUID = -2462791095783420219L;
	
		public static final String CATEGORYID = "categoryid";
		public static final String TYPE = "type";
		public static final String CATEGORY = "category";
		
		// These attributes maps to the columns of the category table.
		private short categoryid;
		private String type;
		private String category;
		
		// These attributes represents whether the above attributes has been
		// modified since being read from the database.
		protected boolean categoryidModified = false;
		protected boolean typeModified = false;
		protected boolean categoryModified = false;
		
		public static Category instance = null;
		/*
		 * Constructors
		 */
		public Category() {
			super();
		}

		public Category(short categoryid, String type, String category) {
			super();
			this.categoryid = categoryid;
			this.type = type;
			this.category = category;
			
		}

		public Category(String type, String category) {
			super();
			this.type = type;
			this.category = category;
		}

		public static Category getInstance() {
			return (instance == null ? (instance = new Category()) : instance);
		}
		
		/*
		 * Getters and setters for attributes
		 */
		public short getCategoryid() {
			return categoryid;
		}

		public void setCategoryid(short categoryid) {
			this.categoryid = categoryid;
			this.categoryidModified = true;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
			this.typeModified = true;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
			this.categoryModified = true;
		}

		/*
		 * Getters and setters for attribute modified status
		 */
		public boolean isCategoryidModified() {
			return categoryidModified;
		}

		public void setCategoryidModified(boolean categoryidModified) {
			this.categoryidModified = categoryidModified;
		}

		public boolean isTypeModified() {
			return typeModified;
		}

		public void setTypeModified(boolean typeModified) {
			this.typeModified = typeModified;
		}

		public boolean isCategoryModified() {
			return categoryModified;
		}

		public void setCategoryModified(boolean categoryModified) {
			this.categoryModified = categoryModified;
		}
		
		/**
		 * Method 'equals'
		 * 
		 * @param _other
		 * @return boolean
		 */
		public boolean equals(Object _other)
		{
			if (_other == null) {
				return false;
			}
			
			if (_other == this) {
				return true;
			}
			
			if (!(_other instanceof Category)) {
				return false;
			}
			
			final Category _cast = (Category) _other;
			if (categoryid != _cast.categoryid) {
				return false;
			}
			
			if (categoryidModified != _cast.categoryidModified) {
				return false;
			}
			
			if (type == null ? _cast.type != type : !type.equals( _cast.type )) {
				return false;
			}
			
			if (typeModified != _cast.typeModified) {
				return false;
			}
			
			if (category == null ? _cast.category != category : !category.equals( _cast.category )) {
				return false;
			}
			
			if (categoryModified != _cast.categoryModified) {
				return false;
			}	
			return true;
		}

		/**
		 * Method 'hashCode'
		 * 
		 * @return int
		 */
		public int hashCode()
		{
			int _hashCode = 0;
			_hashCode = 29 * _hashCode + (int) categoryid;
			_hashCode = 29 * _hashCode + (categoryidModified ? 1 : 0);
			if (type != null) {
				_hashCode = 29 * _hashCode + type.hashCode();
			}
			
			_hashCode = 29 * _hashCode + (typeModified ? 1 : 0);
			if (category != null) {
				_hashCode = 29 * _hashCode + category.hashCode();
			}
			
			_hashCode = 29 * _hashCode + (categoryModified ? 1 : 0);
			return _hashCode;
		}

		/**
		 * Method 'toString'
		 * 
		 * @return String
		 */
		public String toString()
		{
			StringBuffer ret = new StringBuffer();
			ret.append( "Category: " );
			ret.append( CATEGORYID + "=" + categoryid );
			ret.append( ", " + TYPE + "=" + type );
			ret.append( ", " + CATEGORY + "=" + category );
			return ret.toString();
		}
}
