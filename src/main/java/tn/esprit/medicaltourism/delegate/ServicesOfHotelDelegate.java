package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Hotel;
import tn.esprit.medicaltourism.domain.Service_Hotel;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.ServiceHotelRemote;

public class ServicesOfHotelDelegate {
	private static final String jndiName = "/medicaltourismEJB/ServiceHotelService!tn.esprit.medicaltourism.services.ServiceHotelRemote";

	private static ServiceHotelRemote getProxy() {
		return (ServiceHotelRemote) ServiceLocator.getInstance().getProxy(
				jndiName);
	}
	

	public static void create(Service_Hotel service_hotel) {
	 getProxy().create(service_hotel);
		
	}

	
	public static void update(Service_Hotel service_hotel) {
		getProxy().update(service_hotel);
	}

	
	public static void delete(Integer id) {
		getProxy().delete(id);
		
	}

	

	public static List<Service_Hotel> findAll() {
		
		return getProxy().findAll();
		
	}
	public static  List<Service_Hotel> findByhotel(Hotel hotel) {
		return getProxy().findByhotel(hotel);
	}

	
	

}
	
	


