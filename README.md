# Electronic Grade Book REST API

![Imgur](https://i.imgur.com/RR9yR89.png)

Prethodna šema je više skica nego 100% implementaciona, može doći do odstupanja tokom implementacije.
Može biti problem sa prikazom gornje šeme ako je browser u night modu ili je neka dark tema.

Za nasledjivanje je korišćen pristup tabela po entitetu kod UserEntity i njenih podklasa.
Kod ocene i zakljucne ocene je korišćen pristup sa jednom tabelom za sve.

Brisanje je implementirano kao "soft delete", bez oslanjanja na CascadeType.REMOVE zbog nekih neželjenih efekata. 

Za mapiranje Korisnika i obezbedjenje nasledjivanja DTO-ova, korisceni MapStruct i @JsonTypeInfo i @JsonSubTypes. Problem bio sa 
enkriptovanjem passworda, reseno je ali je hacky i stvorilo je problem sa prezentacijom vracenih DTO-va
 ~~- polja password i confirmedPassword su stalno prisutna, bez obzira na @JsonIgnore anotaciju.~~
 Problem je rešen dodavanjem @JsonIgnore anotacije na getere.
 
 API je donekle nedovršen i nekonzistentan, ostaje TODO!!!
 
 
 ## Pokretanje aplikacije
 
 Pre importovanja projekta u IDE u korenu projekta izvršiti 
 
    mvn package 	
 
 Ukoliko se prilikom pokretanja servera dobije greška (da nije moguće pronaći UserMapper...), potrebno je uraditi maven clean i maven install nad   pom.xml da se generiše fajl UserMapperImpl.java u target/generated-sources/annotations/com/iktpreobuka/schoollogtwo/util.
 
 U korenu repozitorijuma se nalazi skripta import.sql za popunjavanje baze ako je spring.jpa.hibernate.ddl-auto=create u application.properties fajlu. Skriptu je potrebno kopirati u src/main/resources ako vec ne postoji ili je izvrsiti samostalno, npr. u mysqlWorkbenchu.
 Baza se zove db_school_2, sa keredencijalima: korisnik = springuser i password = ThePassword 

