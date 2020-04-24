package mybootapp.generation;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import mybootapp.dao.DAOPerson;
import mybootapp.model.Party;
import mybootapp.model.Person;


public class Generation {

	public static String[] prenoms = {"Robert","Paul","Gilbert","Patrick","Gerard","Alfred","Suzie","Marianne","Amanda"};
	public static String[] noms = {"Macron","Depardieu","Vilanni","Bianchinotti","Trump","Melenchon"};
	public static String[] groups = {"Noob ","Slayer ","ILD ","GIG ","IAAA ","IMD ","Mage Noir","Snob","80's","360","Issou","Devil","PGM"};
	
	public void generatePersonsAndGroups(DAOPerson dao) {
		/*Date randomDate = new Date(ThreadLocalRandom.current()
                .nextLong(d1.getTime(), d2.getTime()));*/

		int comptPers = 0, comptGroups = 0;
		ArrayList<Party> formedGroups = new ArrayList<>();
		ArrayList<Person> formedPersons = new ArrayList<>();
		HashSet<String> combinaison = new HashSet<String>();
		for(String n1 : groups) {
			for(String n2 : groups) {
				comptGroups++;
				formedGroups.add(new Party(n1+n2));
			}
		}
		System.out.println("Compteur g : "+comptGroups);
		
		//int oldCombinaisonSize = 0;
		Person p;
		for(String p1 : prenoms) {
			for(String p2 : prenoms) {
				for(String n1 : noms) {
					for(String n2 : noms) {
						comptPers++;
						p = new Person(n1+n2,p1+n2,n1+n2+p1+p2+"@gmail.com",n1+n2);
						p.setWebsite(n1+n2+p1+p2+"@lololol.fr");
						p.setBirthDay(randomDate().getTime());
						p.setPersonParty(formedGroups.get(ThreadLocalRandom.current().nextInt(0,  formedGroups.size())));
						formedPersons.add(p);
						
						/*combinaison.add(n1+n2+p1+p2);
						if(oldCombinaisonSize!=combinaison.size())
							oldCombinaisonSize++;
						else {
							System.err.println("doublon : "+n1+n2+p1+p2);
							System.exit(1);
						}*/
						//System.err.println("GENER :"+n1+n2+p1+p2+"@gmail.com");
					}
				}
			}
		}
		System.out.println("Compteur p : "+comptPers);
		//System.err.println(combinaison.size()+" "+formedPersons.size());
		//System.exit(1);
		
		for(Party part : formedGroups)
			dao.saveParty(part);
		for(Person pers : formedPersons) {
			System.err.println("J'insère "+pers.toString());
			dao.savePerson(pers);
		}
	}
	
	public GregorianCalendar randomDate() {
		GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1900, 2010);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        
        return gc;
	}
	
	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
	/*public static void main(String[] args) {
		Generation gener = new Generation();
		gener.generatePersonsAndGroups();
	}*/
	
}
