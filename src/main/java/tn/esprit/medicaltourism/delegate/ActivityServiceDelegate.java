package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Activity;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.ActivityServiceRemote;
import tn.esprit.medicaltourism.services.HotelServiceRemote;



public class ActivityServiceDelegate {

	private static final String jndiName="/medicaltourismEJB/ActivityService!tn.esprit.medicaltourism.services.ActivityServiceRemote" ;

	private static ActivityServiceRemote getProxy() {	
		return (ActivityServiceRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}
	
	public Activity create(Activity activity) {
		return getProxy().create(activity);
	}

	public void update(Activity activity) {
		 getProxy().update(activity);
	}

	public void delete(Integer id) {
		 getProxy().delete(id);
	}

	public Activity find(Integer id) {
		return getProxy().find(id);
	}

	public Activity findByName(String name) {
		return getProxy().findByName(name);
	}

	public List<Activity> findAll() {
		return getProxy().findAll();
	}
	

	

	


	
	
	

}
