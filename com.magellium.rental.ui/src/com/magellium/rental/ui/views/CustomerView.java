package com.magellium.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;

public class CustomerView extends ViewPart implements ISelectionListener {
	
	private Label label1 = null;

	public CustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		
		label1 = new Label (parent, SWT.NONE);
		label1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		label1.setText("");
	}
	
	protected void setCustomer (Customer c) {
		label1.setText(c.getDisplayName());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

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
		
		if (selection.isEmpty()) {
			return;
		}
		
		if (selection instanceof IStructuredSelection) {
			
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			
			if (selected == null) {
				return;
			}

			if (selected instanceof Customer) {
				setCustomer((Customer)selected);
			}
			
			else
			{
				Customer c = Platform.getAdapterManager().getAdapter(selected, Customer.class);
				
				if (c != null) {
					setCustomer (c);
				}
			}
		}
	}
}
