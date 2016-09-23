package com.magellium.rental.ui.cmd.e4;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class CopyCustomer {

	@Execute
	public void execute(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) Customer item) {

		if (item == null) return;
		ImageData image = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_AGENCY)
				.getImageData();

		String copy = ((Customer) item).getDisplayName();

		Clipboard clipboard = new Clipboard(Display.getCurrent());

		String textData = copy;
		String rtfData = "{\\rtf1\\b\\i " + copy + "}";
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		Transfer[] transfers = new Transfer[] { textTransfer, rtfTransfer, ImageTransfer.getInstance(),
				URLTransfer.getInstance() };
		Object[] data = new Object[] { textData, rtfData, image, "http://www.google.fr?q=" + copy };
		clipboard.setContents(data, transfers);
		clipboard.dispose();

	}
	@CanExecute
	public boolean can(@Named(IServiceConstants.ACTIVE_SELECTION) Object item) 
	{ return item instanceof Customer; }
}
