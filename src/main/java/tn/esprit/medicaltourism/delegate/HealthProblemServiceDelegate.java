package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.HealthProblem;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.HealthProblemServiceRemote;

public class HealthProblemServiceDelegate {

	private final static String jndiName = "/medicaltourismEJB/HealthProblemService!tn.esprit.medicaltourism.services.HealthProblemServiceRemote";

	private static HealthProblemServiceRemote getProxy() {
		return (HealthProblemServiceRemote) ServiceLocator.getInstance()
				.getProxy(jndiName);

	}

	public static void create(HealthProblem healthProblem) {
		getProxy().create(healthProblem);

	}

	public static HealthProblem find(Integer id) {

		return getProxy().find(id);
	}

	public static void update(HealthProblem healthProblem) {
		getProxy().update(healthProblem);

	}

	public static void delete(HealthProblem healthProblem) {
		getProxy().delete(healthProblem);
	}

	public static List<HealthProblem> findAll() {

		return getProxy().findAll();
	}


}
