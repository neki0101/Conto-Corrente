package it.unibs.fp.CartaAlta;

public class ContoCorrente {

	public static final String MSG_ERRATO="IMPORTO ERRATO";
	public static final String MSG_INSUFF="NON HAI ABBASTANZA DENARO";
	public static final String MSG_OFFLIMIT="HAI SUPERATO IL LIMITE CONSENTITO";
	public static final int LIMITE=500;
	private int soldi;
	private String nome;
	
	public ContoCorrente()
	{
		soldi=0;
	}
    
	public ContoCorrente(String intestatario, int soldi) {
		nome=intestatario;
		this.soldi=soldi;
	}

	public int getSoldi() {
		return soldi;
	}
	
	public void setSoldi(int soldi) {
		this.soldi = soldi;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
 
	/**
	 * metodo per prelevare i soldi dal conto
	 * @param somma di denaro da prelevare
	 * @throws ContoInsufficienteException
	 * @throws ImportoErrato
	 * @throws PrelievoTroppoAltoException
	 */
	
    public void prelievo(int somma) throws ContoInsufficienteException, ImportoErrato, PrelievoTroppoAltoException
    {
    	if (somma<0)
    		throw new ImportoErrato(MSG_ERRATO);
    	if(somma>this.soldi)
    		throw new ContoInsufficienteException(MSG_INSUFF);
    	if (somma>LIMITE)
    		throw new PrelievoTroppoAltoException(MSG_OFFLIMIT);
    	this.soldi-=somma;
    }
    
    
    /**
     * metodo per versare i soldi nel conto
     * @param somma di denaro da versare
     * @throws ImportoErrato
     */
    public void versamento(int somma) throws ImportoErrato {
    	
    	if (somma<0)
    		throw new ImportoErrato(MSG_ERRATO);
    	this.soldi+=somma;
    	
    }
  
    /**
     * ritorna una stringa contentente intestatario e i soldi all'interno del conto
     */
    public String toString () {
    	return this.nome + " " + this.soldi;
    }
	
}
