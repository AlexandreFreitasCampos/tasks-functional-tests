package br.af.campos.tasks.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	// CriaA variavel gloBal driver
		protected static WebDriver driver;
		

		// Método paraAcessar o metodo createDriver
		public static WebDriver getDriver() {			
			// Se o driver for nulo,  cria um novo driver
			if (driver ==  null) {
				try {
					createDriver();
					getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				} catch (MalformedURLException e) {
					System.out.println("Falha ao inicializar o driver"+ e.getMessage());
				}
			}

			return driver;
		}

		// Método para criar o driver
		protected static void createDriver() throws MalformedURLException {
			// Configurar o WebDriverManager para gerenciarAutomaticamente o ChromeDriver
	        WebDriverManager.chromedriver().clearDriverCache().setup();
	        
	        // Inicializar o ChromeDriver
	        driver =  new ChromeDriver();
	        //driver =  new FirefoxDriver();
	        //driver =  new EdgeDriver();

			//driver.get("https://irp.imusica.com.br/Account/Login");

	        driver.get("http://localhost:8001/tasks/");
	        driver.manage().window().maximize();
		}

		// Método para fechar o driver
		public static void killDriver() {
			// Se o driver for diferente de null
			if (driver !=  null) {
				// Fechaa o  driver
				driver.quit();
				// Limpa o driver
				driver =  null;
				// Mate o processo chromedriver em segundo plano (se necessário)
				
			}else {
				try {
					// Comando para matar o processo chromedriver
					Process process = Runtime.getRuntime().exec("pkill chromedriver");

					// Aguarde o processo terminar
					process.waitFor();
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}


}
