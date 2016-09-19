package com.magellium.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalView extends ViewPart {
	
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;

	public RentalView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(2, false));
		
		label1 = new Label (infoGroup, SWT.BORDER);
		GridData gd = new GridData ();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;https://github.com/julien-gaucher/formation-rcp.git
		label1.setLayoutData(gd);
		
		label2 = new Label (infoGroup, SWT.BORDER);
		gd = new GridData ();
		gd.horizontalSpan = 1;
		gd.horizontalAlignment = SWT.LEFT;
		label2.setLayoutData(gd);
		label2.setText("Loué à : ");
		
		label3 = new Label (infoGroup, SWT.BORDER);
		gd = new GridData ();
		gd.horizontalSpan = 1;
		gd.horizontalAlignment = SWT.FILL;
		label3.setLayoutData(gd);
		
		setRental (RentalCoreActivator.getAgency().getRentals().get(1));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	protected void setRental (Rental r) {
		label1.setText(r.getRentedObject().getName());
		label3.setText(r.getCustomer().getDisplayName());
	}

}
