package com.magellium.rental.ui.cmd;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class CopyCustomer extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection isel = (IStructuredSelection) currentSelection;
			for (Iterator<?> it = isel.iterator(); it.hasNext(); ) {
				
				Object item = it.next();
				
				System.out.println("Objet sélectionné : " + item);			
				
				if (item instanceof Customer) {
					
					ImageData image = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_AGENCY).getImageData();
					
					String copy = ((Customer)item).getDisplayName();
				
					Clipboard clipboard = new Clipboard (Display.getCurrent());
					
					String textData = copy;
					String rtfData = "{\\rtf1\\b\\i " + copy + "}";
					TextTransfer textTransfer = TextTransfer.getInstance();
					RTFTransfer rtfTransfer = RTFTransfer.getInstance();
					Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer, ImageTransfer.getInstance(), URLTransfer.getInstance()};
					Object[] data = new Object[]{textData, rtfData, image, "http://www.google.fr?q=" + copy};
					clipboard.setContents(data, transfers);
					clipboard.dispose();
				}
			}
		}
		
		return null;

	}
}
