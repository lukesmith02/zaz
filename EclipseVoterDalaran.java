
　
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Time;
import java.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EclipseVoterDalaran {
	public static void main(String[] args) throws InterruptedException{
		Random rand = new Random();
		long timeLastVotedPlus12h=0;
		long curTime=0;
		while (true){
			Thread.sleep(18000 + rand.nextInt(18000)); //sleep 5 mins + random 0-5 mins
			curTime=System.currentTimeMillis() / 1000;
			if(curTime - timeLastVotedPlus12h > 0){
				
				////////////////////////////////////////////
				int sleep = rand.nextInt(10)+1;
				Thread.sleep(sleep*1000);
				vote("username","pass");
				
				sleep = rand.nextInt(100)+1;
				Thread.sleep(sleep*1000);
				vote("username","pass");
				
				sleep = rand.nextInt(100)+1;
				Thread.sleep(sleep*1000);
				vote("username","pass");
				///////////////////////////////////////////
				
				
				timeLastVotedPlus12h=(System.currentTimeMillis() / 1000) + (12*3600) + rand.nextInt(9000) + rand.nextInt(9000);
			}
		}
	}
	
	
	
	
	
	
	public static void putInClip(String str){

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(str);
		clipboard.setContents(strSel, null);
		
	}
	
	public static void vote(String acctName, String pWord) throws InterruptedException{
		System.setProperty("webdriver.ie.driver", "C:\\Windows\\Temp\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		
		driver.get("http://www.dalaran-wow.com/account-log/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//accountName
		String id = "accountName";
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		driver.findElement(By.id(id)).click();
		putInClip(acctName);
		driver.findElement(By.id(id)).sendKeys(Keys.chord(Keys.CONTROL, "v"));
		
		id="password";
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		driver.findElement(By.id(id)).click();
		putInClip(pWord);
		driver.findElement(By.id(id)).sendKeys(Keys.chord(Keys.CONTROL, "v"));
		
		driver.findElement(By.id(id)).sendKeys(Keys.ENTER);
		id="box-art";
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		
		driver.get("http://www.dalaran-wow.com/account/vote/");
		
		id="Mmorpg top list";
		for(int i=1;i<5;i++){
			Random rand = new Random();
			int sleep = rand.nextInt(3000);
			
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(id)));
			driver.get("http://www.dalaran-wow.com/account/vote/"+i);
			Thread.sleep(2000+sleep);
		}
		
		
		driver.close();
	}
}
