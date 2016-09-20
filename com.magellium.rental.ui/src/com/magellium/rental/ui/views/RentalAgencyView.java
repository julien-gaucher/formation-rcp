package com.magellium.rental.ui.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.RentalAgency;

import providers.RentalProvider;

public class RentalAgencyView extends ViewPart {
	
	protected RentalProvider provider = new RentalProvider ();

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		TreeViewer treeViewer = new TreeViewer (parent);
		treeViewer.setContentProvider(provider);
		treeViewer.setLabelProvider(provider);
		
		RentalAgency agencies[] = new RentalAgency[] {RentalCoreActivator.getAgency()};
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() { 
		// TODO Auto-generated method stub

	}

}
