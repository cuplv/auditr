import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

//This example illustrates the problem of confidentiality.
public class Confidentiality_example_2 {
	final int N = 72365;
	
	// We here have a list of patients, each of which has a list of prescriptions, which are private information.
	// Please note here: 
	//     Patient is simplified to an Integer.
	//     Prescription is also simplified to an Integer.
	List<List<Integer>> patientList = new ArrayList<List<Integer>>();
	
	public Confidentiality_example_2() {
		List<Integer> prescriptionList1 = new ArrayList<Integer>();
		Random r = new Random();
		for(int i = 0; i < N; i++) {
			prescriptionList1.add(Math.abs(r.nextInt()));
		}
		patientList.add(prescriptionList1);
	}
	
	// We here want to add a new prescription to a given patient
	void addNewPrescription(int patient, int prescription) throws InterruptedException {
		boolean exist = false;
		// Check whether the patient has the specified prescription before in his record
		for(ListIterator<Integer> i = patientList.get(patient).listIterator(); i.hasNext();) {
			if(prescription == i.next()) {
				exist = true;
				// Long execution path, useless code
				int m = patient * 2;
			}
		}
		if(exist == false) {
			patientList.get(patient).add(prescription);
		}
		printPrescription(150);
	}
	
	// Just for debug
	void printPrescription(int patient) {
		System.out.println("	Printing patient" + patient + "'s prescription...");
		for(ListIterator<Integer> i = patientList.get(patient).listIterator(); i.hasNext();)
			System.out.println("	" + i.next());
	}
}
