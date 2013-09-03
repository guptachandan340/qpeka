package com.qpeka.managers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.qpeka.db.exceptions.user.TypeException;

import com.qpeka.db.handler.user.TypeHandler;
import com.qpeka.db.user.profile.Type;
public class TypeManager {
	public static TypeManager instance= null;

    public TypeManager() {
		super();
	}

	public static TypeManager getInstance() {
		return(instance == null ? instance = new TypeManager() : instance);
	}
	
   public Type createType(short typeid,String type, String typename){
	      Type typeobj = Type.getInstance();
          typeobj.setTypeid(typeid);
          typeobj.setType(type);
          typeobj.setTypename(typename);
	   try {
			TypeHandler.getInstance().insert(typeobj);
			
		} catch (TypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return typeobj;
    }
	
	public boolean deleteType(short typeid){
		try {
			TypeHandler.getInstance().delete(typeid);
			return true;
		} catch (TypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public short updateType(Map<String, Object> updateTypeMap) {
		short counter = 0;
		if (updateTypeMap.get(Type.TYPEID) != null) {
			List<Type> existingtype = null;
			try {
				existingtype = TypeHandler.getInstance()
						.findWhereTypeidEquals(
								Short.parseShort(updateTypeMap.get(
										Type.TYPEID).toString()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TypeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (existingtype != null) {
				for (Type type : existingtype) {
					if (updateTypeMap.get(Type.TYPE) != null) {
						type.setType(updateTypeMap.get(Type.TYPE).toString());
					}

					if (updateTypeMap.get(Type.TYPENAME) != null) {
						type.setTypename(updateTypeMap.get(Type.TYPENAME)
								.toString());
					}
					try {
						counter += TypeHandler.getInstance().update(
								Short.parseShort(updateTypeMap.get(
										Type.TYPEID).toString()),
								type);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TypeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}
		return counter;
	}
	
	//read all fields of typeuser 
	public List<Type> readType() {
		List<Type> type= null;
		try {
			type = TypeHandler.getInstance().findAll();
		} catch (TypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
	 
	//read all fields of typeuser using typeid
	
	public Type readType(short typeid) {
		Type type = null;
		try {
			type= TypeHandler.getInstance().findByPrimaryKey(typeid);
		} catch (TypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
	
	public static void main(String[] args) {
	TypeManager tm = TypeManager.getInstance();
	System.out.println(tm.deleteType((short)17));
	System.out.println(tm.createType((short)17, "service prvider" , "Publisher"));
	Map<String, Object> updateMap = new HashMap<String, Object>();
	updateMap.put(Type.TYPEID, (short)14);
	updateMap.put(Type.TYPE, "printers");
	System.out.println(tm.updateType(updateMap));
	List<Type> type = tm.readType();
	System.out.print(type); 
	
	}

}
