package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Doctor;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.DoctorServiceRemote;

public class DoctorServiceDelegate {
	private static final String jndiName = "/medicaltourismEJB/DoctorService!tn.esprit.medicaltourism.services.DoctorServiceRemote";
	
	public static DoctorServiceRemote getProxy() {
		return (DoctorServiceRemote) ServiceLocator.getInstance().getProxy(
				jndiName);
	}

	public static void createDoctor(Doctor doctor) {
		getProxy().createDoctor(doctor);
	}

	public static List<Doctor> findAllDoctors() {
		return getProxy().findAllDoctors();

	}

	public static Doctor findDoctor(Integer id) {
		return getProxy().findDoctor(id);
	}

	public static void updateDoctor(Doctor doctor) {

		getProxy().updateDoctor(doctor);
	}

	public static void deleteDoctor(Doctor doctor) {

		getProxy().deleteDoctor(doctor);
	}

	public static List<Doctor>  findDoctorBySpec(String speciality) {
		return getProxy().findDoctorBySpec(speciality);
	}

}
