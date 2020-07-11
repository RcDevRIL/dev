
print('#### LISTE AVEC DOUBLONS ####')
ma_liste = [92593568524.558, 'https://sanchez.com/category/categories/category/',
            'nGfYFofaZMjApaTpaMYj', "blabla", -3.546 - 5058194594348.75, 92593568524.558, 'h', 45, -3.546]
print(f'{ma_liste}\n')
print('#### LISTE SANS DOUBLONS ####')
print('exécution de la commande "list(set(ma_liste))"...\n')
ma_liste_sans_doublons = list(set(ma_liste))
print(ma_liste_sans_doublons)
