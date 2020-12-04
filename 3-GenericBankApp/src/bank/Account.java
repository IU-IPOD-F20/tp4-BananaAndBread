package bank;

import bank.exception.AccountException;


/************************************************************
 * Classe Account permettant de g�rer des comptes simples.<BR>
 * Un Account a un num�ro, appartient � un propri�taire et on peut y faire des
 * versements (cr�dits) et des retraits (d�bits).<BR>
 * Seuls des contr�les de montants des op�rations >=0 sont r�alis�s.<BR>
 * 
 * @author Andr� P�ninou.
 * @version 3.01
 *****************************/

// Classe des comptes standards

public class Account extends Object
// Si cet h�ritage n'est pas pr�cis�, il est implicite
{
	/*********************************
	 * Partie priv�e d'impl�mentation
	 **********************************/

	// Attributs

	// Nom du propri�taire
	private String owner;

	// Numero de Account
	private String accountNumber;

	// Attributs debit et credit
	// Permettent de comptabiliser depots et retraits
	private double debits, credits;

	// Nb Op�rations
	private int nbDebits, nbCredits;

	// Constructeurs

	/**
	 * Constructeur non param�tr�, appel� aussi constructeur "par d�faut".<BR>
	 * <BR>
	 * Permet de cr�er un Account.<BR>
	 * Les cr�dits et d�bits sont initialis�s � 0. le num�ro est initialis� �
	 * "Pas de num�ro", le propri�taire � "Pas de propri�taire"<BR>
	 * <BR>
	 * 
	 * @see Account#Compte(String, String)
	 */
	public Account() {
		this("No number", "No owner");
	}

	/**
	 * Constructeur param�tr�.<BR>
	 * <BR>
	 * Permet de cr�er un Account en donnant son num�ro et son propri�taire.<BR>
	 * Les cr�dits et d�bits sont initialis�s � 0.<BR>
	 * <BR>
	 * 
	 * @param pAccountNumber
	 *            Le num�ro du Account.
	 * @param pOwnerName
	 *            Le nom du propri�taire.
	 */
	public Account(String pAccountNumber, String pOwnerName) {
		this.accountNumber = pAccountNumber;
		this.owner = pOwnerName;
		this.debits = 0;
		this.credits = 0;
		this.nbCredits = 0;
		this.nbDebits = 0;
	}

	/**
	 * Permet d'obtenir le solde d'un Account.<BR>
	 * <BR>
	 * 
	 * @return Le solde du Account.
	 */
	public double balance() {
		return this.credits - this.debits;
	}

	/**
	 * Permet d'obtenir le propri�taire d'un Account.<BR>
	 * <BR>
	 * 
	 * @see Account#setOwner(String)
	 * @return Le propri�taire du Account.
	 */
	public String getOwner() {
		return this.owner;
	}

	/**
	 * Permet d'obtenir le num�ro d'un Account.<BR>
	 * <BR>
	 * 
	 * @return Le num�ro du Account.
	 */
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * Affiche un Account - <B>SPECIAL TP.</B><BR>
	 * <BR>
	 * Affiche this.toString()<BR>
	 * <BR>
	 * <B>ATTENTION : M�thode sp�ciale pour les TP.</B><BR>
	 * <BR>
	 */
	public void print() {
		System.out.println(this.toString());
	}

	/**
	 * Permet d'obtenir la repr�sentation String d'un compte.<BR>
	 * <BR>
	 * Construit une chaine contenant le num�ro de compte, le propri�taire, le
	 * total des d�bits, le total des cr�dits, le nombre d'op�rations r�alis�es.<BR>
	 * "  Num. : aaa  - Prop. : bbb - Debit xxx E / Credit yyy E / NbOps zzz"<BR>
	 * <BR>
	 */
	public String toString() {
		return "  Num.: " + this.accountNumber + " - Owner: " + this.owner
				+ " - Debit " + this.debits + " E / Credit " + this.credits
				+ " E / NbOps " + (this.nbCredits + this.nbDebits);
	}

	// Methodes de mise a jour

	/**
	 * Permet d'effectuer un retrait sur un Account.<BR>
	 * <BR>
	 * Effectue un retrait (d�bit) de pSomme Euros sur le Account.<BR>
	 * L�ve une exception si pSomme < 0. <BR>
	 * 
	 * @param pSum
	 *            Le montant retir� sur le Account.
	 * @throws AccountException
	 *             Lorsque pSomme < 0
	 * @see Account#deposit(double)
	 */
	public void withdraw(double pSum) throws AccountException {
		if (pSum < 0) {
			throw new AccountException(
					"Errorr negative withdraw value forbidden " + this.accountNumber);
		}
		this.debits = this.debits + pSum;
		this.nbDebits++;
	}

	/**
	 * Permet d'effectuer un d�pot sur un Account.<BR>
	 * <BR>
	 * Effectue un d�pot (cr�dit) de pSomme Euros sur le Account.<BR>
	 * L�ve une exception si pSomme < 0.<BR>
	 * 
	 * @param pSum
	 *            Le montant d�pos� sur le Account.
	 * @throws AccountException
	 *             Lorsque pSomme < 0
	 * @see Account#withdraw(double)
	 */
	public void deposit(double pSum) throws AccountException {
		if (pSum < 0) {
			throw new AccountException(
					"Errorr negative deposit value forbidden " + this.accountNumber);
		}
		this.credits = this.credits + pSum;
		this.nbCredits++;
	}

	/**
	 * Permet de modifier le propri�taire d'un Account.<BR>
	 * <BR>
	 * 
	 * @param pOwnerName
	 *            Le nom du propri�taire.
	 * @see Account#getOwner()
	 */
	public void setOwner(String pOwnerName) {
		this.owner = pOwnerName;
	}

}
