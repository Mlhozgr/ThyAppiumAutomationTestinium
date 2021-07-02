import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

public class StepImplementation extends BaseTest{

    @Step("<id>id li elemente tıkla")
    public void clickByid(String id){
        appiumDriver.findElement(By.id(id)).click();
        System.out.println("Elemente tıklandı");
    }
    @Step("<id>id li elemente <key> yaz")
    public void sendKeysByid(String id,String key){
        appiumDriver.findElement(By.id(id)).sendKeys(key);
        System.out.println(" : yazıldı"+key);
    }

    @Step("<key> xpath li elemente tıkla")
    public  void clickByxpath(String key){
        appiumDriver.findElement(By.xpath(key)).click();
    }
    @Step("<id> id li ucus listesini kontrol et")
    public void listControl(String key){
        Assert.assertTrue("Ucus Listelenmedi",appiumDriver.findElement(By.id(key)).isDisplayed());}
}