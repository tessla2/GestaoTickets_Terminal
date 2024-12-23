package projeto;

import java.io.*;
import java.util.ArrayList;
 
 
public class Backup {
 
	public static void gravaReparacoes(ArrayList<Reparacao> reparacoes, String nomeFicheiro) {
        File f = new File(nomeFicheiro);
        try {
            f.createNewFile();              //Criar novo ficheiro
            ObjectOutputStream ficheiro = new ObjectOutputStream(new FileOutputStream(nomeFicheiro));
            ficheiro.writeObject(reparacoes); // Escrever o arrayList no ficheiro
            System.out.println("Backup de reparações executado com sucesso.");
            System.out.println("no ficheiro: " + f.getAbsolutePath());
            ficheiro.flush();
            ficheiro.close();
        } catch (IOException e) {
            e.printStackTrace();            // Se a operação der erro mostra o erro...
        }
    }
    @SuppressWarnings("unchecked")
    public static ArrayList<Reparacao> lerReparacoes(String nomeFicheiro) {
        ArrayList<Reparacao> reparacoes = new ArrayList<>();
        File ficheiro = new File(nomeFicheiro);
        if (!ficheiro.exists()) {
            return reparacoes;
        }

        try (ObjectInputStream f = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            reparacoes = (ArrayList<Reparacao>) f.readObject();
            int maxId = reparacoes.stream()
                                   .mapToInt(Reparacao::getId)
                                   .max()
                                   .orElse(0);
            Reparacao.setIdProx(maxId + 1);
           // System.out.println("\nDados carregados com sucesso!\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return reparacoes;
    }
    
}
 