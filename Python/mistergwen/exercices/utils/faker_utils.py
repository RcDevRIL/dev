from models.user import User
from faker import Faker
from faker.providers import BaseProvider


class UserProvider(BaseProvider):
    """ Create a custom provider based on `BaseProvider`
    """

    def user(self, faker: Faker):
        """ Generates a new random User instance using faker base providers
        """
        return User(faker.first_name(),
                    faker.last_name(), faker.job(), faker.address())


def archive_users(users_to_archive: list) -> bool:
    """ Cette fonction tente d'archiver les utilisateurs au format JSON
    return True si cette action est un succès, False sinon
    """
    # for user in users_to_archive:
    #     print(f'#### USER #{users_to_archive.index(user)+1} ####')
    #     print(users_to_archive.__getitem__(users_to_archive.index(user)))
    #     print(f'#################')
    try:

        list_as_file = open('users.json', 'w+')
        list_as_file.write('[\r\n')
        for i in range(users_to_archive.__len__() - 1):
            list_as_file.write(f'{users_to_archive.__getitem__(i)},\n')
        list_as_file.write(f'{users_to_archive.pop()}\n')
        list_as_file.write(']\r\n')
        list_as_file.close()
        print('Archivage terminé. À bientôt !')
        return True
    except Exception as e:
        print(e)
        return False


def init_gen() -> Faker:
    # Create a faker instance
    faker = Faker(['fr_FR'])
    # Adds the custom provider to the faker instance
    faker.add_provider(UserProvider)
    return faker


def gen_user(faker: Faker) -> User:
    return faker.user(faker)
