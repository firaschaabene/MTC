package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Pack;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.PackServiceRemote;



public class PackServiceDelegate {

	private static final String jndiName="/medicaltourismEJB/PackService!tn.esprit.medicaltourism.services.PackServiceRemote" ;

	private static PackServiceRemote getProxy() {	
		return ( PackServiceRemote) ServiceLocator.getInstance().getProxy(jndiName);
	}
	
	public static Pack create(Pack  pack) {
		return getProxy().create( pack);
	}

	public static void update(Pack  pack) {
		 getProxy().update( pack);
	}

	public static  void delete(Integer id) {
		 getProxy().delete(id);
	}

	public  static Pack find(Integer id) {
		return getProxy().find(id);
	}

	public static  Pack findByName(String name) {
		return getProxy().findByName(name);
	}

	public static  List<Pack> findAll() {
		return getProxy().findAll();
	}
	public static  void delete(Pack  pack) {
		 getProxy().delete( pack);
	}

	

	

	


	
	
	

}
