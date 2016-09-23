package com.magellium.rental.e4.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.magellium.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.RentalAgency;

import providers.RentalProvider;

public class RentalAgencyView {
	
	protected RentalProvider provider = new RentalProvider ();
	private TreeViewer treeViewer;
	
	@Inject
	private ESelectionService selectionService;
	
	@Inject
	private EMenuService menuService;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent) {
		
		treeViewer = new TreeViewer (parent);
		treeViewer.setContentProvider(provider);
		treeViewer.setLabelProvider(provider);
		
		RentalAgency agencies[] = new RentalAgency[] {RentalCoreActivator.getAgency()};
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				selectionService.setSelection(sel.size() == 1 ? sel.getFirstElement() : sel.toArray());
			}
		});
		
		menuService.registerContextMenu(treeViewer.getControl(), "com.magellium.rental.eap.popupmenu.helloWorld");
	}

	@Focus
	public void setFocus() { 
		// TODO Auto-generated method stub

	}
	
	// E34
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		super.init(site);
//		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
//	}
//	
//	@Override
//	public void dispose() {
//		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
//		super.dispose();
//	}
//
//	@Override
//	public void propertyChange(PropertyChangeEvent event) {
//		treeViewer.refresh();
//	}

}
