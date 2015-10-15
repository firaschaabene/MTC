package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Medication;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.MedicationServiceRemote;



public class MedicationServiceDelegate {
	
private final static String jndiName = "/medicaltourismEJB/MedicationService!tn.esprit.medicaltourism.services.MedicationServiceRemote";
	
	private static MedicationServiceRemote getProxy() {
		return (MedicationServiceRemote) ServiceLocator
				.getInstance()
				.getProxy(jndiName);

}

	public static void create(Medication medication) {
		getProxy().create(medication);		
	}

	public static Medication find(Integer id) {
		return getProxy().find(id);
	}

	public static void update(Medication medication) {
		getProxy().update(medication);
	}

	public static void delete(Medication medication) {
		getProxy().delete(medication);
	}

	public static List<Medication> findAll() {
		return getProxy().findAll();
	}


}
