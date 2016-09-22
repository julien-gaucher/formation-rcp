package com.magellium.rental.ui.palettes;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.magellium.rental.ui.RentalUIActivator;
import com.magellium.rental.ui.preferences.RentalPreferencePage;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}
	
	private Color getAColor(String rgbKey) {
		
		ColorRegistry registry = JFaceResources.getColorRegistry();
		
		Color col = registry.get(rgbKey);
		if (col == null) {
			registry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = registry.get(rgbKey);
		}
		return col;
	}

	@Override
	public Color getForeground(Object element) {

		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();

//		if (element instanceof Node) {
//			Node node = (Node) element;
//			if (node.label == Node.CUSTOMERS) {
//				return getAColor (store.getString(RentalPreferencePage.FIELD_CUSTOMER));
//			} else if (node.label == Node.RENTALS) {
//				return getAColor (store.getString(RentalPreferencePage.FIELD_RENTAL));
//			} else if (node.label == Node.OBJECTS) {
//				return getAColor (store.getString(RentalPreferencePage.FIELD_OBJECTS));
//			}
//		}
		
		if (element instanceof Customer) {
			return getAColor (store.getString(RentalPreferencePage.FIELD_CUSTOMER));
		} else if (element instanceof Rental) {
			return getAColor (store.getString(RentalPreferencePage.FIELD_RENTAL));
		} else if (element instanceof RentalObject) {
			return getAColor (store.getString(RentalPreferencePage.FIELD_OBJECTS));
		}

		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return null;// Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
	}

}
