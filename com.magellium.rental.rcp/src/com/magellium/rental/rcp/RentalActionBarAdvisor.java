package com.magellium.rental.rcp;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;

public class RentalActionBarAdvisor extends ActionBarAdvisor {
	private IAction preferencesAction;
	private IAction quitAction;

	public RentalActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		// TODO Auto-generated method stub
		super.fillMenuBar(menuBar);
		
		MenuManager menuManager1 = new MenuManager("New MenuManager");
		menuManager1.setMenuText("File\r\n");
		menuBar.add(menuManager1);
		menuManager1.add(quitAction);
		
		MenuManager menuManager2 = new MenuManager("New MenuManager");
		menuManager2.setMenuText("Window");
		menuBar.add(menuManager2);
		menuManager2.add(preferencesAction);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// TODO Auto-generated method stub
		super.fillCoolBar(coolBar);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		super.makeActions(window);
		{
			preferencesAction = ActionFactory.PREFERENCES.create(window);
			register(preferencesAction);
		}
		{
			quitAction = ActionFactory.QUIT.create(window);
			register(quitAction);
		}
	}

}
