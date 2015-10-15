package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Activity;
import tn.esprit.medicaltourism.domain.Hotel;
import tn.esprit.medicaltourism.domain.Image;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.ActivityServiceRemote;
import tn.esprit.medicaltourism.services.HotelServiceRemote;
import tn.esprit.medicaltourism.services.ImageServiceRemote;



public class ImageServiceDelegate {

	private static final String jndiName="/medicaltourismEJB/ImageService!tn.esprit.medicaltourism.services.ImageServiceRemote" ;

	private static ImageServiceRemote getProxy() {	
		return (ImageServiceRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}
	
	public static Image create(Image image) {
		return getProxy().create(image);
	}

	public static void update(Image image) {
		 getProxy().update(image);
	}

	public static  void delete(Integer id) {
		 getProxy().delete(id);
	}

	public  static Image find(Integer id) {
		return getProxy().find(id);
	}

	public static  Image findByName(String url) {
		return getProxy().findByUrl(url);
	}

	public static  List<Image> findAll() {
		return getProxy().findAll();
	}
	public static  void delete(Image image) {
		 getProxy().delete( image);
	}
	public static Image findHotelImage(Hotel hotel){
		return getProxy().findHotelImage(hotel);
	}

	

	

	


	
	
	

}
