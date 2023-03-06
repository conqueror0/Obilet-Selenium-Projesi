package com.obilet;


import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class StepImplementation extends BaseTest{

    FluentWait<WebDriver> wait;

    String gidis;
    String gidisKontrol;

    public StepImplementation() {
        wait = new FluentWait<WebDriver>(driver);
        wait.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(300)).ignoring(NoSuchElementException.class);
    }
    @Step("<saniye> saniye kadar bekle")
    public void waitElement(int saniye) throws InterruptedException{
        Thread.sleep(saniye*1000);
    }

    @Step("Css li <elemet> elemente tıkla")
    public void clickElementByCss(String element){
        driver.findElement(By.cssSelector(element)).click();
    }

    @Step("Id li <elemet> elemente tıkla")
    public void clickElementById(String element){
        driver.findElement(By.id(element)).click();
    }


    @Step("xpath li <elemet> elemente tıkla")
    public void clickElementByXpath(String element){
        driver.findElement(By.xpath(element)).click();
    }


    @Step("Css li <element> elementi bul ve <key> değeri yaz")
    public void sendKeysByCss(String element,String key){
        driver.findElement(By.cssSelector(element)).sendKeys(key);
    }

    @Step("xpath'li <element> elementi bul alanını kontrol et")
    public void textAreaControl(String element){
        Assert.assertTrue("Element degeri dogru",driver.findElement(By.xpath(element)).isDisplayed());
        System.out.println("Sayfa baglantisi dogru");
    }

    @Step("xpath'li <xpath> liste içerisinden rastgele seç ve tıkla")
    public void clickRandomXpath(String xpath){
        Random random=new Random();
        List<WebElement> products=driver.findElements(By.xpath(xpath));
        int index= random.nextInt(5);
        products.get(index).click();
    }

    @Step("xpath li <element> element var ise tıklanır")
    public boolean clickİsEnabled(String element){
        try {
            boolean x=driver.findElement(By.xpath(element)).isEnabled();
            if(x){
                driver.findElement(By.xpath(element)).click();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("xpath li <element> elementin uçuş numarası kaydedilir")
    public void saveNumber(String element){
        gidis =driver.findElement(By.xpath(element)).toString();
        System.out.println(gidis);
    }

    @Step("bilet numarası xpath li <element> element verilerek kontrol edilir")
    public void ticketControl(String element){
        gidisKontrol=driver.findElement(By.xpath(element)).toString();
        if(gidisKontrol.contains(gidis)){
            System.out.println("bilet numarası doğru");
        }else {
            System.out.println("bilet numarası hatalı");
        }
    }



}

