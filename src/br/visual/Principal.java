package br.visual;

import java.sql.Date;
import java.text.SimpleDateFormat;

import br.modelo.Empregado;
import br.modelo.EmpregadoDAO;

/**
 * classe onde executa o programa 
 * @author Roberto Silva
 *
 */
public class Principal {
	public static void main(String[] args) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		EmpregadoDAO empDao=new EmpregadoDAO();
		
		Empregado emp1=new Empregado();
		Empregado emp2=new Empregado();
		Empregado emp3=new Empregado();
		
		//setando o empregado 1
		emp1.setId(1);
		emp1.setNome("Roberto");
		emp1.setDat_nasc(new Date(sdf.parse("1983-08-12").getTime()));
		emp1.setSalario(5500.45);

		//setando o empregado 2
		emp2.setId(2);
		emp2.setNome("Katia");
		emp2.setDat_nasc(new Date(sdf.parse("1983-08-12").getTime()));
		emp2.setSalario(4500.50);

		//setando o empregado 3
		emp3.setId(3);
		emp3.setNome("Rose");
		emp3.setDat_nasc(new Date(sdf.parse("1983-08-12").getTime()));
		emp3.setSalario(6500.50);

		//inserindo
		empDao.inserir(emp1);
		empDao.inserir(emp2);
		empDao.inserir(emp3);
		
		//exibe os dados após a inserção
		System.out.println("Inserção:\n"+empDao.selecionar());
		
		//alterando os salarios, tiveram um aumento :)
		emp1.setSalario(6000.70);
		emp2.setSalario(5000.50);
		emp3.setSalario(7000.70);
		
		//atualizando os dados
		empDao.atualizar(emp1);
		empDao.atualizar(emp2);
		empDao.atualizar(emp3);
		
		//exibe os dados após a atualização
		System.out.println("Alteração do sálario:\n"+empDao.selecionar());
		
		//coloca em um array de Empregado
		Empregado []emps={emp1,emp2,emp3};
		
		//for each irá exclui um a um empregado do array
		for(Empregado empIn:emps)
		empDao.excluir(empIn.getId());
		
		System.out.println("Exclusão, total de registros :"+empDao.selecionar().size());
	}
}
