INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'),'Cineplexx Promenada','Bulevar Oslobodjenja 119');
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'),'Arena Cineplex','Bulevar Mihajla Pupina 3');
INSERT INTO "bioskop"("id", "naziv", "adresa") VALUES(nextval('bioskop_seq'),'CineStar','Sentandrejski put 11');

INSERT INTO "film"("id", "trajanje", "naziv", "recenzija", "zanr") VALUES(nextval('film_seq'),125,'Decak i caplja','8','Avantura');
INSERT INTO "film"("id", "trajanje", "naziv", "recenzija", "zanr") VALUES(nextval('film_seq'),166,'Dina: Drugi deo','9','Avantura');
INSERT INTO "film"("id", "trajanje", "naziv", "recenzija", "zanr") VALUES(nextval('film_seq'),115,'Godzila x Kong: Novo carstvo','9','Akcija');
INSERT INTO "film"("id", "trajanje", "naziv", "recenzija", "zanr") VALUES(nextval('film_seq'),84,'Jorgovani','8','Drama');
INSERT INTO "film"("id", "trajanje", "naziv", "recenzija", "zanr") VALUES(nextval('film_seq'),95,'Kung Fu Panda 4','7','Avantura');
INSERT INTO "film"("id", "trajanje", "naziv", "recenzija", "zanr") VALUES(nextval('film_seq'),89,'Immaculate','6','Horor');
INSERT INTO "film"("id", "trajanje", "naziv", "recenzija", "zanr") VALUES(nextval('film_seq'),121,'Monkey Man','9','Triler');

INSERT INTO "sala"("id", "broj_redova", "kapacitet", "bioskop") VALUES(nextval('sala_seq'),20,250,1);
INSERT INTO "sala"("id", "broj_redova", "kapacitet", "bioskop") VALUES(nextval('sala_seq'),30,300,1);
INSERT INTO "sala"("id", "broj_redova", "kapacitet", "bioskop") VALUES(nextval('sala_seq'),15,200,2);
INSERT INTO "sala"("id", "broj_redova", "kapacitet", "bioskop") VALUES(nextval('sala_seq'),10,150,3);

INSERT INTO "rezervacija"("id", "broj_osoba", "cena_karte", "datum", "placeno", "film", "sala") 
VALUES(nextval('rezervacija_seq'),2,800,'2024-04-16', true, 1, 1);
INSERT INTO "rezervacija"("id", "broj_osoba", "cena_karte", "datum", "placeno", "film", "sala") 
VALUES(nextval('rezervacija_seq'),1,400,'2024-04-20', true, 5, 2);
INSERT INTO "rezervacija"("id", "broj_osoba", "cena_karte", "datum", "placeno", "film", "sala") 
VALUES(nextval('rezervacija_seq'),3,1200,'2024-04-28', true, 2, 4);
INSERT INTO "rezervacija"("id", "broj_osoba", "cena_karte", "datum", "placeno", "film", "sala") 
VALUES(nextval('rezervacija_seq'),1,400,'2024-04-16', true, 7, 1);



