package com.magellium.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin {

	// Constants to manage object images in registry. Constant values are path
	// to icons
	public static final String IMG_AGENCY = "icons/Agency.png";
	public static final String IMG_CUSTOMER = "icons/Customers.png";
	public static final String IMG_RENTAL = "icons/Rentals.png";
	public static final String IMG_RENTAL_OBJECT = "icons/RentalObjects.png";
	public static final String IMG_COLLAPSE_ALL = "icons/collapseall.gif";
	public static final String IMG_EXPAND_ALL = "icons/expandall.gif";

	// The plug-in ID
	public static final String PLUGIN_ID = "com.magellium.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;

	private Map<String, PaletteDesc> paletteManager = new HashMap<String, PaletteDesc>();

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
			if (e.getName().equals("view")) {
				System.out.println("Plugin : " + e.getNamespaceIdentifier() + " / Vue : " + e.getAttribute("name"));
			}
		}

		readPalettes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	private void readPalettes() {

		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg.getConfigurationElementsFor("com.magellium.rental.ui.palette")) {

			try {

				PaletteDesc pdesc = new PaletteDesc();
				pdesc.setName(e.getAttribute("name"));
				pdesc.setId(e.getAttribute("id"));

				IColorProvider provider = (IColorProvider) e.createExecutableExtension("paletteClass");
				pdesc.setProvider(provider);

				paletteManager.put(pdesc.getId(), pdesc);

			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(getClass());

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));

	}

	public Map<String, PaletteDesc> getPaletteManager() {
		return paletteManager;
	}

}
