package providers;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

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

		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// return getChildren(element).length > 0;
		return true;
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
	}

}
