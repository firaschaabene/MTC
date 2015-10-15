package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Allergyinformation;
import tn.esprit.medicaltourism.domain.HealthProblem;
import tn.esprit.medicaltourism.domain.MedicalRecords;
import tn.esprit.medicaltourism.domain.Medication;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.MedicalRecordsServiceRemote;



public class MedicalRecordsServiceDelegate {
private final static String jndiName = "/medicaltourismEJB/MedicalRecordsService!tn.esprit.medicaltourism.services.MedicalRecordsServiceRemote";
	
	private static MedicalRecordsServiceRemote getProxy() {
		return (MedicalRecordsServiceRemote) ServiceLocator
				.getInstance()
				.getProxy(jndiName);

}

	
	public static void create(MedicalRecords medicalRecords) {
		getProxy().create(medicalRecords);
		
	}

	
	public static MedicalRecords find(Integer id) {
		
		return getProxy().find(id);
	}

	public static void update(MedicalRecords medicalRecords) {
		getProxy().update(medicalRecords);
	}

	
	public static void delete(MedicalRecords medicalRecords) {
		getProxy().delete(medicalRecords);
		
	}

	
	public static List<MedicalRecords> findAll() {
		return getProxy().findAll();
	}
	
	public static List<Allergyinformation> findAllByMr(int id){
		return getProxy().findAllByMr(id);
	}
	public static List<HealthProblem> findHpByMr(int id){
		return getProxy().findHpByMr(id);
	}
	public static List<Medication> findMedByMr(int id){
		return getProxy().findMedByMr(id);
	}
	

}
