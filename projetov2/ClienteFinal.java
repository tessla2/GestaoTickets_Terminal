package projeto;

import java.io.Serializable;
import java.util.Scanner;

public class ClienteFinal extends Reparacao implements Serializable{
	
	/**
	 * 
	 */
	static final long serialVersionUID = 0;
	private String clientet="Cliente Final";
	private Float valorseri; //Valor Inicial recebido para calcular o desconto final
	private Float valorser;
	private Float valorpec;
	private Float total;
	
	
	
	public ClienteFinal(String nomec, String desch, String clientet) {
		 super(nomec, desch);
		 this.clientet = clientet;
		 valorseri=0f; 
		 valorser=0f;
		 valorpec=0f;
		 total=0f;
		 }

	public String getClientet() {
		return clientet;
	}

	public void setClientet(String clientet) {
		this.clientet = clientet;
	}
	public Float getValorser() {
		System.out.println("Valor dos serviços a registrar: ");
		valorseri=getFloat();
		valorser=valorseri-(3*valorseri/100);
		return valorser;
	}

	public void setValorser(Float valorser) {
		this.valorser = valorser;
	}
	public Float getValorpec() {
		System.out.print("Valor das peças a registrar: ");
		valorpec=getFloat();
		return valorpec;
	}

	public void setValorpec(Float valorpec) {
		this.valorpec = valorpec;
	}
	public Float getTotal() {
		total= valorser + valorpec;
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	private static Float getFloat() {
		 Scanner t = new Scanner(System.in);
		 return t.nextFloat();
		 }


	@Override
	
	public String toString() {
	    return " " + super.toString() + "\n" +
	           " Cliente:         " + clientet + "\n" +
	           " Valor serviços:  " + valorser + "€\n" +
	           " Valor peças:     " + valorpec + "€\n" +
	           " Total:           " + total + "€\n" +
	           " +-------------------------------------------+";
	}


}

