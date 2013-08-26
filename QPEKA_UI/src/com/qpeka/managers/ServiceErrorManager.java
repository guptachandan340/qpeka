package com.qpeka.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.Badges;
import com.qpeka.db.exceptions.BadgesException;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.handler.BadgesHandler;
import com.qpeka.db.handler.ServiceErrorHandler;
import com.qpeka.services.Errors.ServiceError;

public class ServiceErrorManager {

public static ServiceErrorManager instance = null;
	
	public ServiceErrorManager() {
		super();
	}
	public static ServiceErrorManager getInstance() {
		return (instance == null ? instance = new ServiceErrorManager() : instance);
	}

	// Create badges
	public ServiceError createServiceError(int status, String name,
			String message) {
		ServiceError serviceError = ServiceError.getInstance();
		serviceError.setStatus(status);
		serviceError.setName(name);
		serviceError.setMessage(message);
		try {
			ServiceErrorHandler.getInstance().insert(serviceError);
		} catch (QpekaException e) {
			e.printStackTrace();
		}
		return serviceError;
	}

	// Delete Badges
	public boolean deleteServiceError(short errorid) {
		try {
			ServiceErrorHandler.getInstance().delete(errorid);
			return true;
		} catch (QpekaException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//Reading all badges
	public List<ServiceError> readServiceError() {
		List<ServiceError> serviceError = null;
		try {
			serviceError = ServiceErrorHandler.getInstance().findAll();
		} catch (QpekaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serviceError;
	} 

	//@Overloading
	//Reading through name
		public List<ServiceError> readBadges(String name) {
			List<ServiceError> seriveError = null;
			try {
				seriveError = ServiceErrorHandler.getInstance().findWherenameEquals(name);;
			} catch (QpekaException e) {
				e.printStackTrace();
			}
			return seriveError;
		}

	//@Overloading
	//Reading through status
	public List<ServiceError> readBadges(int status) {
		List<ServiceError> seriveError = null;
		try {
			seriveError = ServiceErrorHandler.getInstance().findWhereStatus(status);
		} catch (QpekaException e) {
			e.printStackTrace();
		}
		return seriveError;
	}

	// Updating ServiceError;
	public short updateServiceError(Map<String, Object> updateServiceErrorMap) {
		short counter = 0;
		if (updateServiceErrorMap.get(ServiceError.ERRORID) != null) {
			List<ServiceError> existingServiceError = null;
			try {
				// Retrieving badges from database based on BadgeId
				existingServiceError = ServiceErrorHandler.getInstance()
						.findWhereErroridEquals(
								Short.parseShort(updateServiceErrorMap.get(ServiceError.ERRORID)
										.toString()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (QpekaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingServiceError != null) {
				for (ServiceError serviceError : existingServiceError) {
					if (updateServiceErrorMap.get(ServiceError.STATUS) != null) {
						serviceError.setStatus(Integer.parseInt(updateServiceErrorMap.get(
								ServiceError.STATUS).toString()));
					}

					if (updateServiceErrorMap.get(ServiceError.NAME) != null) {
						serviceError.setName(updateServiceErrorMap.get(ServiceError.NAME).toString());
					}

					if (updateServiceErrorMap.get(ServiceError.MESSAGE) != null) {
						serviceError.setMessage(updateServiceErrorMap
								.get(ServiceError.MESSAGE).toString());
					}

					try {
						counter += ServiceErrorHandler.getInstance().update(
								Short.parseShort(updateServiceErrorMap.get(ServiceError.ERRORID)
										.toString()), serviceError);
					} catch (QpekaException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(ServiceErrorManager.getInstance().deleteServiceError((short) 1));
		System.out.println(ServiceErrorManager.getInstance().createServiceError(215, "bad authentication", "username not found"));
			
		Map<String, Object> update1 = new HashMap<String, Object>();
		update1.put("errorid", 1);
		update1.put("status", 201);
		update1.put("name", "bad request");
		update1.put("message", "bad");
		//System.out.println(ServiceErrorManager.getInstance().updateServiceError(update1)); 
		System.out.println(ServiceErrorManager.getInstance().readBadges(215)); 
		System.out.println(ServiceErrorManager.getInstance().readBadges("bad authentication"));
		}	
}
