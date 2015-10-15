package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Activity;
import tn.esprit.medicaltourism.domain.Hotel;
import tn.esprit.medicaltourism.domain.Services_Hotel;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.OfferServiceRemote;
import tn.esprit.medicaltourism.services.ServicesHotelRemote;

public class ServicesOfHotelDelegate {
	private static final String jndiName = "/medicaltourismEJB/ServicesHotelService!tn.esprit.medicaltourism.services.ServicesHotelRemote";

	private static ServicesHotelRemote getProxy() {
		return (ServicesHotelRemote) ServiceLocator.getInstance().getProxy(
				jndiName);
	}
	

	public static void create(Services_Hotel service_hotel) {
	 getProxy().create(service_hotel);
		
	}

	
	public static void update(Services_Hotel service_hotel) {
		getProxy().update(service_hotel);
	}

	
	public static void delete(Integer id) {
		getProxy().delete(id);
		
	}

	

	public static List<Services_Hotel> findAll() {
		
		return getProxy().findAll();
		
	}
	public static  List<Services_Hotel> findByhotel(Hotel hotel) {
		return getProxy().findByhotel(hotel);
	}

	
	

}
	
	


