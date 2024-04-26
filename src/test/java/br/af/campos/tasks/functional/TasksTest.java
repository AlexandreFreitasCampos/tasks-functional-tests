package br.af.campos.tasks.functional;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.xpath;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import br.af.campos.tasks.utils.DriverFactory;

public class TasksTest extends DriverFactory {
//	@BeforeClass
//	public static void acessarAplicacao() {
//		getDriver();
//	}
	

	@Test
	public void deveSalvarTarefaComSucesso() {
		try {
			getDriver();
			// Clicar em Add Todo
			getDriver().findElement(By.xpath("//a[@id='addTodo']")).click();
			// Escrever descrição
			getDriver().findElement(xpath("//input[@id='task']")).sendKeys("Tarefa via Selenium");
			// Escrever a data
			getDriver().findElement(By.xpath("//input[@id='dueDate']")).sendKeys("10/10/2024");
			// Clicar em salvar
			getDriver().findElement(By.xpath("//input[@id='saveButton']")).click();
			// Validar mensagem de sucesso
			String mensagem = getDriver().findElement(By.id("message")).getText();
			assertEquals("Success!", mensagem);
		} finally {
			// Fechar o browser
			killDriver();
		}

	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		try {
			// Clicar em Add Todo
			getDriver().findElement(By.xpath("//a[@id='addTodo']")).click();
			// Escrever descrição
			getDriver().findElement(By.xpath("//input[@id='task']")).sendKeys("Tarefa via Selenium");
			// Escrever a data
			getDriver().findElement(By.xpath("//input[@id='dueDate']")).sendKeys("10/10/2020");
			// Clicar em salvar
			getDriver().findElement(By.xpath("//input[@id='saveButton']")).click();
			// Validar mensagem de sucesso
			String mensagem = getDriver().findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			// Fechar o browser
			killDriver();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		try {
			// Clicar em Add Todo
			getDriver().findElement(By.xpath("//a[@id='addTodo']")).click();
			// Escrever descrição
			getDriver().findElement(By.xpath("//input[@id='task']")).sendKeys("Tarefa via Selenium");
			// Escrever a data
			//getDriver().findElement(By.xpath("//input[@id='dueDate']")).sendKeys("10/10/2020");
			// Clicar em salvar
			getDriver().findElement(By.xpath("//input[@id='saveButton']")).click();
			// Validar mensagem de sucesso
			String mensagem = getDriver().findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			// Fechar o browser
			killDriver();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		try {
			// Clicar em Add Todo
			getDriver().findElement(By.xpath("//a[@id='addTodo']")).click();
			// Escrever descrição
			//getDriver().findElement(By.xpath("//input[@id='task']")).sendKeys("Tarefa via Selenium");
			// Escrever a data
			getDriver().findElement(By.xpath("//input[@id='dueDate']")).sendKeys("10/10/2024");
			// Clicar em salvar
			getDriver().findElement(By.xpath("//input[@id='saveButton']")).click();
			// Validar mensagem de sucesso
			String mensagem = getDriver().findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			// Fechar o browser
			killDriver();
		}
	}
}
