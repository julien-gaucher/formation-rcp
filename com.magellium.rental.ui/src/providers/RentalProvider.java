package providers;

import java.text.SimpleDateFormat;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;

import com.magellium.rental.ui.RentalUIActivator;
import com.magellium.rental.ui.preferences.RentalPreferencePage;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		}

		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency) {
			RentalAgency agency = (RentalAgency) parentElement;
			return new Node[] { new Node(Node.CUSTOMERS, agency), new Node(Node.RENTALS, agency),
					new Node(Node.OBJECTS, agency) };
		}

		else if (parentElement instanceof Node) {
			Node node = (Node) parentElement;
			if (node.label == Node.CUSTOMERS) {
				return node.agency.getCustomers().toArray();
			} else if (node.label == Node.RENTALS) {
				return node.agency.getRentals().toArray();
			} else if (node.label == Node.OBJECTS) {
				return node.agency.getObjectsToRent().toArray();
			}
		}

		// ciomment
		return new Object[0];
	}
	
	private Color getAColor(String rgbKey) {
		
		ColorRegistry registry = JFaceResources.getColorRegistry();
		
		Color col = registry.get(rgbKey);
		if (col == null) {
			registry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = registry.get(rgbKey);
		}
		return col;
	}

	@Override
	public Color getForeground(Object element) {

		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();

//		if (element instanceof Node) {
//			Node node = (Node) element;
//			if (node.label == Node.CUSTOMERS) {
//				return getAColor (store.getString(RentalPreferencePage.FIELD_CUSTOMER));
//			} else if (node.label == Node.RENTALS) {
//				return getAColor (store.getString(RentalPreferencePage.FIELD_RENTAL));
//			} else if (node.label == Node.OBJECTS) {
//				return getAColor (store.getString(RentalPreferencePage.FIELD_OBJECTS));
//			}
//		}
		
		if (element instanceof Customer) {
			return getAColor (store.getString(RentalPreferencePage.FIELD_CUSTOMER));
		} else if (element instanceof Rental) {
			return getAColor (store.getString(RentalPreferencePage.FIELD_RENTAL));
		} else if (element instanceof RentalObject) {
			return getAColor (store.getString(RentalPreferencePage.FIELD_OBJECTS));
		}

		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return null;// Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof RentalAgency) || (element instanceof Node);
	}

	@Override
	public String getText(Object element) {

		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		}

		else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}

		else if (element instanceof Rental) {
			Rental rental = (Rental) element;
			return getText (rental.getRentedObject()) + " / " + getText (rental.getCustomer()) + " / "
					+ dateFormat.format(rental.getStartDate()) + " / " + dateFormat.format(rental.getEndDate());
		}

		else if (element instanceof RentalObject) {
			return ((RentalObject)element).getName();
		}

		return super.getText(element);
	}

	private class Node {

		public static final String CUSTOMERS = "Customers";
		public static final String RENTALS = "Locationss";
		public static final String OBJECTS = "Objets à louer";

		protected String label = "";
		protected RentalAgency agency = null;

		public Node(String label, RentalAgency agency) {
			this.label = label;
			this.agency = agency;
		}

		@Override
		public String toString() {
			return label;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}
	}

}
