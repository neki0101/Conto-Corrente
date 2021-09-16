package it.unibs.fp.CartaAlta;

import java.io.File;

import it.unibs.fp.mylib.*;
public class ContoCorrenteMain {

	public static String TITOLO="SCEGLI";
	public static String [] SCELTE= {"PRELEVA","VERSA","CREA NUOVO CONTO","STAMPA"};
	public static final int PRELEVA=1;
	public static final int VERSA=2;
	public static final String SOMMA="scegli la somma";
	public static final String NOME="scegli il nome";
	public static final int CREA=3;
	public static final int STAMPA=4;
	
	public static void main(String [] args) {
		MyMenu menu= new MyMenu(TITOLO, SCELTE);
		int scelta;
		int somma;
		ContoCorrente cc=caricaContoCorrente();
		String nome=InputDati.leggiStringa(NOME);
		cc.setNome(nome);
		do
		{
			
			scelta= menu.scegli();
			try 
			{
			   
			    switch(scelta)
			    {
			        case PRELEVA: somma=InputDati.leggiIntero(SOMMA);
			                      cc.prelievo(somma);
				                  break;
				     
			        case VERSA:  somma=InputDati.leggiIntero(SOMMA);
                                 cc.versamento(somma);
			    	             break;
			    	             
			        case CREA: cc= creaContoCorrente();
			                     break;
			        
			        case STAMPA:cc.toString();
			                    break;
			        
			    }
			}
			
			catch(ContoInsufficienteException e) 
			{
			    e.getMessage();
			}
			
			catch(ImportoErrato e) 
	    	{
	    		e.getMessage();
	    	}
			
			catch(PrelievoTroppoAltoException e) 
	    	{
	    		e.getMessage();
	    	}
			
			salvaContoCorrenteSuFile(cc);
			
		}while (scelta !=0 );
		
	}
	
	
	/**
	 * metodo per caricare su file un conto corrente vuoto
	 * @return il conto corrente inserito nel file
	 */
	private static ContoCorrente caricaContoCorrente() {
		File fileCC = new File("contocorrente.dat");
		ContoCorrente contoLetto= (ContoCorrente)ServizioFile.caricaSingoloOggetto(fileCC);
		return contoLetto;
	}
	
	
	/**
	 * metodo che salva sul file lo stato del conto corrente dopo ogni azione dell'utente
	 * @param cc
	 */
	private static void salvaContoCorrenteSuFile(ContoCorrente cc) {
		
		File dst = new File("contocorrente.dat");
		ServizioFile.salvaSingoloOggetto(dst, cc);
	
   }
	
	
	/**
	 * crea un nuovo conto corrente, l'utente sceglie il nome e il saldo iniziale del conto
	 * @return
	 */
	private static ContoCorrente creaContoCorrente() {
		String intestatario = InputDati.leggiStringaNonVuota("Intestatario del conto");
		int saldoIniziale = InputDati.leggiInteroConMinimo("Saldo iniziale",0);
		return new ContoCorrente(intestatario, saldoIniziale);
	}
}
