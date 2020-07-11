import sys


def capitalize_input(prompt):
    i = input(prompt)
    return i.capitalize()


# ask for user input and capitalize first letter
user_name = capitalize_input('Bonjour ! Comment t\'appelles tu ?\n')
# greet the user again
print(f'Bonjour, {user_name} !\n')
user_age = int(input('Quel est ton âge ?\n'))
# answer differently according to `userAge`
if(user_age < 10):
    print(f'Ok, petit homme, merci pour tes réponses {user_name}')
elif(user_age >= 10 and user_age < 30):
    print(f'Ok, jeune homme, merci pour tes réponses {user_name}')
else:
    print(
        f'Flemme de faire plus de cas, merci pour les réponses en tout cas {user_name} :-)')
