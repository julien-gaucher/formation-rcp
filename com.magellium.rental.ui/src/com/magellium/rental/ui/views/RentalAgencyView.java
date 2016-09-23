package com.magellium.rental.ui.views;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.magellium.rental.core.RentalCoreActivator;
import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.RentalAgency;

import providers.RentalProvider;

/**
 *  @deprecated
 */
public class RentalAgencyView extends ViewPart implements IPropertyChangeListener {
	
	protected RentalProvider provider = new RentalProvider ();
	private TreeViewer treeViewer;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		treeViewer = new TreeViewer (parent);
		treeViewer.setContentProvider(provider);
		treeViewer.setLabelProvider(provider);
		
		RentalAgency agencies[] = new RentalAgency[] {RentalCoreActivator.getAgency()};
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		getSite().setSelectionProvider(treeViewer);
		
		MenuManager menuManager = new MenuManager ();
		Menu menu = menuManager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, treeViewer);
	}

	@Override
	public void setFocus() { 
		// TODO Auto-generated method stub

	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		treeViewer.refresh();
	}

}
