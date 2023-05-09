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
 
 Ukoliko se prilikom pokretanja servera dobije greška da nije moguće pronaći UserMapper, potrebno je uraditi maven clean i maven install da se 
 generiše fajl UserMapperImpl.java u target/generated-sources/annotations/com/iktpreobuka/schoollogtwo/util.

