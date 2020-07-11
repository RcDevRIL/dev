from getpass import getpass


def capitalize_input(prompt):
    i = input(prompt)
    return i.capitalize()


saved_password = 'T3st!'
error = True
name = capitalize_input('Comment vous appelez-vous ?\n')
while(error):
    try:
        assert saved_password == getpass('Mot de passe: ')
        print(f'\nBienvenue {name}... :-)')
        error = False
    except AssertionError:
        print('\nMauvais mot de passe...')

print('Mot de passe trouvé !')
