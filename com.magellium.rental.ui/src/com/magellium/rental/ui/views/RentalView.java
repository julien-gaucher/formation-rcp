package com.magellium.rental.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalView extends ViewPart implements ISelectionListener {
	
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label labelDateFrom = null;
	private Label labelDateTo = null;

	public RentalView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(2, false));
		
		label1 = new Label (infoGroup, SWT.NONE);
		label1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
			
		label2 = new Label (infoGroup, SWT.NONE);
		label2.setText("Loué à : ");
		
		label3 = new Label (infoGroup, SWT.NONE);
		label3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(grpDatesDeLocation, SWT.NONE);
		lblNewLabel.setText("du : ");
		
		labelDateFrom = new Label(grpDatesDeLocation, SWT.NONE);
		labelDateFrom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(grpDatesDeLocation, SWT.NONE);
		lblNewLabel_1.setText("au : ");
		
		labelDateTo = new Label(grpDatesDeLocation, SWT.NONE);
		labelDateTo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
//		setRental (RentalCoreActivator.getAgency().getRentals().get(1));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	protected void setRental (Rental r) {
		label1.setText(r.getRentedObject().getName());
		label3.setText(r.getCustomer().getDisplayName());
		labelDateFrom.setText(r.getStartDate().toString());
		labelDateTo.setText(r.getEndDate().toString());
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();

			if (selected instanceof Rental) {
				setRental ((Rental)selected);
			}
		}
	}

}
