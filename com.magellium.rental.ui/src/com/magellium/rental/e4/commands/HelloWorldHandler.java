package com.magellium.rental.e4.commands;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class HelloWorldHandler {
	
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "Hello World title", "Hello World message");
	}
}
