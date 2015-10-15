package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Patient;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.PatientServiceRemote;

public class PatientServiceDelegate {

	private static final String jndiName = "/medicaltourismEJB/PatientService!tn.esprit.medicaltourism.services.PatientServiceRemote";
	
	public static PatientServiceRemote getProxy() {
		return (PatientServiceRemote) ServiceLocator.getInstance().getProxy(
				jndiName);
	}

	public static void createPatient(Patient patient) {
		getProxy().createPatient(patient);
	}

	public static List<Patient> findAllPatient() {
		return getProxy().findAllPatient();

	}

	public static Patient findPatient(Integer id) {
		return getProxy().findPatient(id);
	}

	public static void updatePatient(Patient patient) {

		getProxy().updatePatient(patient);
	}

	public static void deletePatient(Patient patient) {
		getProxy().deletePatient(patient);

	}
}
