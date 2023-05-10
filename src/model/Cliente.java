package model;

public class Cliente {
	public String cpf;
	public String nome;
	public int idade;
	public double LimiteCredito;
	
	@Override
	public String toString() {
		return "Idade:" + idade + "; LimiteCredito:" + LimiteCredito + "\n";
	}
	
}