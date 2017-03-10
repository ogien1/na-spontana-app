package pl.lodz.p.it.naspontanaapp.service;

import pl.lodz.p.it.naspontanaapp.entities.Activity;

/**
 * Zawiera obsługę aktywności
 */
public interface ActivityDetailsManager {

	/**
	 * Pobiera aktywność na podstawie przekazanego id
	 * @param activtyId
	 * @return
	 */
	 Activity getActivity(long activtyId);
}
