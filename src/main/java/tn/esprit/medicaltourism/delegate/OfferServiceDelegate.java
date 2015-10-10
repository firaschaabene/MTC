package tn.esprit.medicaltourism.delegate;

import java.util.List;

import tn.esprit.medicaltourism.domain.Activity;
import tn.esprit.medicaltourism.domain.Offer;
import tn.esprit.medicaltourism.locator.ServiceLocator;
import tn.esprit.medicaltourism.services.ActivityServiceRemote;
import tn.esprit.medicaltourism.services.HotelServiceRemote;
import tn.esprit.medicaltourism.services.OfferServiceRemote;

public class OfferServiceDelegate {

	private static final String jndiName = "/medicaltourismEJB/OfferService!tn.esprit.medicaltourism.services.OfferServiceRemote";

	private static OfferServiceRemote getProxy() {
		return (OfferServiceRemote) ServiceLocator.getInstance().getProxy(
				jndiName);
	}

	public static Offer create(Offer offer) {
	return	getProxy().create(offer);

	}

	public static void delete(Integer id) {

		getProxy().delete(id);
	}

	public static List<Offer> findAll() {

		return getProxy().findAll();
	}

	public static void update(Offer offer) {
		 getProxy().update(offer);
	}

	public static Offer find(Integer id) {
		return getProxy().find(id);
	}

	public static List<Offer> findByName(String name) {
		return getProxy().findByName(name);
	}

}
