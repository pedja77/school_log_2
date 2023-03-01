# Electronic Grade Book REST API

![Imgur](https://i.imgur.com/RR9yR89.png)

Može biti problem ako je browser u night modu ili je neka dark tema.

Za nasledjivanje je korišćen pristup tabela po entitetu kod UserEntity i njenih podklasa.
Kod ocene i zakljucne ocene je korišćen pristup sa jednom tabelom za sve.

Brisanje je implementirano kao "soft delete", bez oslanjanja na CascadeType.REMOVE zbog nekih neželjenih efekata. 