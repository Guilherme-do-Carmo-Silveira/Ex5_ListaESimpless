package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import docarmo.ListaObject.ListaEncadeadaObj;
import model.Cliente;

public class CadastroController {

	private void novoArquivo(ListaEncadeadaObj l, String nomeArquivo) {
		File file = new File("C:\\Temp", nomeArquivo);
		int cont;
		StringBuffer buffer = new StringBuffer();
		int ctd = 0;
		int tamanho = l.size();
		for (int i = 0; i < tamanho; i++) {
			Cliente c = new Cliente();
			try {
				c = (Cliente) l.get(i);
				buffer.append(c);
				FileWriter filewrite = new FileWriter(file);
				PrintWriter print = new PrintWriter(filewrite);
				print.write(buffer.toString());
				print.flush();
				print.close();
				filewrite.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

	public void novoCadastro(int idadeMin, int idadeMax, Double limiteCredito) throws IOException {
		File arq = new File("C:\\Temp", "Cadastro.csv");
		ListaEncadeadaObj lista = new ListaEncadeadaObj();
		lista = geraLista(lista, idadeMin, idadeMax, limiteCredito);
		String nomearq = "Idade" + idadeMax + "Limite" + limiteCredito + ".csv";
		novoArquivo(lista, nomearq);

	}

	// GeraLista
	private ListaEncadeadaObj geraLista(ListaEncadeadaObj lista, int idadeMin, int idadeMax, Double limiteCredito)
			throws IOException {
		File arq = new File("C:\\Temp", "Cadastro.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitorfluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitorfluxo);
			String linha = buffer.readLine();
			int ctd = 0;
			while (linha != null) {
				Cliente c = new Cliente();
				String vtlinha[] = linha.split(";");
				c.cpf = vtlinha[0];
				c.idade = Integer.parseInt(vtlinha[2]);
				c.nome = vtlinha[1];
				c.LimiteCredito = Double.parseDouble(vtlinha[3]);
				if (c.idade > idadeMin && c.idade < idadeMax && c.LimiteCredito > limiteCredito) {
					try {
						if(ctd == 0) {
							lista.addFirst(c);
						}else {
							lista.add(c, ctd);
							ctd++;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitorfluxo.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo invalido");
		}
		return lista;
	}
}
