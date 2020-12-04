package bank;

import java.util.ArrayList;

import bank.exception.ABAccountAlreadyExistingException;
import bank.exception.ABInexistingAccountException;
import bank.exception.ABNullAccountException;


// V ARRAYLLIST OK

/************************************************************
 * La classe BankAgency permet de g�rer une agence bancaire comme un ensemble de Comptes.<BR>
 * Une BankAgency a un name, une localisation (ville) et g�re un ensemble de Comptes.<BR>
 * Exemple : agence "Agence Caisse Epargne" � "Pibrac".<BR>
 * Chaque Account de l'BankAgency a un num�ro unique (v�rification faite lors de l'ajout d'un Account).<BR>
 * 
 * @author Andr� P�ninou.
 * @version 7.02
 *****************************/

public class BankAgency {
	// Attributs
	private String name, loc;
	private ArrayList<Account> accounts;

	/**
	 * Constructeur param�tr�.<BR>
	 * Permet de cr�er une BankAgency en donnant son name et sa ville.<BR>
	 * L'BankAgency est initialis�e avec 0 comptes.<BR>
	 * 
	 * @param pNomAgence
	 *            Le name de l'BankAgency.
	 * @param pLocAgence
	 *            La ville de localisation de l'BankAgency.
	 */

	public BankAgency(String pNomAgence, String pLocAgence) {
		this.name = pNomAgence;
		this.loc = pLocAgence;
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * Permet de conna�tre le name de l'BankAgency.<BR>
	 * <BR>
	 * 
	 * @return Le name de l'BankAgency.
	 */

	public String getAgencyName() {
		return this.name;
	}

	/**
	 * Permet de conna�tre la localisation de l'BankAgency.<BR>
	 * <BR>
	 * 
	 * @return La ville de localisation de l'BankAgency.
	 */

	public String getAgencyLoc() {
		return this.loc;
	}

	/**
	 * Permet de conna�tre le nombre de Comptes existant dans l'BankAgency.<BR>
	 * <BR>
	 * 
	 * @return Le nombre de comptes de l'BankAgency.
	 */

	public int getNbAccounts() {
		return this.accounts.size();
	}

	/**
	 * Permet de retrouver un Account de l'BankAgency � partir de son num�ro de
	 * compte (unique).<BR>
	 * Renvoie null si le Account de num�ro de compte pNumCompte n'est pas
	 * trouv�.<BR>
	 * <BR>
	 * 
	 * @param pNumCompte
	 *            Le num�ro du Account recherch�.
	 * @see BankAgency#addAccount(Account)
	 * @return Le Account de num�ro pNumCompte, null si le compte n'est pas
	 *         trouv�.
	 */

	public Account getAccount(String pNumCompte) {
		// A titre d'exemple : utilise une m�thode priv�e d�finie en fin de
		// classe
		return this.findAccount(pNumCompte);
	}

	/**
	 * Permet de retrouver les Comptes de l'BankAgency correspondant � un name de
	 * propri�taire donn�.<BR>
	 * Renvoie un tableau du nombre de Comptes ayant pour propri�taire
	 * pNomProprietaire (et contenant les dits Comptes...).<BR>
	 * Si aucun Account n'est trouv�, renvoie un tableau de longueur 0.<BR>
	 * <BR>
	 * 
	 * @param pOwnerName
	 *            Le name du propri�taire de Account dont on veut les Comptes.
	 * @see BankAgency#addAccount(Account)
	 * @return Un tableau des Comptes ayant pour propri�taire pNomProprietaire,
	 *         un tableau de longueur 0 si aucun compte n'est trouv�.
	 */

	public Account[] getAccountsOf(String pOwnerName) {
		/*
		 * M�thode : cr�er une ArrayList de Account temporaire : alTemp. 
		 * Parcourir this.alCptes.
		 * 	Si un compe appartient au proprietaire 
		 * 		=> le garder dans alTemp.
		 * Si alTemp non vide : recopier tous les comptes de alTemp dans
		 * un nouveau tableau qui sera renvoy� 
		 * Sinon cr�er un tableau de 0 �l�ments.
		 */

		Account t[], cTemp;
		ArrayList<Account> alTemp; // ArrayList de Account temporaire
		int nbC, nbCTemp, i;

		t = null;

		// Recherche des comptes de pNomProprietaire dans l'BankAgency
		alTemp = new ArrayList<Account>();
		nbC = this.accounts.size();
		for (i = 0; i < nbC; i++) {
			cTemp = accounts.get(i);
			if (cTemp.getOwner().equals(pOwnerName)) {
				alTemp.add(cTemp);
			}
		}

		// Si on a trouv� des comptes ...
		if (alTemp.size() != 0) {
			// ... on cr�e un tableau et on les recopie
			nbCTemp = alTemp.size();
			t = new Account[nbCTemp];
			for (i = 0; i < nbCTemp; i++) {
				t[i] = alTemp.get(i);
			}
			/*
			 * Ou bien directement, sans les lignes ci-dessus :
			 *		t = alTemp.toArray(new Account[0]); 
			 * cf. doc ArrayList / T[] toArray(T[])
			 */
		} else {
			// ... pas de compte dans un tableau vide
			t = new Account[0];
		}
		return t;
	}

	/*	Autre solution possible : sans ArrayList temporaire
	 	public Account[] getComptesDe (String pNomProprietaire)
		{
			Account t[], cTemp;
			int nbC, nbCCourants, i, j;
			
			t=null;
			
			// On compte les comptes de pNomProprietaire
			nbC = 0;
			nbCCourants = this.vCpt.size();
			for (i=0; i<nbCCourants; i++)
			{
				cTemp = accounts.get(i);
				if (cTemp.getProprietaire().equals(pNomProprietaire)) {
					nbC++;
				}
			}
			
			// Si on a trouv� des comptes ...
			if (nbC != 0)
			{
				// ...  on cr�e un tableau et on les recherche � nouveau
				// pour les recopier
				t = new Account[nbC];
				j=0;
				nbCCourants = this.alCptes.size();
				for (i=0; i<nbCCourants; i++)
				{
					cTemp = accounts.get(i);
					if (cTemp.getProprietaire().equals(pNomProprietaire)) {
						t[j++] = cTemp;
					}
				}
			}
			else {
				t=new Account[0];
			}
	
			return t;
		}
	 */

	/**
	 * Permet d'afficher une BankAgency -<B> SPECIAL TP.</B><BR>
	 * <BR>
	 * Affiche le name de l'BankAgency, sa localisation, le nombre de Comptes,
	 * chaque Account avec son solde.<BR>
	 * <BR>
	 * <B>ATTENTION : M�thode sp�ciale pour les TP.</B><BR>
	 * <BR>
	 */

	public void print() {
		int i, nbC;
		Account cTemp;

		System.out.println();
		System.out.println("Name: " + this.name + " (" + this.loc + ")");
		System.out.println("" + this.accounts.size() + " Accounts:");
		nbC = this.accounts.size();
		for (i = 0; i < nbC; i++) {
			cTemp = accounts.get(i);
			cTemp.print();
			System.out.println("   Balance: " + cTemp.balance());
		}
		System.out.println();
	}

	/**
	 * Permet d'obtenir la repr�sentation String d'une BankAgency.<BR>
	 * <BR>
	 * Donne le name de l'BankAgency, la localisation, le nombre de Comptes.<BR>
	 * <BR>
	 */

	public String toString() {
		return "Agency Name: " + this.name + " (" + this.loc + ") : "+this.accounts.size()+" accounts";
	}
	
	/**
	 * Permet d'ajouter un Account � l'BankAgency.<BR>
	 * Le Account pCompteAAjouter est ajout� � l'BankAgency s'il n'existe pas d�j� un Account
	 * ayant le m�me num�ro de compte que pCompteAAjouter 
	 * (identique en num�ro de compte � pCompteAAjouter.getNumCompte()).<BR>
	 * Si on appelle la m�thode avec null en param�tre, la m�thode l�ve une exception.<BR>
	 * 
	 * @param pAccountToAdd
	 *            Le Account � ajouter � l'BankAgency.
	 * @throws ABNullAccountException  lorsque pCompteAAjouter == null	
	 * @throws ABAccountAlreadyExistingException  lorsque un compte de num�ro pCompteAAjouter.getNumCompte() existe d�j� dans l'BankAgency.
	 * @see BankAgency#removeAccount(String)
	 */

	public void addAccount(Account pAccountToAdd) throws ABNullAccountException, ABAccountAlreadyExistingException {
		Account c;

		// Si null => refus�
		if (pAccountToAdd == null) {
			throw new ABNullAccountException("Errorr adding 'null' Account in Agency " 
					+ this.name + " (" + this.loc + ")");
		}

		// On cherche un Account de m�me num�ro
		c = this.findAccount(pAccountToAdd.getAccountNumber());

		// Si trouv�
		if (c != null) {
			// => refus�
			throw new ABAccountAlreadyExistingException(
					"Error adding existing Account ("
					+ pAccountToAdd.getAccountNumber() + ") in Agency " 
					+ this.name + " (" + this.loc + ")");
		} else {
			// ... on ajoute le Account
			this.accounts.add(pAccountToAdd);

		}
	}

	/**
	 * Permet de supprimer un Account de l'BankAgency, � partir de son num�ro.<BR>
	 * Le Account (unique) de num�ro pNumCompte est retir� de l'BankAgency s'il est
	 * trouv�.<BR>
	 * Une exception est lev�e si le compte n'est pas trouv�.<BR>
	 * 
	 * @param pNumCompte
	 *            Le num�ro du Account � supprimer de l'BankAgency.
	 * @throws ABInexistingAccountException	Lorsque aucun compte de num�ro pNumCompte ne peut �tre trouv�.
	 * @see BankAgency#addAccount(Account)
	 */
	public void removeAccount(String pNumCompte) throws ABInexistingAccountException{
		Account c;

		// On cherche un Account de m�me num�ro
		c = this.findAccount(pNumCompte);

		// Si pas trouv� ...
		if (c == null) {
			// ... erreur
			throw new ABInexistingAccountException(
					"Error suppressing inexisting Account ("
					+ pNumCompte+ ") in Agency " 
					+ this.name + " (" + this.loc + ")");
		} else {
			// ... on enl�ve ce Account
			this.accounts.remove(c);
		}
	}


	/**
	 * M�thode priv�e de recherche d'un compte.
	 * Cherche un compte dans accounts ayant pour num�ro de compte pNC.
	 * Renvoie null si aucun compte n'est trouv�.
	 *
	 * @param      pNC  num�ro dui compte recherch�
	 * @return     l'objet compte (la r�f�rence de) trouv� ou null si non trouv�. 
	 */
	private Account findAccount(String pNC) {
		Account c, cTemp;
		int i, nbC;

		// On parcours l'ArrayList de l'BankAgency
		nbC = this.accounts.size();
		c = null;
		for (i = 0; i < nbC && c == null; i++) {
			cTemp = accounts.get(i);
			// Si un Account a le m�me num�ro que pNC
			if (cTemp.getAccountNumber().equals(pNC)) {
				// ... on le garde
				c = cTemp;
			}
		}
		return c;
	}
}