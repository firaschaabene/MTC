package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Allergyinformation;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.AllergyinformationServiceRemote;



public class AllergyinformationServiceDelegate {
private final static String jndiName = "/medicaltourismEJB/AllergyinformationService!tn.esprit.medicaltourism.services.AllergyinformationServiceRemote";
	
	private static AllergyinformationServiceRemote getProxy() {
		return (AllergyinformationServiceRemote) ServiceLocator
				.getInstance()
				.getProxy(jndiName);

}

	
	public static Boolean create(Allergyinformation allergyinformation) {
		return getProxy().create(allergyinformation);		
	}

	
	public static Allergyinformation find(Integer id) {
		return getProxy().find(id);
	}

	
	public static void update(Allergyinformation allergyinformation) {
		getProxy().update(allergyinformation);		
	}

	
	public static void delete(Allergyinformation allergyinformation) {
		getProxy().delete(allergyinformation);		
	}

	
	public static List<Allergyinformation> findAll() {
		return getProxy().findAll();
	}
	
	

}
