
# Projenin detaylı anlatımı aşağıdaki gibidir.



## 1 Docker Container Altyapısı
   UI web testleri için öncelikle docker altyapısı oluşturma ile başladım.
    VNC viewer ile senaryolarımı izleyebilmek için nodeların debug versiyonlarını seçtim.
    iki tane browser seçtim ve docker-compose.yml dosyasındaki gibi hub containerına linkledim.

    selenium/hub --> 4446  --> http://localhost:4446/grid/console
    selenium/node-firefox-debug   VNC localhost:5902 portundan izlenebilir. (password: secret)
    selenium/node-chrome-debug    VNC localhost:5901 portundan izlenebilir. (password: secret)

   chrome containerımda yaşadığım performans sorunları nedeni ile volume parametresi geçtim.
    sonrasında sorun kalmadı.

   VNC viewer ile testleri izlemek için farklı portlar verdim bunları da compose dosyamda görebilirsiniz.

   docker altyapısını ayağa kaldırmak için testlerden önce bu komut çalıştırılmalıdır:

    docker compose start için : docker-compose up -d

    docker compose stop için : docker-compose stop

## 2 Paralel Koşum ve Crossbrowsing Test Koşumu

   testlerimi testng ile koşmaktayım. pom içerisinde testng.xml kısmını parametrik hale getirdim. testng.xml web ui tarafını
    wstestng.xml parametresi ise webservis tarafını koşmaktadır.

   testlere browser parametresi vererek testleri çoğalttım. oluşutrulan her test iki browserda da koşacak hale geldi.

    örnek web koşum :  mvn test -DsuiteXMLFile=testng.xml
    örnek webservis koşum :  mvn test -DsuiteXMLFile=wstestng.xml

   testngnin paralel test koşma özelliğini kullandım.

## 3 Raporlama
   extentreports kullandım raporlamada. ITestListener implement ederek, main>java>utils altındaki TestListener classında
    testlerimi dinleyerek extentreports html raporu test sonunda ExtentReports altında oluşmaktadır.

   fail olan senaryolarda otomatik ekran görüntüsü de alınmaktadır. Ayrıca success de de ekran görüntüsü aldırdım.

## 4 Tüm Butik ve mağazaların linklerini ayrıca imajlarının linklerini toplamak için 3 adet testim bulunmaktadır.

   #### 4.1.ui ekran üzerinde sayfanın en altına kadar scroll ederek, tüm linklerin yüklenmesini sağlamaktadır.
        2 browser için de çalışmakdatır.  (MainPageTest > scrollToBottom x 2 browser - 2 TEST)

  #### 4.2. elde edilen tüm mağaza linklerini çağırarak, statusCode ve responseTime larını csv ye yazmaktadır. (1 Test)
   CompanyResponseCodes.csv dosyası incelenebilir.

  #### 4.3. ise elde edilen imaj urllerini çağırarak, statusCode ve responseTime larını csv ye yazmaktadır. (1 Test)
   CompanyImagesResponseCodes.csv dosyası incelenebilir.

   4.2. ve 4.3 için tek bir browser üzerinde çalıştırdım çünkü browser bağımsız restassured ile istenilen kontrolleri gerçekleştiriyorum. bu yüzden zaman kaybı olmaması için sadece chrome testlerine ekledim.

## 5. Login için toplamda 6 adet senaryo yazdım. Fail olan senaryo başarılı fail senaryomdur. LoginPageTest classı içindedir.


## 6. Web servis senaryolarını ise; mvn test -DsuiteXMLFile=wstestng.xml şeklinde çalıştırabilirsiniz.
   istenildiği gibi APITestCase classını extend edip API_ROOT değişkeni testin en başında setlenmiştir.
