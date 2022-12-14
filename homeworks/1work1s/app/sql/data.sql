insert into concert(title, author, description, image_path)
VALUES ('The Nutcracker', 'Pyotr Ilych Tchaikovsky', 'The Nutcracker is a two-act ballet by Pyotr Ilyich Tchaikovsky. Tchaikovskys adaptation of the story "The Nutcracker and the Mouse King" by E. T. A. Hoffmann was commissioned by the director of the Imperial Theatres Ivan Vsevolozhsky in 1891. The original production was staged by Marius Petipa on 18 December 1892, premiering on a double-bill with a now semi-forgotten Tchaikovsky opera, Iolanta.', 'images/nutcracker.jpg'),
       ('The Sleeping Beauty', 'Pyotr Ilych Tchaikovsky', 'The Sleeping Beauty was the second of Tchaikovsky three ballet scores, based on the fairy tale by Charles Perrault. It was composed and orchestrated from October 1888 to August 1889, with minor revisions during stage rehearsals in the last three months of 1889.', 'images/sleeping_beauty.webp'),
        ('Swan Lake', 'Pyotr Ilych Tchaikovsky', 'Swan Lake was the first of Tchaikovskys ballet scores, composed between August 1875 and April 1876, with additions and revisions in February and April 1877. The ballets original title, as indicated on the manuscript score, was The Lake of Swans or Lake of the Swans, and its story derives from a German fairy tale.', 'images/swan_lake.jpg');

INSERT INTO presentation(concert_id, time)
VALUES (1, '2022-12-14 14:00:00'),
       (1, '2022-12-14 18:00:00'),
       (1, '2022-12-15 15:00:00')