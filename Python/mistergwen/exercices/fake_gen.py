from utils.faker_utils import archive_users, init_gen, gen_user
from faker import Faker


#### SCRIPT ####
fake_generator = init_gen()
error = True
while(error):
    print('\n################################################################')
    print('## Bienvenue dans ce programme de génération d\'utilisateur ! ###')
    print('################################################################\n')
    print('Combien d\'utilisateur souhaitez-vous créer?\n')
    try:
        number_to_gen = int(
            input('>>> '))
        if(number_to_gen < 0):
            raise ValueError
        user_list = []
        for i in range(number_to_gen):
            user_list.append(gen_user(fake_generator))
            print(f'#### USER #{i+1} ####')
            print(user_list[i])
            print(f'#################\n')
        print('Souhaitez-vous sauvegarder ces utilisateurs au format JSON? (y/n)\n')
        archive = input('>>> ')
        if (archive.__len__() == 1 and archive.isalpha):
            if(archive == 'y' or archive == 'Y'):
                archive_users(user_list)
                error = False
            elif(archive == 'n' or archive == 'N'):
                print('\nOk, à bientôt !')
                error = False
            else:
                raise ValueError
        else:
            raise ValueError

    except ValueError:
        print('\nValeur spécifiée incorrecte... Redémarrage du générateur.\n')
