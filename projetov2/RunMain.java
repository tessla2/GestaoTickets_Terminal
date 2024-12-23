package projeto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;

public class RunMain {

		private static ArrayList<Reparacao> reparacoes = new ArrayList<>();
		final private static String fileCli = "myClientes.db"; // Final indica que o método não pode ser sobrescrito por subclasses.
		public static void main(String[] args) {
		
		reparacoes=(ArrayList<Reparacao>)Backup.lerReparacoes(fileCli);
		if (reparacoes == null || reparacoes.size()==0) {
			reparacoes = new ArrayList<>(); 
		}
		 
		 int op;
		 int op2;
		 
		 do {
		 op = menu();

		 switch (op) {
		 
// -------------------------------- Case 0 ------------------------------------		 
		 case 0:
		 {
		 Backup.gravaReparacoes(reparacoes, fileCli);
		 System.out.println("  \\_/");
         System.out.println(" (o,o)");
         System.out.println(" (   )");
         System.out.println("  -\\-");
         System.out.println("  / \\");
		 exitApplication();
		 }
		 
// -------------------------------- Registar clientes ------------------------------------		 
		 case 1: 
		 {
			 op2=menu1();
			 switch (op2) {
			 case 1:
			 {
				 Reparacao x;
				 x = new ClienteFinal("nome do cliente","descrição histórica","Cliente Final");

					insereReparacao(x);
					reparacoes.add(x);
					System.out.println(" +-------------------------------------------+");
					System.out.println(" |         Reparação Criada                  |");
					System.out.println(x);
					System.out.println();
					break;
			 }		
					
			 case 2:
			 {
				 Reparacao y;
				 y = new ClienteRevendedor("nome do cliente","descrição histórica","Cliente Revendedor");

					insereReparacao(y);
					reparacoes.add(y);
					System.out.println(" +-------------------------------------------+");
					System.out.println(" |         Reparação Criada                  |");
					System.out.println(y);
					System.out.println();
					break;
			 }	 
			 }
			 break;
		 }
			 
			 
// -------------------------------- Consultar registos ----------------------------------			 

		 case 2:
			 op2=menu2();
			 switch (op2) {
			 case 1:
				boolean encontrou0 = false;
				 // Consultar todos
				 
				 for (Reparacao x : reparacoes) { 
						System.out.println(x.toString());
						encontrou0 = true;
						}
				if (!encontrou0) {
					System.out.println("\nNenhum registo encontrado\n");
				}
				 
						break;
				 
			 case 2:
				 // Consulta por estados
				 
				 System.out.println("+-------------------------------------------+");
				 System.out.println("| Estados possíveis para consulta:          |");
				 System.out.println("| • Registado                               |");
				 System.out.println("| • Orçamentado                             |");
				 System.out.println("| • Inviável                                |");
				 System.out.println("| • Em curso                                |");
				 System.out.println("| • Aguarda peças                           |");
				 System.out.println("| • Pronto (aguarda levantamento do cliente)|");
				 System.out.println("| • Fechado                                 |");
				 System.out.println("+-------------------------------------------+");
				    String estadoBusca = getString();
				    boolean encontrou1 = false;

				    for (Reparacao x : reparacoes) {
				        if (x.getEstado().equalsIgnoreCase(estadoBusca)) {
				            System.out.println(x);
				            encontrou1 = true;
				        }
				    }
				    if (!encontrou1) {
				        System.out.println("Nenhuma reparação encontrada com o estado: " + estadoBusca);
				    }
				
				 break;

				 
				 
			 case 3:
				 //Consulta por intervalo de tempo
				 System.out.println("Data de início para consulta: (Formato AAAA-MM-DD)" );
				 LocalDate DIC = LocalDate.parse(getString());
				 System.out.println("Data de final para consulta: (Formato AAAA-MM-DD)" );
				 LocalDate DFC = LocalDate.parse(getString());
				
				 boolean encontrou2 = false;
				
				 for(Reparacao x : reparacoes) {
					 LocalDate dataRegisto = x.getDatar();
					 if (!dataRegisto.isBefore(DIC) && !dataRegisto.isAfter(DFC)) 
					 {
						 System.out.println(x);
						 encontrou2 = true;
					 }
				 } 
				 
				 if(!encontrou2) 
				 {
					 System.out.println("Nenhum registo encontrado neste intervalo de tempo");
				 }
				 break;
				
			 case 4:
				 // Consulta por cliente
				
				 System.out.print("Digite o nome do cliente para consulta: ");
				    String consultaCliente = getString();
				    boolean encontrou3 = false;
 
				    for (Reparacao x : reparacoes) {
				        if (x.getNomec().equalsIgnoreCase(consultaCliente)) {
				            System.out.println(x);
				            encontrou3 = true;
				        }
				    }
				    if (!encontrou3) {
				        System.out.println("Nenhuma reparação encontrada com o cliente: " + consultaCliente);
				    }
				 break;
 
				
			 case 5:
				 // Consultar entregas em atraso
				
				 boolean encontrou4 = false;
				 for(Reparacao x : reparacoes) {
					 if (x.getEntregap().isBefore(LocalDate.now())){ //método da classe LocalDate,que é usado para verificar se uma data é anterior a outra data.
					 System.out.println("Entregas em atraso: " + x);
					 encontrou4 = true;
					
					   }
					 
					 }
					 if (!encontrou4) {
						 System.out.println("\nNenhuma entrega em atraso");
				 }
				 
			break;
			 case 6:
				    // Consultar por ID
				    System.out.print("Digite o número de ID a consultar: \n");
				    int consultaId = getInt();
				    boolean encontrou5 = false;

				    for (Reparacao reparacao : reparacoes) {
				        if (reparacao.getId() == consultaId) {
				            System.out.println("Reparação encontrada:\n");
				            System.out.println(reparacao);
				            encontrou5 = true;
				            break; // Encerra o loop assim que a reparação é encontrada
				        }
				    }

				    if (!encontrou5) {
				        System.out.println("Nenhuma reparação encontrada com o ID: " + consultaId);
				    }
				    break;
	 
			 }
			 break;
			 
// ----------------------------- Editar registo ------------------------------------------			 

		 case 3:
			 Boolean encontrei = false;
			 Integer consId = 0;
			 System.out.println("Id do registo : ");
			 consId = getInt();
			 for (Reparacao x : reparacoes) {   		 
			 if(x.getId().equals(consId)) {
			 System.out.println(x);
			 encontrei = true;
			 op2 = menu3();
			 switch (op2) {
			 
			 case 1:
				    System.out.println("Digite a data que o pedido foi registado (Formato AAAA-MM-DD): ");
				    LocalDate newDate = LocalDate.parse(getString());
				    x.setDatar(newDate); // Atualiza a data de registo da reparação
				    System.out.println("Data de registo atualizada: " + x.getDatar() + "\n");
				    break;
			 case 2:
				    System.out.println("Nome do Cliente : ");
				    x.setNomec(getString());
				    System.out.println("Nome do Cliente atualizado: " + x.getNomec() + "\n");
				    break;
			 case 3:
				    System.out.println("Descrição Histórica : ");
				    x.setDesch(getString());
				    System.out.println("Descrição histórica atualizada");
				    break;
			 case 4:
				    System.out.println("  Nova data de entrega");
				    System.out.println("  Data de entrega atualizada: " + x.getEntregapi() + "\n");
				    break;

			 case 5:
				 System.out.println("+-------------------------------------------+");
				 System.out.println("| Estados possíveis para edição  :          |");
				 System.out.println("| • Registado                               |");
				 System.out.println("| • Orçamentado                             |");
				 System.out.println("| • Inviável                                |");
				 System.out.println("| • Em curso                                |");
				 System.out.println("| • Aguarda peças                           |");
				 System.out.println("| • Pronto (aguarda levantamento do cliente)|");
				 System.out.println("| • Fechado                                 |");
				 System.out.println("+-------------------------------------------+");
				 System.out.print("Estado: ");
				 x.setEstado(getString());
				 System.out.print("Estado atualizado: "  +
				 x.getEstadoat()+"\n");
				 break;
			 case 6:	 
				 System.out.print("Informações adicionais : ");
				 x.setInfo(getString());
				 break;
			 case 7:	 
				 System.out.print("Valor dos serviços: " +
				 x.getValorser()+"\n");
				 break;
			 case 8:	 
				 System.out.print("Valor das peças: " +
				 x.getValorpec()+"\n");
				 break;
			 case 9:
				 System.out.println("Total: " + x.getTotal()+"\n");
			 }

			 }
			 }
			 if (!encontrei) // if (encontrei == false)
			 System.out.println("Não existe registo : " +
			consId);
		 break;

// ----------------------------- Eliminar registo ----------------------------------------			 

		 case 4:
			 
			 Boolean eliminei = false;
			 Integer delId = 0;
			 System.out.println("ID do registo : ");
			 delId = getInt();
			 for (Reparacao x : reparacoes) {
			 if
			(x.getId().equals(delId) && (x.getEstado().equals("Registado"))) {
			 System.out.println(x);
			 if(confirma())
			 {

			 reparacoes.remove(x);
			 eliminei = true;
			 if (eliminei == true) {
				 System.out.println("Registo eliminado com sucesso\n" + x);
			 }
			 }
			 break;
			   }
			 }
			 if (!eliminei)
			 System.out.println("Impossivel eliminar o registo : " + delId);
			 break;
		     } 
		 } while (op != 0);
	}


// ------------------------- Funções auxiliares -----------------------------------------
		 
		 private static void insereReparacao(Reparacao novaReparacao) {
			 System.out.println();
			 System.out.println("======== Inserção de dados de Registo ========");
			 System.out.println("  Numero da reparação : " +
			 novaReparacao.getId());
			 System.out.print("  Data do registo do pedido : " +
			 novaReparacao.getDatar());
			 System.out.print("\n  Nome do cliente : ");
			 novaReparacao.setNomec(getString());
			 System.out.print("  Descrição histórica : ");
			 novaReparacao.setDesch(getString());
			 System.out.print("  Entrega prevista para : " +
			 novaReparacao.getEntregapi());
			 System.out.print("\n  Estados possíveis a registar: \n"
			 		+ "• Registado\r\n"
			 		+ "• Orçamentado\r\n"
			 		+ "• Inviável\r\n"
			 		+ "• Em curso\r\n"
			 		+ "• Aguarda peças\r\n"
			 		+ "• Pronto (aguarda levantamento do cliente)\r\n"
			 		+ "• Fechado\n");
			 System.out.print("\n  Estado : ");
			 novaReparacao.setEstado(getString());
			 System.out.print("  Estado atualizado a : " +
			 novaReparacao.getEstadoat());
			 System.out.print("\n  Informações adicionais : ");
			 novaReparacao.setInfo(getString());
			 System.out.println();
			 }

		 
		 private static boolean confirma() {
			 char resposta;
			 System.out.println("Confirma S/N : ");
			 resposta = getChar();
			 if(resposta == 'S' || resposta == 's')
			 return true;
			 return false;
			 }
		 
		 private static void exitApplication() {
			    System.out.println("Saindo do programa...");
			    System.exit(0);
			}

// -------------------------- Funções para receber variáveis-----------------------
		 
		 private static Float getFloat() {
		 Scanner t = new Scanner(System.in);
		 return t.nextFloat();
		 }

		 private static int getInt() {
		 Scanner t = new Scanner(System.in);
		 return t.nextInt();
		 }

		 private static String getString() {
		 Scanner t = new Scanner(System.in);
		 return t.nextLine();
		 }
		 private static char getChar() {
		 Scanner t = new Scanner(System.in);
		 return t.nextLine().charAt(0);
		 }
		 
// ------------------------------------- Menus --------------------------------------		 
		 
		 private static int menu() {
		 System.out.println();
		 System.out.println("+-----------------------------+");
		 System.out.println("| Menu Inicial                |");
		 System.out.println("+-----------------------------+");
		 System.out.println("| 1 Registar pedido           |");
		 System.out.println("| 2 Consultar pedido          |");
		 System.out.println("| 3 Editar pedido             |");
		 System.out.println("| 4 Eliminar pedido           |");
		 System.out.println("| 0 Sair                      |");
		 System.out.println("+-----------------------------+");
		 System.out.print("\n Opção : ");
		 return getInt();

		 }
		 private static int menu1() {
			 System.out.println();
			 System.out.println("+-----------------------------+");
			 System.out.println("| Tipo de Cliente a Registrar:|");
			 System.out.println("+-----------------------------+");
			 System.out.println("| 1 Cliente Final             |");
			 System.out.println("| 2 Cliente Revendedor        |");
			 System.out.println("| 0 Retornar                  |");
			 System.out.println("+-----------------------------+");
			 System.out.print("\n Opção : ");
			 return getInt();
			 }
		 private static int menu2() {
			 System.out.println();
			 System.out.println("+-----------------------------+");
			 System.out.println("| Consultar                   |");
			 System.out.println("+-----------------------------+");
			 System.out.println("| 1 Todos                     |");
			 System.out.println("| 2 Por estado                |");
			 System.out.println("| 3 Por intervalo de tempo    |");
			 System.out.println("| 4 Por cliente               |");
			 System.out.println("| 5 Entregas em atraso        |");
			 System.out.println("| 6 Por Id                    |");
			 System.out.println("| 0 Retornar                  |");
			 System.out.println("+-----------------------------+");
			 System.out.print("\n Opção : ");
			 return getInt();
			 }
		 private static int menu3() {
			 System.out.println();
			 System.out.println("+-----------------------------+");
			 System.out.println("| Campo a editar              |");
			 System.out.println("+-----------------------------+");
			 System.out.println("| 1 Data do registo           |");
			 System.out.println("| 2 Nome do Cliente           |");
			 System.out.println("| 3 Descrição histórica       |");
			 System.out.println("| 4 Data de Entrega           |");
			 System.out.println("| 5 Estado                    |");
			 System.out.println("| 6 Informações adicionais    |");
			 System.out.println("| 7 Valor dos serviços        |");
			 System.out.println("| 8 Valor das peças           |");
			 System.out.println("| 9 Calcular Total            |");
			 System.out.println("| 0 Retornar                  |");
			 System.out.println("+-----------------------------+");
			 System.out.print("\n Opção : ");
			 return getInt();
			 }
		 


           
		}

