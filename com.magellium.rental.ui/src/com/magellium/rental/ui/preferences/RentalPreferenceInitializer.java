package com.magellium.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.magellium.rental.ui.RentalUIActivator;

public class RentalPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(RentalPreferencePage.FIELD_CUSTOMER, StringConverter.asString(new RGB (255, 0, 0)));
		store.setDefault(RentalPreferencePage.FIELD_RENTAL, StringConverter.asString(new RGB (0, 255, 0)));
		store.setDefault(RentalPreferencePage.FIELD_OBJECTS, StringConverter.asString(new RGB (0, 0, 255)));
	}

}
