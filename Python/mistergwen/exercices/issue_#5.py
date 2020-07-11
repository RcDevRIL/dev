from decimal import Decimal
import datetime

print('###### LISTE TRIEE ######\n')
the_list = [4560, 8460, 'boydbrian@hotmail.com', 'XCVTHbZvoxWoFSvEgTIK',
            'OdpIdJoYbQTkAZknwxNm', 'https://bennett.com/home/', 'rixTcgrYwCEYSaZkJjVG']
print(f'Liste à trier: {the_list}\n')
print(f'Liste triée: {sorted(the_list, key=lambda e: str(e)[1])}')

print('\n###### ECRITURE FICHIER ######\n')
# Ouvre le fichier 'malistetriée.txt' en mode 'w+'
list_as_file = open('malistetriée.txt', 'w+')
# Pour chaque élément de la liste triée, on écrit une ligne dans le fichier
for e in sorted(the_list, key=lambda e: str(e)[1]):
    list_as_file.write(f'{e}\n')
# On ferme le fichier pour enregistrer les lignes
list_as_file.close()
# On l'ouvre en mode lecture 'r' pour afficher le contenu dans la console
list_as_file = open('malistetriée.txt', 'r')
print(f'Contenu du fichier:\n\n{list_as_file.read()}')

print('###### LISTE CONCAT ######\n')
# Données:
list1 = ['caNlEEFLlHVMpmnwHjMb', Decimal('78.63338397'), 9957, 8911, 3044,
         'http://becker-carpenter.net/search.asp', 4157, 7982, 16226.39890321, 6730, 'LEChstZAjDFhjcZTNVAq']
list2 = ['mebWzEGMVBesnXyNFWbx', 'LrpxlUYmCXhKrtbOImQB', 4087, 'patricia27@hotmail.com', 'shWsCxLYznvPNeVKjXqv', 'cNwLCYFsYtvyDVRFITVP',
         datetime.datetime(1986, 9, 9, 7, 51, 58), 'tavKoHkYUrQteeLHGtUu', 'umartin@yahoo.com', 'ZpzzBCroLyiDkyehgDya', 597724697.8, 2220]
print(f'list1 = {list1}\nlist2 = {list2}\n\n')
list1.extend(list2)  # Applique la concaténation
print(f'Listes concaténées:\n{list1}')
