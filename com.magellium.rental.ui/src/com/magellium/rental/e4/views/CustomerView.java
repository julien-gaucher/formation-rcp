package com.magellium.rental.e4.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.adapter.Adapter;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;

public class CustomerView {
	
	private Label label1 = null;

	public CustomerView() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		
		label1 = new Label (parent, SWT.NONE);
		label1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		label1.setText("");
	}
	
	protected void setCustomer (Customer c) {
		label1.setText(c.getDisplayName());
	}

	@Focus
	public void setFocus() {
		// TODO Auto-generated method stub

	}
//	
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		super.init(site);
//		site.getPage().addSelectionListener(this);
//	}
//	
//	@Override
//	public void dispose() {
//		getSite().getPage().removeSelectionListener(this);
//		super.dispose();
//	}
//
//	@Override
//	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//		
//		if (selection.isEmpty()) {
//			return;
//		}
//		
//		if (selection instanceof IStructuredSelection) {
//			
//			Object selected = ((IStructuredSelection) selection).getFirstElement();
//			
//			if (selected == null) {
//				return;
//			}
//
//			if (selected instanceof Customer) {
//				setCustomer((Customer)selected);
//			}
//			
//			else
//			{
//				Customer c = Platform.getAdapterManager().getAdapter(selected, Customer.class);
//				
//				if (c != null) {
//					setCustomer (c);
//				}
//			}
//		}
//	}
	
//	@Inject @Optional
//	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Customer c) {
//		
//		if (c == null) {
//			return;
//		}
//		
//		setCustomer (c);
//	}
	
	@Inject @Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object o, Adapter adapter) {
		
		if (o == null) {
			return;
		}
		
		if (o instanceof Customer) {
			setCustomer ((Customer) o);
		}
		
		else {
			Customer c = adapter.adapt (o, Customer.class);
			if (c != null) {
				setCustomer (c);
			}
		}
	}
}
