package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Consultant;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.ConsultantServiceRemote;

public class ConsultantServiceDelegate {
	private static final String jndiName = "/medicaltourismEJB/ConsultantService!tn.esprit.medicaltourism.services.ConsultantServiceRemote";
	
	public static ConsultantServiceRemote getProxy() {
		return (ConsultantServiceRemote) ServiceLocator.getInstance().getProxy(
				jndiName);
	}

	public static void createConsultant(Consultant consultant) {
		getProxy().createConsultant(consultant);
	}

	public static List<Consultant> findAllConsultants() {
		return getProxy().findAllConsultants();

	}

	public static Consultant findConsultant(Integer id) {
		return getProxy().findConsultant(id);
	}

	public static void updateConsultant(Consultant consultant) {

		getProxy().updateConsultant(consultant);
	}

	public static void deleteConsultant(Consultant consultant) {

		getProxy().deleteConsultant(consultant);
	}
}
