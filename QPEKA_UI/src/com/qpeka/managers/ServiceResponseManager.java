package com.qpeka.managers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qpeka.db.ServiceResponse;
import com.qpeka.db.exceptions.QpekaException;
import com.qpeka.db.handler.ServiceResponseHandler;

public class ServiceResponseManager {

public static ServiceResponseManager instance = null;
	
	public ServiceResponseManager() {
		super();
	}
	public static ServiceResponseManager getInstance() {
		return (instance == null ? instance = new ServiceResponseManager() : instance);
	}

	// Create badges
	public ServiceResponse createServiceResponse(short errorid, int status, String name,
			String message) {
		ServiceResponse serviceResponse = ServiceResponse.getInstance();
		serviceResponse.setErrorid(errorid);
		serviceResponse.setStatus(status);
		serviceResponse.setName(name);
		serviceResponse.setMessage(message);
		try {
			ServiceResponseHandler.getInstance().insert(serviceResponse);
		} catch (QpekaException e) {
			e.printStackTrace();
		}
		return serviceResponse;
	}

	// Delete Badges
	public boolean deleteServiceResponse(short errorid) {
		try {
			ServiceResponseHandler.getInstance().delete(errorid);
			return true;
		} catch (QpekaException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//Reading all ServiceResponses
	public List<ServiceResponse> readServiceResponse() {
		List<ServiceResponse> serviceResponse = null;
		try {
			serviceResponse = ServiceResponseHandler.getInstance().findAll();
		} catch (QpekaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serviceResponse;
	} 
/*
	//@Overloading
	//Reading through name
		public List<ServiceError> readServiceError(String name) {
			List<ServiceError> seriveError = null;
			try {
				seriveError = ServiceErrorHandler.getInstance().findWherenameEquals(name);;
			} catch (QpekaException e) {
				e.printStackTrace();
			}
			return seriveError;
		}
*/
	//@Overloading
	//Reading through status
	public Map<String, Object> readServiceResponse(int status) {
		List<ServiceResponse> ExistingserviceResponse = null;
		try {
			ExistingserviceResponse = ServiceResponseHandler.getInstance().findWhereStatus(status);
			
		} catch (QpekaException e) {
			e.printStackTrace();
		}
		return retrieveServiceResponse(ExistingserviceResponse);
	}

	private Map<String, Object> retrieveServiceResponse(
			List<ServiceResponse> existingserviceResponse) {
		// TODO Auto-generated method stub
		Map<String, Object> sResponse = new HashMap<String, Object>();
		for(ServiceResponse serviceResponse : existingserviceResponse) {
			sResponse.put(ServiceResponse.STATUS, serviceResponse.getStatus());
			sResponse.put(ServiceResponse.NAME, serviceResponse.getName());
			sResponse.put(ServiceResponse.MESSAGE, serviceResponse.getMessage());
		}
		return sResponse;
	}
	// Updating ServiceError;
	public short updateServiceResponse(Map<String, Object> updateServiceResponseMap) {
		short counter = 0;
		if (updateServiceResponseMap.get(ServiceResponse.ERRORID) != null) {
			List<ServiceResponse> existingServiceResponse = null;
			try {
				// Retrieving badges from database based on BadgeId
				existingServiceResponse = ServiceResponseHandler.getInstance()
						.findWhereErroridEquals(
								Short.parseShort(updateServiceResponseMap.get(ServiceResponse.ERRORID)
										.toString()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (QpekaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingServiceResponse != null) {
				for (ServiceResponse serviceError : existingServiceResponse) {
					if (updateServiceResponseMap.get(ServiceResponse.STATUS) != null) {
						serviceError.setStatus(Integer.parseInt(updateServiceResponseMap.get(
								ServiceResponse.STATUS).toString()));
					}

					if (updateServiceResponseMap.get(ServiceResponse.NAME) != null) {
						serviceError.setName(updateServiceResponseMap.get(ServiceResponse.NAME).toString());
					}

					if (updateServiceResponseMap.get(ServiceResponse.MESSAGE) != null) {
						serviceError.setMessage(updateServiceResponseMap
								.get(ServiceResponse.MESSAGE).toString());
					}

					try {
						counter += ServiceResponseHandler.getInstance().update(
								Short.parseShort(updateServiceResponseMap.get(ServiceResponse.ERRORID)
										.toString()), serviceError);
					} catch (QpekaException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return counter;
	}

	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ServiceResponseManager.getInstance().deleteServiceResponse((short) 1));
		System.out.println(ServiceResponseManager.getInstance().createServiceResponse((short)1,200, "Success", "Success"));
			
		Map<String, Object> update1 = new HashMap<String, Object>();
		update1.put("errorid", 4);
		update1.put("status", 215);
		update1.put("name", "bad request");
		update1.put("message", "bad request");
		System.out.println(ServiceResponseManager.getInstance().updateServiceResponse(update1)); 
		System.out.println(ServiceResponseManager.getInstance().readServiceResponse(215)); 
		}*/
}
