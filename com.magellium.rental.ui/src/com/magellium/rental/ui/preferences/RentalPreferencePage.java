package com.magellium.rental.ui.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.magellium.rental.ui.RentalUIActivator;

public class RentalPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public final static String FIELD_CUSTOMER = "FIELD_CUSTOMER";
	public final static String FIELD_RENTAL = "FIELD_RENTAL";
	public final static String FIELD_OBJECTS = "FIELD_OBJECTS";

	public RentalPreferencePage() {
		super (GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental Preference Page");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(FIELD_CUSTOMER, "Customer : ", getFieldEditorParent()));
		addField(new ColorFieldEditor(FIELD_RENTAL, "Rental : ", getFieldEditorParent()));
		addField(new ColorFieldEditor(FIELD_OBJECTS, "Objects : ", getFieldEditorParent()));
	}

	
}
