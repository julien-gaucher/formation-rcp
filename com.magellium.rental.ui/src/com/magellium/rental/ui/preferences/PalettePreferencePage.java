package com.magellium.rental.ui.preferences;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.magellium.rental.ui.PaletteDesc;
import com.magellium.rental.ui.RentalUIActivator;

public class PalettePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public final static String FIELD_PALETTE = "FIELD_PALETTE";

	public PalettePreferencePage() {
		super (GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Palette Preference Page");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		
		Map<String, PaletteDesc> palettes = RentalUIActivator.getDefault().getPaletteManager();
		
		String [][] comboValues = new String[palettes.size()][2];
		int i = 0;
		for (PaletteDesc p : palettes.values()) {
			comboValues[i][0] = p.getName();
			comboValues[i][1] = p.getId();
			i++;
		}
		
		addField(new ComboFieldEditor(FIELD_PALETTE, "Palette : ", comboValues, getFieldEditorParent())); 
	}

	
}
