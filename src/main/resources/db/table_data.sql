INSERT INTO INNTEKT_TYPE (NAVN, BESKRIVELSE) VALUES ('UTLEIE_INNTEKT', 'Inntekt på utleie hver måned');

INSERT INTO UTGIFT_TYPE (NAVN, BESKRIVELSE) VALUES ('HUSLEIE', 'Fast husleie utgift hver måned');
INSERT INTO UTGIFT_TYPE (NAVN, BESKRIVELSE) VALUES ('FESTEAVGIFT', 'Festeavgift');
INSERT INTO UTGIFT_TYPE (NAVN, BESKRIVELSE) VALUES ('TILLEGGSUTGIFT', 'Diverse utgift');

INSERT INTO LEILIGHET (NAVN, ADRESSE, POSTNR, POSTSTED) VALUES ('Glaenga', 'Gladengveien 15C', '0661', 'Oslo');
INSERT INTO LEILIGHET (NAVN, ADRESSE, POSTNR, POSTSTED) VALUES ('Ensjø Torg', 'Ensjøveien 21D', '0662', 'Oslo');

INSERT INTO INNTEKT (LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND) VALUES (1, 1, 23432, 2023, 1);
INSERT INTO INNTEKT (LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND) VALUES (1, 1, 6575, 2023, 2);
INSERT INTO INNTEKT (LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND) VALUES (1, 1, 123, 2023, 3);
INSERT INTO INNTEKT (LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND) VALUES (1, 1, 978, 2023, 4);