package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Hotel;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.HotelServiceRemote;



public class HotelServiceDelegate  {

	private static final String jndiName="/medicaltourismEJB/HotelService!tn.esprit.medicaltourism.services.HotelServiceRemote" ;

	private static HotelServiceRemote getProxy() {	
		return (HotelServiceRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}
	

	public static Hotel create(Hotel Hotel) {
		 return getProxy().create(Hotel);

	}



	public static Hotel find(Integer id) {
		return getProxy().findById(id);
	}

	public static void update(Hotel Hotel) {
		getProxy().update(Hotel);
	}

	public static void delete(Hotel Hotel) {
		getProxy().delete(Hotel);
	}

	
	public static void deleteById(Integer id) {
		getProxy().deleteById(id);
	}

	public static List<Hotel> findAll() {
		return getProxy().findAll();
	}



	public List<Hotel> findByStars(Integer Star) {
	return	getProxy().findByStars(Star);
	}


	

	
}
