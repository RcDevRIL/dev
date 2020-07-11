from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.engine import create_engine
from utils.faker_utils import gen_user, init_gen
from sqlalchemy.orm import sessionmaker
from sqlalchemy import Column, Integer, String

#### CLASSES ####
Base = declarative_base()


class UserSQL(Base):
    """ Classe représentant un utilisateur
    """

    __tablename__ = 'users'

    def __repr__(self):
        no_breaks_adress = self.address.replace("\n", " - ")
        return '{{\n  "Name": "{}",\r\n  "Job Title": "{}",\r\n  "Address": "{}"\r\n}}'.format(self.name, self.job, no_breaks_adress)

    id = Column(Integer, primary_key=True)
    name = Column('name', String(50))
    job = Column('job', String(50))
    address = Column('address', String(50))


#### SCRIPT ####
engine = create_engine('sqlite:///:memory:', echo=True)
Session = sessionmaker(bind=engine)
session = Session()
Base.metadata.create_all(engine)
fake_generator = init_gen()
error = True
print('Combien d\'utilisateur souhaitez-vous créer?\n')
while(error):
    try:
        number_to_gen = int(
            input('>>> '))
        if(number_to_gen < 0):
            raise ValueError
        user_list = []
        for i in range(number_to_gen):
            user_list.append(UserSQL(id=i, name=fake_generator.name(
            ), job=fake_generator.job(), address=fake_generator.address()))
            print(f'#### USER #{i+1} ####')
            print(user_list[i])
            print(f'#################\n')
            try:
                print('Trying to add this user in database...')
                session.add(user_list[i])
                print('Success.\n')
            except Exception as e:
                print('Error while adding user in database !')
                print(e.__cause__)
                exit(0)
        print('##### Begin of commit to database #####')
        session.commit()
        print('##### SUCCESS !')
        session.close()
        error = False
    except Exception:
        print('\nERROR\n')
