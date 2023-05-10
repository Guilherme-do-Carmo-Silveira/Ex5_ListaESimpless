package view;

import java.io.IOException;

import controller.CadastroController;

public class main {
	public static void main(String[] args) {
		
		CadastroController m = new CadastroController();
		
		try {
			m.novoCadastro(41, 60, 8000.00);
			m.novoCadastro(31, 40, 5000.00);
			m.novoCadastro(21, 30, 3000.00);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}