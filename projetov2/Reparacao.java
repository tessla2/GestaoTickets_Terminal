package projeto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class Reparacao implements Serializable{
	
	/**
	 * 
	 */
	static final long serialVersionUID =0;
	private static Integer idProx=1;
	private Integer id;
	private LocalDate datar; //Data do registo
	private String nomec;
	private String desch;
	private LocalDate entregap;
	private LocalDate entregapi;
	private String estado; //Possíveis:Registado, Orçamentado, Inviável, Curso, Peças, Pronto, Fechado
	private LocalDate estadoat;
	private String info;
	private Integer dias;
	
	public Reparacao (String nomec,String desch) {
		super();
		this.id = idProx++;
		this.nomec = nomec;
		this.desch = desch;
		this.datar=LocalDate.now();
	}

	public Integer getId() {
		return id;
	}


	public LocalDate getDatar() {
		return datar;
	}
	public void setDatar(LocalDate datar) {
        this.datar = datar;
    }


	public String getNomec() {
		return nomec;
	}

	public void setNomec(String nomec) {
		this.nomec = nomec;
	}

	public String getDesch() {
		return desch;
	}

	public void setDesch(String desch) {
		this.desch = desch;
	}
	
	public LocalDate getEntregapi() {
		System.out.print("  Entrega prevista para daqui a quantos dias: ");
		dias=getInt();
		entregapi= LocalDate.now().plusDays(dias);
		return entregapi;
	}

	public LocalDate getEntregap() {
		entregap = entregapi;
		return entregap;
	}

	public void setEntregap(LocalDate entregap) {
		this.entregap = entregap;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getEstadoat() {
		estadoat= LocalDate.now();
		return estadoat;
	}


	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public abstract Float getValorser();
	public abstract Float getValorpec();
	public abstract Float getTotal();
	
		
	private static int getInt() {
		 Scanner t = new Scanner(System.in);
		 return t.nextInt();
		 }
	

	@Override
	public String toString() {
	    return "+-------------------------------------------+\n" +
	           " ID:               " + id + "                \n" +
	           " Data:             " + datar + "             \n" +
	           " Nome:             " + nomec + "             \n" +
	           " Descrição:        " + desch + "             \n" +
	           " Entrega prevista: " + entregapi + "         \n" +
	           " Estado:           " + estado + "            \n" +
	           "\n Informação adicional: " + info +  "       \n";
	            
	}


	public static void setIdProx(int nextId) { //Método criado para ler o próximo Id da BD
		idProx=nextId;
		
	}

}